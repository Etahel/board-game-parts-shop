package pl.lodz.p.it.bges.shop.criteria;

import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.bges.shop.entity.OrderStatus;

@Setter
@Getter
public class OrderCriteria extends ShopCriteria {

    private OrderStatus status;

}
