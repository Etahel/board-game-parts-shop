package pl.lodz.p.it.account.exception.user;

import pl.lodz.p.it.account.exception.AccountError;
import pl.lodz.p.it.account.exception.AccountException;

public class UsernameTakenException extends AccountException {

    public UsernameTakenException() {
        super(AccountError.USERNAME_TAKEN.toString());
    }
}
