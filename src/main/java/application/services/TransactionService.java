package application.services;

import domain.models.Account;
import domain.models.Currency;
import domain.models.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction deposit(Long amount, Account account, Currency currency);

    List<Transaction> transfer(Long amount, Account origin, Account destiny, Currency currency);
}
