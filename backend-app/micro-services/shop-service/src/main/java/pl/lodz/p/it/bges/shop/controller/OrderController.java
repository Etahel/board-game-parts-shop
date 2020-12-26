package pl.lodz.p.it.bges.shop.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.bges.core.definitions.Views;
import pl.lodz.p.it.bges.core.roles.Roles;
import pl.lodz.p.it.bges.shop.criteria.OrderCriteria;
import pl.lodz.p.it.bges.shop.dto.OrderDto;
import pl.lodz.p.it.bges.shop.exception.ShopException;
import pl.lodz.p.it.bges.shop.service.OrderService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/orders")
@RestController
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/me")
    @RolesAllowed(Roles.USER)
    @ResponseStatus(HttpStatus.OK)
    public void createOrder(@JsonView(Views.Modify.class) @Valid @RequestBody OrderDto orderDto,
                            Principal principal) throws ShopException {
        orderService.createOrder(orderDto, principal.getName());
    }

    @GetMapping("/me")
    @RolesAllowed(Roles.USER)
    @JsonView(Views.List.class)
    public Page<OrderDto> getMyOrders(Pageable pageable, Principal principal, @Valid OrderCriteria orderCriteria) {
        return orderService.getClientOrders(principal.getName(), pageable, orderCriteria).map(OrderDto::new);
    }

    @GetMapping("/me/{id}")
    @RolesAllowed(Roles.USER)
    @JsonView(Views.Details.class)
    public OrderDto getMyOrder(Principal principal, @PathVariable("id") Long id) throws ShopException {
        return new OrderDto(orderService.getClientOder(principal.getName(), id));
    }

    @PutMapping("/me/{id}/cancellation")
    @RolesAllowed(Roles.USER)
    @ResponseStatus(HttpStatus.OK)
    public void cancelOrder(Principal principal, @PathVariable("id") Long id) throws ShopException {
        orderService.cancelClientOrder(principal.getName(), id);
    }

    @PutMapping("/{id}")
    @RolesAllowed(Roles.EMPLOYEE)
    @ResponseStatus(HttpStatus.OK)
    public void finalizeOrder(@PathVariable("id") Long id) throws ShopException {
        orderService.finalizeOrder(id);

    }

    @GetMapping
    @RolesAllowed(Roles.EMPLOYEE)
    @JsonView(Views.List.class)
    public Page<OrderDto> getOrders(Pageable pageable, @Valid OrderCriteria orderCriteria) {
        return orderService.getOrders(pageable, orderCriteria).map(OrderDto::new);
    }

    @GetMapping("/{id}")
    @RolesAllowed(Roles.EMPLOYEE)
    @JsonView(Views.Details.class)
    public OrderDto getOrder(@PathVariable("id") Long id) throws ShopException {
        return new OrderDto(orderService.getOrder(id));
    }

    @GetMapping("/client/{clientId}")
    @RolesAllowed(Roles.EMPLOYEE)
    @JsonView(Views.List.class)
    public Page<OrderDto> getClientOrders(@PathVariable("clientId") Long clientId,
                                          @Valid OrderCriteria orderCriteria, Pageable pageable) throws ShopException {
        return orderService.getClientOrders(clientId, pageable, orderCriteria).map(OrderDto::new);
    }

}
