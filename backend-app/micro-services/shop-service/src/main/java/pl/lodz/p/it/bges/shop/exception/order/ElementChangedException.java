package pl.lodz.p.it.bges.shop.exception.order;

import pl.lodz.p.it.bges.shop.exception.ShopError;
import pl.lodz.p.it.bges.shop.exception.ShopException;

public class ElementChangedException extends ShopException {
    public ElementChangedException() {
        super(ShopError.ORDER_ELEMENT_CHANGED.toString());
    }
}
