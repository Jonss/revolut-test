package domain.exceptions;

public class AccountNotCreatedException extends RuntimeException {

    public AccountNotCreatedException(String message) {
        super(message);
    }

    public AccountNotCreatedException() {
        super("Account not created. Try again later.");
    }
}
