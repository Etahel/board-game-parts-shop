package pl.lodz.p.it.bges.inventory.exception.boardgame;

import pl.lodz.p.it.bges.inventory.exception.InventoryError;
import pl.lodz.p.it.bges.inventory.exception.InventoryException;

public class PublisherExistsException extends InventoryException {
    public PublisherExistsException() {
        super(InventoryError.PUBLISHER_EXISTS.toString());
    }
}
