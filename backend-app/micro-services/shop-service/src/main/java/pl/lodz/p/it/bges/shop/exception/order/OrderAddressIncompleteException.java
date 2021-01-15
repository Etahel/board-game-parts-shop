package pl.lodz.p.it.bges.shop.exception.order;

import pl.lodz.p.it.bges.shop.exception.ShopError;
import pl.lodz.p.it.bges.shop.exception.ShopException;

public class OrderAddressIncompleteException extends ShopException {
    public OrderAddressIncompleteException() {
        super(ShopError.ORDER_ADDRESS_INCOMPLETE.toString());
    }
}
