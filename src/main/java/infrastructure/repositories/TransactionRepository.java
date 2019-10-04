package infrastructure.repositories;

import domain.exceptions.TransactionNotExecutedException;
import domain.models.Transaction;
import org.jdbi.v3.core.Jdbi;

public class TransactionRepository {

    private Jdbi jdbi;

    public TransactionRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public Transaction save(Transaction tx) {

        try {
            String query = "INSERT INTO transactions(amount, operation, origin_account_id, " +
                    "destiny_account_id, external_id,created_at, currency) " +
                    "VALUES (:amount, :operation, :originAccountId, " +
                    ":destinyAccountId, :externalId, :createdAt, :currency)";

            jdbi.withHandle(handle ->
                    handle.createUpdate(query)
                            .bind("amount", tx.getAmount())
                            .bind("operation", tx.getOperation())
                            .bind("originAccountId", tx.getOrigin().getId())
                            .bind("destinyAccountId", tx.getDestiny().getId())
                            .bind("externalId", tx.getExternalId())
                            .bind("createdAt", tx.getCreatedAt())
                            .bind("currency", tx.getCurrency())
            );
            return tx;
        }catch (Exception e) {
            throw new TransactionNotExecutedException();
        }
    }
}
