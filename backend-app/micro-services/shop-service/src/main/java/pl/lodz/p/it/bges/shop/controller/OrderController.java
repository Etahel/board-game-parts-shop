package pl.lodz.p.it.bges.shop.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.it.bges.core.roles.Roles;
import pl.lodz.p.it.bges.shop.dto.OrderDto;
import pl.lodz.p.it.bges.shop.dto.Views;
import pl.lodz.p.it.bges.shop.repository.OrderRepository;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RequestMapping("/order")
@RestController
public class OrderController {

    private OrderRepository orderRepository;

    @Autowired
    OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping(path = "/create")
    @RolesAllowed(Roles.USER)
    @JsonView(Views.ClientWithAddress.class)
    @ResponseStatus(HttpStatus.OK)
    public void createOrder(OrderDto orderDto, Principal principal) {

    }

}
