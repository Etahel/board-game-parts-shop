package pl.lodz.p.it.bges.shop.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.bges.core.roles.Roles;
import pl.lodz.p.it.bges.shop.criteria.OrderCriteria;
import pl.lodz.p.it.bges.shop.dto.OrderDto;
import pl.lodz.p.it.bges.shop.dto.Views;
import pl.lodz.p.it.bges.shop.exception.ShopException;
import pl.lodz.p.it.bges.shop.service.OrderService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

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
    public Page<OrderDto> getMyOrders(Pageable pageable, Principal principal, OrderCriteria orderCriteria) {
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

//    @PutMapping("/{id}")
//    @RolesAllowed(Roles.EMPLOYEE)
//    @ResponseStatus(HttpStatus.OK)
//    public void finalizeOrder(@PathVariable("id") Long id) throws ShopException {
//
//    }
//
//    @GetMapping
//    @RolesAllowed(Roles.EMPLOYEE)
//    public Page<OrderDto> getOrders(Pageable pageable) {
//
//    }
//
//    @GetMapping("/{id}")
//    @RolesAllowed(Roles.EMPLOYEE)
//    public OrderDto getOrder(@PathVariable("id") Long id) throws ShopException {
//
//    }
//
//    @GetMapping("/client/{clientId}")
//    @RolesAllowed(Roles.EMPLOYEE)
//    public Page<OrderDto> getClientOrders(@PathVariable("clientId") Long clientId, Pageable pageable) {
//
//    }

}
