package pl.lodz.p.it.bges.shop.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.bges.core.roles.Roles;
import pl.lodz.p.it.bges.shop.dto.ClientDto;
import pl.lodz.p.it.bges.shop.dto.Views;
import pl.lodz.p.it.bges.shop.service.ClientService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
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
    public String test() {
        return "hello";

    }

    @GetMapping(path = "/me")
    @RolesAllowed(Roles.USER)
    @JsonView(Views.getClient.class)
    public ClientDto getMe(Principal principal) {
        return new ClientDto(clientService.getClient(principal.getName()));
    }

    @PatchMapping(path = "/me")
    @RolesAllowed(Roles.USER)
    @JsonView(Views.patchClient.class)
    @ResponseStatus(HttpStatus.OK)
    public void patchMe(@RequestBody @Valid @JsonView(Views.patchClient.class) ClientDto clientDto, Principal principal) {
        System.out.println("HELLO");
        clientService.updateClient(clientDto, principal.getName());
    }


}
