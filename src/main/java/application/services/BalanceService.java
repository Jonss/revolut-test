package application.services;

import domain.models.Account;
import domain.models.Balance;
import domain.models.Currency;
import domain.models.Transaction;

import java.util.List;

public interface BalanceService {

    Balance findBalance(Account account, Currency currency);

    List<Balance> findAllBalance(Account account);

    void append(Transaction transaction);

    void asyncAppend(Transaction transaction);
}
