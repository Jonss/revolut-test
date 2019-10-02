package domain.exceptions;

public class TransactionNotExecutedException extends RuntimeException {
    public TransactionNotExecutedException(String message) {
        super(message);
    }
    public TransactionNotExecutedException() {
        super("Transaction not excecuted. Try again later.");
    }
}