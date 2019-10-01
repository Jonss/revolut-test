package application.services;

import domain.models.Account;
import domain.models.Currency;
import domain.models.Transaction;
import infrastructure.repositories.TransactionRepository;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Override
    public Transaction deposit(Long amount, Account account, Currency currency) {
        return null;
    }

    @Override
    public List<Transaction> transfer(Long amount, Account origin, Account destiny, Currency currency) {
        return null;
    }
}
