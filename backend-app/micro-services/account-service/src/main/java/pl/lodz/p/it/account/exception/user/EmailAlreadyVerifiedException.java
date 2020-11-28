package pl.lodz.p.it.account.exception.user;

import pl.lodz.p.it.account.exception.AccountError;
import pl.lodz.p.it.account.exception.AccountException;

public class EmailAlreadyVerifiedException extends AccountException {
    public EmailAlreadyVerifiedException() {
        super(AccountError.EMAIL_ALREADY_VERIFIED.toString());
    }
}
