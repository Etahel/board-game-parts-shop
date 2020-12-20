package pl.lodz.p.it.bges.shop.exception.order;

import pl.lodz.p.it.bges.shop.exception.ShopError;
import pl.lodz.p.it.bges.shop.exception.ShopException;

public class OrderFinalizedException extends ShopException {
    public OrderFinalizedException() {
        super(ShopError.ORDER_FINALIZED.toString());
    }
}
