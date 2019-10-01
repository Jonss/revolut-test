package infrastructure.repositories;

import domain.models.Transaction;
import org.jdbi.v3.core.Jdbi;

public class TransactionRepository {

    private final Jdbi jdbi;

    public TransactionRepository(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public Transaction save(Transaction tx) {
        return null;
    }
}
