package pl.lodz.p.it.bges.account.service;

import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.bges.account.config.KeycloakConfig;
import pl.lodz.p.it.bges.account.dto.user.UserCompleteDto;
import pl.lodz.p.it.bges.account.exception.AccountException;
import pl.lodz.p.it.bges.account.exception.keycloak.KeycloakConnectionException;
import pl.lodz.p.it.bges.account.exception.keycloak.VerificationEmailException;
import pl.lodz.p.it.bges.account.exception.user.EmailAlreadyVerifiedException;
import pl.lodz.p.it.bges.account.exception.user.UserNotFoundException;
import pl.lodz.p.it.bges.account.properties.RolesProperties;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

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

    public void sendResetPasswordEmail(String username) throws AccountException {
        UserRepresentation user = getUser(username);
        sendEmailAction(user, KeycloakConfig.EmailAction.UPDATE_PASSWORD);
    }

    public void sendVerifyEmail(String username) throws AccountException {
        UserRepresentation user = getUser(username);
        if (user.isEmailVerified()) {
            throw new EmailAlreadyVerifiedException();
        }
        sendEmailAction(user, KeycloakConfig.EmailAction.VERIFY_EMAIL);
    }

    private void sendEmailAction(UserRepresentation user, KeycloakConfig.EmailAction action) throws AccountException {
        UserResource resource = keycloakService.getUserResource(user.getId());
        try {
            resource.executeActionsEmail(Arrays.asList(action.toString()));
        } catch (Exception e) {
            throw new KeycloakConnectionException();
        }
    }

    public UserRepresentation getUser(String username) throws AccountException {
        UsersResource usersResource = keycloakService.getUsersResource();
        List<UserRepresentation> users;
        try {
            users = usersResource.search(username, true);
        } catch (Exception e) {
            throw new KeycloakConnectionException();
        }
        if (users.isEmpty() || users.get(0) == null) {
            throw new UserNotFoundException();
        }
        return users.get(0);
    }

    public void changeEmail(String username, String email) throws AccountException {
        UserRepresentation user = getUser(username);
        user.setEmail(email);
        user.setEmailVerified(true);
        UserResource resource = keycloakService.getUserResource(user.getId());
        try {
            resource.update(user);
        } catch (Exception e) {
            throw new KeycloakConnectionException();
        }
    }

//    public void patchUser(String username, UserBaseDto userDetails) throws AccountException {
//        UserRepresentation user = getUser(username);
//        UserResource resource = keycloakService.getUserResource(user.getId());
//        userDetails.patchProperties(user);
//        try {
//            resource.update(user);
//        } catch (Exception e) {
//            throw new KeycloakConnectionException();
//        }
//
//    }


}
