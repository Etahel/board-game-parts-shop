package pl.lodz.p.it.bges.shop.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.bges.core.roles.Roles;
import pl.lodz.p.it.bges.shop.criteria.ClientCriteria;
import pl.lodz.p.it.bges.shop.dto.ClientDto;
import pl.lodz.p.it.bges.shop.dto.Views;
import pl.lodz.p.it.bges.shop.exception.ShopException;
import pl.lodz.p.it.bges.shop.service.ClientService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/clients")
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
    @JsonView(Views.Details.class)
    public ClientDto getMe(Principal principal) {
        return new ClientDto(clientService.getClient(principal.getName()));
    }

    @PatchMapping(path = "/me")
    @RolesAllowed(Roles.USER)
    @ResponseStatus(HttpStatus.OK)
    public void patchMe(@RequestBody @Valid @JsonView(Views.Modify.class) ClientDto clientDto, Principal principal) {
        clientService.updateClient(clientDto, principal.getName());
    }

    @GetMapping
    @RolesAllowed(Roles.EMPLOYEE)
    @JsonView(Views.List.class)
    public Page<ClientDto> getClients(Pageable pageable, ClientCriteria clientCriteria) {
        return clientService.getClients(pageable, clientCriteria).map(ClientDto::new);
    }

    @GetMapping("/{id}")
    @RolesAllowed(Roles.EMPLOYEE)
    @JsonView(Views.List.class)
    public ClientDto getClient(@PathVariable Long id) throws ShopException {
        return new ClientDto(clientService.getClient(id));
    }


}
