package pl.lodz.p.it.bges.shop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.bges.core.definitions.Views;
import pl.lodz.p.it.bges.shop.dto.serializer.BasicSerializer;
import pl.lodz.p.it.bges.shop.entity.Order;
import pl.lodz.p.it.bges.shop.entity.OrderItem;
import pl.lodz.p.it.bges.shop.entity.OrderStatus;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderDto extends ShopDto<Order> {

    @JsonView(Views.Basic.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    @JsonView(Views.Normal.class)
    @JsonSerialize(using = BasicSerializer.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ClientDto client;
    @JsonView(Views.Basic.class)
    @Size(max = 30)
    private String firstName;
    @JsonView(Views.Basic.class)
    @Size(max = 30)
    private String lastName;
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
        setFirstName(entity.getOrderFirstName());
        setLastName(entity.getOrderLastName());
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
        entity.setOrderFirstName(getFirstName());
        entity.setOrderLastName(getLastName());
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
