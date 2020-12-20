package pl.lodz.p.it.bges.shop.exception.order;

import pl.lodz.p.it.bges.shop.exception.ShopError;
import pl.lodz.p.it.bges.shop.exception.ShopException;

public class OrderCancelledException extends ShopException {

    public OrderCancelledException() {
        super(ShopError.ORDER_CANCELLED.toString());
    }
}
