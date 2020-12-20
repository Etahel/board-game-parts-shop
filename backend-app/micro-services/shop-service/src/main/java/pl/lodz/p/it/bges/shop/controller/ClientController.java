package pl.lodz.p.it.bges.shop.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.bges.core.roles.Roles;
import pl.lodz.p.it.bges.shop.dto.ClientDto;
import pl.lodz.p.it.bges.shop.dto.OrderDto;
import pl.lodz.p.it.bges.shop.dto.Views;
import pl.lodz.p.it.bges.shop.exception.ShopException;
import pl.lodz.p.it.bges.shop.service.ClientService;
import pl.lodz.p.it.bges.shop.service.OrderService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/client")
@RestController
public class ClientController {

    private ClientService clientService;
    private OrderService orderService;

    @Autowired
    ClientController(ClientService clientService, OrderService orderService) {
        this.orderService = orderService;
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

    @PostMapping("/me/orders")
    @RolesAllowed(Roles.USER)
    @ResponseStatus(HttpStatus.OK)
    public void createOrder(@JsonView(Views.Modify.class) @Valid @RequestBody OrderDto orderDto,
                            Principal principal) throws ShopException {
        orderService.createOrder(orderDto, principal.getName());
    }

    @GetMapping("/me/orders")
    @RolesAllowed(Roles.USER)
    @JsonView(Views.List.class)
    public Page<OrderDto> getMyOrders(Pageable pageable, Principal principal) {
        return orderService.getClientOrders(principal.getName(), pageable).map(OrderDto::new);
    }

    @GetMapping("/me/orders/{id}")
    @RolesAllowed(Roles.USER)
    @JsonView(Views.Details.class)
    public OrderDto getMyOrder(Principal principal, @PathVariable("id") Long id) throws ShopException {
        return new OrderDto(orderService.getClientOder(principal.getName(), id));
    }

    @PutMapping("/me/orders/{id}/cancellation")
    @RolesAllowed(Roles.USER)
    @ResponseStatus(HttpStatus.OK)
    public void cancelOrder(Principal principal, @PathVariable("id") Long id) throws ShopException {
        orderService.cancelClientOrder(principal.getName(), id);
    }


}
