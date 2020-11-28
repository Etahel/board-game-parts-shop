package pl.lodz.p.it.account.service;

import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.account.config.KeycloakConfig;
import pl.lodz.p.it.account.dto.user.UserBaseDto;
import pl.lodz.p.it.account.dto.user.UserCompleteDto;
import pl.lodz.p.it.account.exception.AccountException;
import pl.lodz.p.it.account.exception.keycloak.KeycloakConnectionException;
import pl.lodz.p.it.account.exception.keycloak.VerificationEmailException;
import pl.lodz.p.it.account.exception.user.UserNotFoundException;
import pl.lodz.p.it.account.properties.RolesProperties;

import javax.ws.rs.core.Response;
import java.util.Arrays;

@Service
@EnableConfigurationProperties(RolesProperties.class)
public class UserService {

    private KeycloakService keycloakService;
    private RolesProperties rolesProperties;

    @Autowired
    public UserService(KeycloakService keycloakService, RolesProperties rolesProperties) {
        this.keycloakService = keycloakService;
        this.rolesProperties = rolesProperties;
    }

    public void registerUser(UserCompleteDto userCompleteDto) throws AccountException {
        UserRepresentation user = new UserRepresentation();
        userCompleteDto.putProperties(user);
        user.setEnabled(true);
        user.setRequiredActions(Arrays.asList(KeycloakConfig.EmailAction.VERIFY_EMAIL.toString()));
        UsersResource usersResource = keycloakService.getUsersResource();
        Response response;

        try {
            response = usersResource.create(user);
        } catch (Exception e) {
            throw new KeycloakConnectionException();
        }

        keycloakService.processRegisterResponse(response);

        try {
            sendVerifyEmail(user.getUsername());
        } catch (Exception e) {
            throw new VerificationEmailException();
        }
    }

    public void sendResetPasswordEmail(String username) throws UserNotFoundException, KeycloakConnectionException {
        sendEmailAction(username, KeycloakConfig.EmailAction.UPDATE_PASSWORD);
    }

    public void sendVerifyEmail(String username) throws UserNotFoundException, KeycloakConnectionException {
        sendEmailAction(username, KeycloakConfig.EmailAction.VERIFY_EMAIL);
    }

    private void sendEmailAction(String username, KeycloakConfig.EmailAction action) throws UserNotFoundException, KeycloakConnectionException {
        UserRepresentation user = getUser(username);
        UserResource resource = keycloakService.getUserResource(user.getId());
        try {
            resource.executeActionsEmail(Arrays.asList(action.toString()));
        } catch (Exception e) {
            throw new KeycloakConnectionException();
        }
    }

    public UserRepresentation getUser(String username) throws UserNotFoundException {
        UsersResource usersResource = keycloakService.getUsersResource();
        try {
            return usersResource.search(username, true).get(0);
        } catch (Exception e) {
            throw new UserNotFoundException();
        }

    }

    public void patchUser(String username, UserBaseDto userDetails) throws UserNotFoundException, KeycloakConnectionException {
        UserRepresentation user = getUser(username);
        UserResource resource = keycloakService.getUserResource(user.getId());
        userDetails.patchProperties(user);
        try {
            resource.update(user);
        } catch (Exception e) {
            throw new KeycloakConnectionException();
        }

    }


}
