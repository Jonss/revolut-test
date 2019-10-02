package domain.exceptions;

public class BalanceException extends RuntimeException {

    public BalanceException() {
        super("Balance below requested.");
    }
}
