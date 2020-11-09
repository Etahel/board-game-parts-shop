package pl.lodz.p.it.account.controller;

import org.keycloak.KeycloakPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RequestMapping("/user")
@RestController
public class UserController {

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

    }
}
