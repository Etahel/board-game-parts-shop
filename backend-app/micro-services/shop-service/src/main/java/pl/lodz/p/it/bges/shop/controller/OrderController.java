package pl.lodz.p.it.bges.shop.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.bges.core.roles.Roles;
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

    private OrderService orderService;

    @Autowired
    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/create")
    @RolesAllowed(Roles.USER)
    @JsonView(Views.ClientWithAddress.class)
    @ResponseStatus(HttpStatus.OK)
    public void createOrder(@JsonView(Views.postOrder.class) @Valid @RequestBody OrderDto orderDto,
                            Principal principal) throws ShopException {
        orderService.createOrder(orderDto, principal.getName());
    }

}
