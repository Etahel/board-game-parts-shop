package pl.lodz.p.it.bges.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.bges.shop.criteria.OrderCriteria;
import pl.lodz.p.it.bges.shop.dto.OrderDto;
import pl.lodz.p.it.bges.shop.entity.*;
import pl.lodz.p.it.bges.shop.exception.ShopException;
import pl.lodz.p.it.bges.shop.exception.order.*;
import pl.lodz.p.it.bges.shop.exception.stock.StockInsufficientException;
import pl.lodz.p.it.bges.shop.exception.stock.StockUnavailableException;
import pl.lodz.p.it.bges.shop.repository.ElementRepository;
import pl.lodz.p.it.bges.shop.repository.OrderRepository;
import pl.lodz.p.it.bges.shop.repository.StockRepository;
import pl.lodz.p.it.bges.shop.repository.specification.OrderSpecification;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Transactional(rollbackOn = {RuntimeException.class, ShopException.class})
public class OrderService {

    private ClientService clientService;
    private OrderRepository orderRepository;
    private ElementRepository elementRepository;
    private StockRepository stockRepository;


    @Autowired
    OrderService(ClientService clientService, OrderRepository orderRepository, ElementRepository elementRepository, StockRepository stockRepository) {
        this.clientService = clientService;
        this.orderRepository = orderRepository;
        this.elementRepository = elementRepository;
        this.stockRepository = stockRepository;
    }

    public void createOrder(OrderDto orderDto, String username) throws ShopException {
        Order order = new Order();
        orderDto.putProperties(order);
        order.setClient(clientService.getClient(username));
        order.setDate(LocalDate.now());
        order.setStatus(OrderStatus.OPEN);
        populateOrderElementsAndExecuteTransaction(order);
        orderRepository.save(order);
    }

    public Page<Order> getClientOrders(String username, Pageable pageable, OrderCriteria orderCriteria) {
        return orderRepository.findAll(where(OrderSpecification.getOwnerSpecification(username))
                .and(OrderSpecification.getCriteriaSpecification(orderCriteria)), pageable);
    }

    public Page<Order> getClientOrders(Long clientId, Pageable pageable, OrderCriteria orderCriteria) throws ShopException {
        Client client = clientService.getClient(clientId);
        return orderRepository.findAll(where(OrderSpecification.getOwnerSpecification(client.getUsername()))
                .and(OrderSpecification.getCriteriaSpecification(orderCriteria)), pageable);
    }

    public Page<Order> getOrders(Pageable pageable, OrderCriteria orderCriteria) {
        return orderRepository.findAll(OrderSpecification.getCriteriaSpecification(orderCriteria), pageable);
    }

    public Order getClientOder(String username, Long id) throws ShopException {
        Optional<Order> optOrder = orderRepository.findByIdAndClientUsername(id, username);
        if (optOrder.isPresent()) {
            return optOrder.get();
        } else {
            throw new OrderNotFoundException();
        }
    }

    public Order getOrder(Long id) throws ShopException {
        Optional<Order> optOrder = orderRepository.findById(id);
        if (optOrder.isPresent()) {
            return optOrder.get();
        } else {
            throw new OrderNotFoundException();
        }
    }

    public void cancelClientOrder(String username, Long id) throws ShopException {
        Order order = getClientOder(username, id);
        if (order.getStatus().equals(OrderStatus.FINALIZED)) {
            throw new OrderFinalizedException();
        } else {
            order.setStatus(OrderStatus.CANCELLED);
        }
        for (OrderItem orderItem : order.getOrderItems()) {
            Optional<Element> elementOpt = elementRepository.findById(orderItem.getElementId());
            if (elementOpt.isPresent()) {
                Element element = elementOpt.get();
                element.getStock().setStockSize(element.getStock().getStockSize() + orderItem.getElementsCount());
            } else {
                throw new IllegalStateException("Unable to retrieve element associated with order");
            }
        }
    }

    public void finalizeOrder(Long id) throws ShopException {
        Order order = getOrder(id);
        if (order.getStatus().equals(OrderStatus.FINALIZED)) {
            throw new OrderCancelledException();
        } else {
            order.setStatus(OrderStatus.FINALIZED);
        }
    }


    private void populateOrderElementsAndExecuteTransaction(Order order) throws ShopException {
        double value = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            Optional<Element> elementOpt = elementRepository.findById(orderItem.getElementId());
            if (elementOpt.isPresent()) {
                Element element = elementOpt.get();
                checkElementVersion(orderItem, element);
                checkStock(orderItem, element.getStock());
                element.getStock().setStockSize(element.getStock().getStockSize() - orderItem.getElementsCount());
                value += element.getPrice() * orderItem.getElementsCount();
                orderItem.setElement(element);
            } else {
                throw new ElementNotFoundException();
            }
        }
        order.setValue(value);
    }

    private void checkElementVersion(OrderItem orderItem, Element element) throws ShopException {
        if (!element.getVersion().equals(orderItem.getElementVersion())) {
            throw new ElementChangedException();
        }
    }

    private void checkStock(OrderItem orderItem, Stock stock) throws ShopException {
        if (!stock.getAvailable()) {
            throw new StockUnavailableException();
        } else if (stock.getStockSize() < orderItem.getElementsCount()) {
            throw new StockInsufficientException();
        }
    }


}
