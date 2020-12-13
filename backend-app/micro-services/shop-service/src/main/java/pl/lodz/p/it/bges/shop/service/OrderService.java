package pl.lodz.p.it.bges.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.bges.core.definitions.OrderStatus;
import pl.lodz.p.it.bges.shop.dto.OrderDto;
import pl.lodz.p.it.bges.shop.entity.Order;
import pl.lodz.p.it.bges.shop.entity.OrderItem;
import pl.lodz.p.it.bges.shop.repository.OrderRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(rollbackOn = {RuntimeException.class})
public class OrderService {

    private ClientService clientService;
    private OrderRepository orderRepository;


    @Autowired
    OrderService(ClientService clientService, OrderRepository orderRepository) {
        this.clientService = clientService;
        this.orderRepository = orderRepository;
    }

    public void createOrder(OrderDto orderDto, String username) {
        Order order = new Order();
        orderDto.putProperties(order);
        order.setClient(clientService.getClient(username));
        order.setDate(LocalDate.now());
        order.setStatus(OrderStatus.OPEN);
        orderRepository.save(order);
    }

    private int calculateOrderValue(List<OrderItem> orderItems) {
        return 500;
    }
}
