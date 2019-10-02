package infrastructure.repositories;

import domain.models.Account;
import domain.models.Balance;
import domain.models.Currency;
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
            String query = "SELECT * FROM balances WHERE account_id = :accountId AND currency = :currency";

            Optional<Balance> balance = jdbi.withHandle(handle ->
                    handle.createQuery(query)
                            .bind("accountId", account.getId())
                            .bind("currency", currency)
                            .mapToBean(Balance.class)
                            .findFirst());

            return balance.get();
        } catch (Exception e) {
            return new Balance();
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
}

