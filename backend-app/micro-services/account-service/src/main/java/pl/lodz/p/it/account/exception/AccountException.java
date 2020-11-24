package pl.lodz.p.it.account.exception;

public class AccountException extends Exception {

    AccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountException(String message) {
        super(message);
    }
}
