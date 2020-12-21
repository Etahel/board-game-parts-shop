package pl.lodz.p.it.bges.shop.exception.client;

import pl.lodz.p.it.bges.shop.exception.ShopError;
import pl.lodz.p.it.bges.shop.exception.ShopException;

public class ClientNotFoundException extends ShopException {

    public ClientNotFoundException() {
        super(ShopError.CLIENT_NOT_FOUND.toString());
    }
}
