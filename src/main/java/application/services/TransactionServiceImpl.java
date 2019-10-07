package application.services;

import domain.exceptions.BalanceException;
import domain.exceptions.EntityNotFoundException;
import domain.models.*;
import infrastructure.repositories.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TransactionServiceImpl implements TransactionService {

    private Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private TransactionRepository transactionRepository;
    private AccountService accountService;
    private BalanceService balanceService;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  AccountService accountService,
                                  BalanceService balanceService) {
        this.balanceService = balanceService;
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    @Override
    public Transaction deposit(Long amount, Account account, Currency currency) {

        Optional<Account> revolutIssuer = accountService.findRevolutIssuer();

        if(!revolutIssuer.isPresent())
            throw new EntityNotFoundException("Issuer not found.");

        Transaction transaction = new Transaction(amount,revolutIssuer.get(), account, Operation.SAVING, currency);

        transactionRepository.save(transaction);
        balanceService.append(transaction);
        return transaction;
    }

    @Override
    public List<Transaction> transfer(Long amount, Account origin, Account destiny, Currency currency) {
        Balance balance = balanceService.findBalance(origin, currency);

        if(hasNoBalance(amount, balance)) {
            throw new BalanceException();
        }

        logger.info("Transfer {} {} from [{}] to [{}]", currency, amount, origin.getExternalId(), destiny.getExternalId());

        Transaction withdrawal = new Transaction(amount, destiny, origin, Operation.WITHDRAWAL, currency);
        Transaction transfer = new Transaction(amount, origin, destiny, Operation.TRANSFER, currency);

        handleWithdrawal(withdrawal);
        handleTransfer(transfer);

        return Arrays.asList(transfer, withdrawal);
    }

    private void handleTransfer(Transaction transaction) {
         transactionRepository.save(transaction);
         balanceService.append(transaction);
    }

    private void handleWithdrawal(Transaction transaction) {
        transactionRepository.asyncSave(transaction);
        balanceService.asyncAppend(transaction);
    }



    private boolean hasNoBalance(Long amount, Balance balance) {
        return balance.getTotal() == null || balance.getTotal() < amount;
    }
}
