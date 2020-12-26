package pl.lodz.p.it.bges.shop.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.bges.core.definitions.Views;
import pl.lodz.p.it.bges.shop.entity.OrderItem;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Setter
@Getter
public class OrderItemDto extends ShopDto<OrderItem> {

    @NotNull
    @JsonView(Views.Basic.class)
    private Long elementId;

    @NotNull
    @JsonView(Views.Basic.class)
    @Min(1)
    @Max(1000)
    private Integer elementsCount;

    @JsonView(Views.Basic.class)
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
        entity.setElementVersion(decodeVersion(getElementVersion()));
    }

    @Override
    public void patchProperties(OrderItem entity) {
        super.patchProperties(entity);
    }
}


