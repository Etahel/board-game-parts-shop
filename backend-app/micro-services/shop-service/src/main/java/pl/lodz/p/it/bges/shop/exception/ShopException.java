package pl.lodz.p.it.bges.shop.exception;

import pl.lodz.p.it.bges.core.exception.AppException;

public class ShopException extends AppException {
    ShopException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShopException(String message) {
        super(message);
    }
}
