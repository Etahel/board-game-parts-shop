package pl.lodz.p.it.account.exception.user;

import pl.lodz.p.it.account.exception.AccountError;
import pl.lodz.p.it.account.exception.AccountException;

public class EmailTakenException extends AccountException {
    public EmailTakenException() {
        super(AccountError.EMAIL_TAKEN.toString());
    }
}
