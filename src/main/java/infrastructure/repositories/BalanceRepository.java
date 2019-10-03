package infrastructure.repositories;

import domain.models.Account;
import domain.models.Balance;
import domain.models.Currency;
import domain.models.Transaction;
import org.jdbi.v3.core.Jdbi;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BalanceRepository {

    private Jdbi jdbi;

    public BalanceRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    // TODO Handle optional
    public Balance findBalance(@NotNull Account account, Currency currency) {
        try {
            String query = "SELECT * FROM balances WHERE account_id = :accountId " +
                    "AND currency = :currency ORDER BY created_at DESC LIMIT 1";

            Optional<Balance> balance = jdbi.withHandle(handle ->
                    handle.createQuery(query)
                            .bind("accountId", account.getId())
                            .bind("currency", currency)
                            .mapToBean(Balance.class)
                            .findFirst());

            return balance.get();
        } catch (Exception e) {
            return new Balance(0L, null, account, currency);
        }
    }

    @NotNull
    public List<Balance> findAllBalance(@NotNull Account account) {
        try {
            String query = "SELECT * FROM balances WHERE account_id = :accountId";

            return jdbi.withHandle(handle ->
                    handle.createQuery(query)
                            .bind("accountId", account.getId())
                            .mapToBean(Balance.class)
                            .list()
            );
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void updateBalance(Transaction transaction) {
        try {
            String query = "INSERT INTO balances(total, last_deposit, account_id, currency) VALUES" +
                    "(:total, lastDeposit, :accountId, :currency);";

            Account account = transaction.getDestiny();
            Balance latestBalance = findBalance(account, transaction.getCurrency());

            jdbi.withHandle(handle ->
                    handle.createQuery(query)
                            .bind("total", latestBalance.getTotal() + transaction.getAmount())
                            .bind("last_deposit", transaction.getCreatedAt())
                            .bind("accountId", account.getId())
                            .bind("currency", transaction.getCurrency()));


        } catch (Exception e) {
            throw e;
        }
    }

}

