package pl.lodz.p.it.bges.account.service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.bges.account.exception.AccountException;
import pl.lodz.p.it.bges.account.exception.user.EmailTakenException;
import pl.lodz.p.it.bges.account.exception.user.RegistrationDataException;
import pl.lodz.p.it.bges.account.exception.user.UsernameTakenException;
import pl.lodz.p.it.bges.account.properties.KeycloakAdminProperties;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Service
@EnableConfigurationProperties(KeycloakAdminProperties.class)
public class KeycloakService {

    Logger logger = LoggerFactory.getLogger(KeycloakService.class);


    private Keycloak keycloak;
    private KeycloakAdminProperties adminProperties;

    @Autowired
    public KeycloakService(Keycloak keycloak, KeycloakAdminProperties adminProperties) {
        this.keycloak = keycloak;
        this.adminProperties = adminProperties;
    }

    public RealmResource getRealm() {
        return keycloak.realm(adminProperties.getRealm());
    }

    public UsersResource getUsersResource() {
        return keycloak.realm(adminProperties.getRealm()).users();
    }

    public UserResource getUserResource(String id) {
        return keycloak.realm(adminProperties.getRealm()).users().get(id);
    }

    public RolesResource getRolesResource() {
        return keycloak.realm(adminProperties.getRealm()).roles();
    }

    public void closeConnection() {
        keycloak.close();
    }

    public void processRegisterResponse(Response response) throws AccountException {
        if (response.getStatus() != HttpStatus.CREATED.value() && response.getStatus() != HttpStatus.OK.value()) {
            HashMap responseMap = response.readEntity(new GenericType<HashMap<String, String>>() {
            });
            String errorMessage = ((String) responseMap.get("errorMessage"));

            if (response.getStatus() == HttpStatus.CONFLICT.value()) {
                logger.debug("Keycloak error: " + response.getStatus() + " " + errorMessage);
                if (errorMessage.contains("same email")) {
                    throw new EmailTakenException();
                }
                if (errorMessage.contains("same username")) {
                    throw new UsernameTakenException();
                }
            } else {
                logger.error("Unknown keycloak error: " + response.getStatus() + " " + errorMessage);
                throw new RegistrationDataException();
            }
        }
    }
}
