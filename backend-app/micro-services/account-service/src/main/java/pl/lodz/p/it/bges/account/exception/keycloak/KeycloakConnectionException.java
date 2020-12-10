package pl.lodz.p.it.bges.account.exception.keycloak;

import lombok.Getter;
import pl.lodz.p.it.bges.account.exception.AccountError;
import pl.lodz.p.it.bges.account.exception.AccountException;

@Getter
public class KeycloakConnectionException extends AccountException {

    public KeycloakConnectionException() {
        super(AccountError.KEYCLOAK_CONNECTION_FAILURE.toString());
    }

}
