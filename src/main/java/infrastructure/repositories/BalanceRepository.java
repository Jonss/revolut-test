package infrastructure.repositories;

import domain.models.Account;
import domain.models.Balance;
import domain.models.Currency;
import domain.models.Transaction;
import org.jdbi.v3.core.Jdbi;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BalanceRepository {

    private Logger logger = LoggerFactory.getLogger(BalanceRepository.class);

    private Jdbi jdbi;

    public BalanceRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public Balance findBalance(@NotNull Account account, Currency currency) {
        try {
            String query = "SELECT * FROM balances WHERE account_id = :accountId " +
                    "AND currency = :currency ORDER BY last_deposit DESC LIMIT 1";

            List<Balance> balances = jdbi.withHandle(handle ->
                    handle.createQuery(query)
                            .bind("accountId", account.getId())
                            .bind("currency", currency.name())
                            .mapToBean(Balance.class)
                            .list());

            return balances.stream().findFirst().get();
        } catch (Exception e) {
            logger.info("Account [{}] has no balance.", account.getExternalId());
            return new Balance(0L, null, 0L, currency);
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

    public void append(Transaction transaction) {
        try {
            String query = "INSERT INTO balances(total, last_deposit, account_id, currency) VALUES" +
                    "(:total, :lastDeposit, :accountId, :currency);";

            Account account = transaction.getDestiny();
            Balance latestBalance = findBalance(account, transaction.getCurrency());

            Balance balance = new Balance(latestBalance.getTotal() + transaction.getAmount(), LocalDateTime.now(), account.getId(), transaction.getCurrency());

            jdbi.withHandle(handle ->
                    handle.createUpdate(query)
                            .bind("total", balance.getTotal())
                            .bind("lastDeposit", balance.getLastDeposit())
                            .bind("accountId", balance.getAccountId())
                            .bind("currency", balance.getCurrency())
                            .execute());

        } catch (Exception e) {
            logger.error("Could not append amount [{}] to account [{}]", transaction.getAmount(), transaction.getDestiny().getExternalId());
            throw e;
        }
    }

}
