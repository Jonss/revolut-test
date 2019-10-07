package infrastructure.repositories;

import domain.exceptions.TransactionNotExecutedException;
import domain.models.Transaction;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

public class TransactionRepository {

    private Logger logger = LoggerFactory.getLogger(TransactionRepository.class);
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

    public CompletableFuture<Transaction> asyncSave(Transaction transaction) {
        logger.info("Tx async save " + Thread.currentThread().getName());
        return CompletableFuture.completedFuture(save(transaction));
    }
}
