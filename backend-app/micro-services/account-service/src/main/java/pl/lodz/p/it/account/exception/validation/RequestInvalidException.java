package pl.lodz.p.it.account.exception.validation;

import pl.lodz.p.it.account.exception.AccountError;
import pl.lodz.p.it.account.exception.AccountException;

public class RequestInvalidException extends AccountException {
    public RequestInvalidException() {
        super(AccountError.REQUEST_NOT_VALID.toString());
    }
}
