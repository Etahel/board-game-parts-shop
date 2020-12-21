package pl.lodz.p.it.bges.inventory.exception.element;

import pl.lodz.p.it.bges.inventory.exception.InventoryError;
import pl.lodz.p.it.bges.inventory.exception.InventoryException;

public class NegativeStockException extends InventoryException {
    public NegativeStockException() {
        super(InventoryError.STOCK_NEGATIVE.toString());
    }
}
