package pl.lodz.p.it.bges.account.exception.user;

import pl.lodz.p.it.bges.account.exception.AccountError;
import pl.lodz.p.it.bges.account.exception.AccountException;

public class UserNotFoundException extends AccountException {

    public UserNotFoundException() {
        super(AccountError.USER_NOT_FOUND.toString());
    }
}
