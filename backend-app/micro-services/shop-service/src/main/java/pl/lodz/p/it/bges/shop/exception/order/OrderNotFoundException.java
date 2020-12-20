package pl.lodz.p.it.bges.shop.exception.order;

import pl.lodz.p.it.bges.shop.exception.ShopError;
import pl.lodz.p.it.bges.shop.exception.ShopException;

public class OrderNotFoundException extends ShopException {
    public OrderNotFoundException() {
        super(ShopError.ORDER_NOT_FOUND.toString());
    }
}
