package pl.lodz.p.it.bges.account.exception.keycloak;

import pl.lodz.p.it.bges.account.exception.AccountError;
import pl.lodz.p.it.bges.account.exception.AccountException;

public class VerificationEmailException extends AccountException {

    public VerificationEmailException() {
        super(AccountError.VERIFICATION_EMAIL_NOT_SENT.toString());
    }
}
