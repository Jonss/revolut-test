package application.services;

import domain.exceptions.BalanceException;
import domain.exceptions.EntityNotFoundException;
import domain.models.*;
import infrastructure.repositories.TransactionRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private AccountService accountService;
    private BalanceService balanceService;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  AccountService accountService,
                                  BalanceService balanceService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.balanceService = balanceService;
    }

    @Override
    public Transaction deposit(Long amount, Account account, Currency currency) {
        Optional<Account> revolutIssuer = accountService.findRevolutIssuer();
        if(!revolutIssuer.isPresent())
            throw new EntityNotFoundException("Issuer not found.");

        Transaction transaction = new Transaction(amount, account, revolutIssuer.get(), Operation.SAVING, currency);
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public List<Transaction> transfer(Long amount, Account origin, Account destiny, Currency currency) {
        Balance balance = balanceService.findBalance(origin, currency);
        if(balance.getTotal() < amount) {
            throw new BalanceException();
        }

        Transaction transfer = new Transaction(amount, origin, destiny, Operation.TRANSFER, currency);
        Transaction withdrawal = new Transaction(amount, destiny, origin, Operation.WITHDRAWAL, currency);

        List<Transaction> transactions = Arrays.asList(transfer, withdrawal);

        transactions.
                forEach(t -> transactionRepository.save(t));

        return transactions;
    }
}
