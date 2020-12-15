package pl.lodz.p.it.bges.shop.exception.stock;

import pl.lodz.p.it.bges.shop.exception.ShopError;
import pl.lodz.p.it.bges.shop.exception.ShopException;

public class StockUnavailableException extends ShopException {
    public StockUnavailableException() {
        super(ShopError.ORDER_STOCK_UNAVAILABLE.toString());
    }
}
