package pl.lodz.p.it.bges.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.bges.core.definitions.OrderStatus;
import pl.lodz.p.it.bges.shop.entity.Order;
import pl.lodz.p.it.bges.shop.entity.OrderItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderDto extends ShopDto<Order> {

    @JsonView(Views.Basic.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate date;
    @JsonView(Views.Internal.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ClientDto client;
    @JsonView(Views.Basic.class)
    private String orderFirstName;
    @JsonView(Views.Basic.class)
    private String orderLastName;
    @JsonView(Views.Normal.class)
    private AddressDto address;
    @JsonView(Views.Normal.class)
    private List<OrderItemDto> orderItems;
    @JsonView(Views.Basic.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OrderStatus status;
    @JsonView(Views.Basic.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double value;


    public OrderDto(Order order) {
        super(order);
    }

    public OrderDto() {

    }

    @Override
    public void fillProperties(Order entity) {
        super.fillProperties(entity);
        setDate(entity.getDate());
        setOrderFirstName(entity.getOrderFirstName());
        setOrderLastName(entity.getOrderLastName());
        setStatus(entity.getStatus());
        setValue(entity.getValue());
        if (entity.getClient() != null) {
            setClient(new ClientDto(entity.getClient()));
        }
        if (entity.getAddress() != null) {
            setAddress(new AddressDto(entity.getAddress()));
        }
        if (entity.getOrderItems() != null) {
            orderItems = new ArrayList<>();
            for (OrderItem orderItem : entity.getOrderItems()) {
                getOrderItems().add(new OrderItemDto(orderItem));
            }
        }

    }

    @Override
    public void putProperties(Order entity) {
        super.putProperties(entity);
        entity.setOrderFirstName(getOrderFirstName());
        entity.setOrderLastName(getOrderLastName());
        if (getAddress() != null) {
            getAddress().putProperties(entity.getAddress());
        }
        if (getOrderItems() != null) {
            entity.setOrderItems(new ArrayList<>());
            for (OrderItemDto orderItemDto : getOrderItems()) {
                OrderItem orderItem = new OrderItem();
                orderItemDto.putProperties(orderItem);
                entity.getOrderItems().add(orderItem);
            }
        }
    }

    @Override
    public void patchProperties(Order entity) {
        super.patchProperties(entity);
    }
}
