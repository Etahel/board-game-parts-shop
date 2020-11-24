package pl.lodz.p.it.account.exception.keycloak;

import lombok.Getter;
import pl.lodz.p.it.account.exception.AccountError;
import pl.lodz.p.it.account.exception.AccountException;

@Getter
public class KeycloakConnectionException extends AccountException {

    public KeycloakConnectionException() {
        super(AccountError.KEYCLOAK_CONNECTION_FAILURE.toString());
    }

}
