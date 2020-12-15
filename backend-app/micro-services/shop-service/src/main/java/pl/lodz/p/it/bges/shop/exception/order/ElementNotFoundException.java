package pl.lodz.p.it.bges.shop.exception.order;

import pl.lodz.p.it.bges.shop.exception.ShopError;
import pl.lodz.p.it.bges.shop.exception.ShopException;

public class ElementNotFoundException extends ShopException {
    public ElementNotFoundException() {
        super(ShopError.ORDER_ELEMENT_NOT_FOUND.toString());
    }
}
