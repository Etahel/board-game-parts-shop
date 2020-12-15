package pl.lodz.p.it.bges.account.exception;


import pl.lodz.p.it.bges.core.exception.AppException;

public class AccountException extends AppException {

    AccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountException(String message) {
        super(message);
    }
}
