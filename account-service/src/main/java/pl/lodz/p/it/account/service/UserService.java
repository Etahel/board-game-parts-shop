package pl.lodz.p.it.account.service;

import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.account.dto.UserDto;
import pl.lodz.p.it.account.exception.AccountException;
import pl.lodz.p.it.account.exception.keycloak.KeycloakConnectionException;
import pl.lodz.p.it.account.properties.RolesProperties;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

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

    public void registerUser(UserDto userDto) throws AccountException {
        UserRepresentation user = new UserRepresentation();
        userDto.putProperties(user);
        user.setRealmRoles(new ArrayList<String>());
        user.getRealmRoles().add(rolesProperties.getUser());
        UsersResource usersResource = keycloakService.getUserResource();
        Response response;
        try {
            response = usersResource.create(user);
        } catch (Exception e) {
            throw new KeycloakConnectionException();
        }
        keycloakService.processResponse(response);

    }


}
