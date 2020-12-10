package pl.lodz.p.it.bges.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.it.bges.shop.common.Roles;
import pl.lodz.p.it.bges.shop.dto.ClientDto;
import pl.lodz.p.it.bges.shop.service.ClientService;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RequestMapping("/client")
@RestController
public class ClientController {

    private ClientService clientService;

    @Autowired
    ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(path = "/test")
    @RolesAllowed(Roles.USER)
    public String test(Principal principal) {
        return principal.getName();
    }

    @GetMapping(path = "/me")
    @RolesAllowed(Roles.USER)
    public ClientDto getMe(Principal principal) {
        return new ClientDto(clientService.getClient(principal.getName()));
    }
}
