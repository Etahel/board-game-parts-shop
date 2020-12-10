package pl.lodz.p.it.bges.account.exception.user;

import pl.lodz.p.it.bges.account.exception.AccountError;
import pl.lodz.p.it.bges.account.exception.AccountException;

public class RegistrationDataException extends AccountException {

    public RegistrationDataException() {
        super(AccountError.REGISTRATION_INVALID_DATA.toString());
    }
}
