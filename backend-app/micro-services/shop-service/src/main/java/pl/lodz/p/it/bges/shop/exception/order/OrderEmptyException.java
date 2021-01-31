package pl.lodz.p.it.bges.shop.exception.order;

import pl.lodz.p.it.bges.shop.exception.ShopError;
import pl.lodz.p.it.bges.shop.exception.ShopException;

public class OrderEmptyException extends ShopException {
    public OrderEmptyException() {
        super(ShopError.ORDER_EMPTY.toString());
    }
}
