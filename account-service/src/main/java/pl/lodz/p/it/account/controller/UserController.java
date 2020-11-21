package pl.lodz.p.it.account.controller;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;

@RequestMapping("/user")
@RestController
public class UserController {

    @Value("${keycloak.realm}")
    private String realm;

    @Autowired
    private Keycloak keycloak;

    @GetMapping(path = "/test")
    @RolesAllowed("user")
    public String test(Principal principal)
    {
        return principal.getName();
    }

    @GetMapping(path = "/register")
    @PermitAll
    @ResponseStatus(value = HttpStatus.OK)
    public void register()
    {
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername("tester1");
        user.setFirstName("First");
        user.setLastName("Last");
        user.setEmail("tom+tester1@tdlabs.local");

        // Get realm
        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersRessource = realmResource.users();

        // Create user (requires manage-users role)
        Response response = usersRessource.create(user);
        System.out.printf("Repsonse: %s %s%n", response.getStatus(), response.getStatusInfo());
        System.out.println(response.getLocation());
        String userId = CreatedResponseUtil.getCreatedId(response);

        System.out.printf("User created with userId: %s%n", userId);

        // Define password credential
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue("test");

        UserResource userResource = usersRessource.get(userId);

        // Set password credential
        userResource.resetPassword(passwordCred);

        keycloak.close();

    }
}
