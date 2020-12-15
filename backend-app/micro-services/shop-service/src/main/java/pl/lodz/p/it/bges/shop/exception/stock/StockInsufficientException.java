package pl.lodz.p.it.bges.shop.exception.stock;

import pl.lodz.p.it.bges.shop.exception.ShopError;
import pl.lodz.p.it.bges.shop.exception.ShopException;

public class StockInsufficientException extends ShopException {
    public StockInsufficientException() {
        super(ShopError.ORDER_STOCK_INSUFFICIENT.toString());
    }
}
