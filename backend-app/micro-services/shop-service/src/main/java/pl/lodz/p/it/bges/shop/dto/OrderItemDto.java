package pl.lodz.p.it.bges.shop.dto;

import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.bges.shop.entity.OrderItem;

import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

@Setter
@Getter
public class OrderItemDto extends ShopDto<OrderItem> {

    @NotEmpty
    private Long elementId;

    private Integer elementsCount;

    private BigInteger elementVersion;

    OrderItemDto() {
    }

    ;

    OrderItemDto(OrderItem orderItem) {
        super(orderItem);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void fillProperties(OrderItem entity) {
        super.fillProperties(entity);
        setElementId(entity.getElementId());
        setElementsCount(entity.getElementsCount());
    }

    @Override
    public void putProperties(OrderItem entity) {
        super.putProperties(entity);
        entity.setElementId(getElementId());
        entity.setElementsCount(getElementsCount());
    }

    @Override
    public void patchProperties(OrderItem entity) {
        super.patchProperties(entity);
    }
}


