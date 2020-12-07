package pl.lodz.p.it.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.it.shop.common.Roles;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RequestMapping("/client")
@RestController
public class ClientController {

    @GetMapping(path = "/test")
    @RolesAllowed(Roles.USER)
    public String test(Principal principal) {
        return principal.getName();
    }
}
