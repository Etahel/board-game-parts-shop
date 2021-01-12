package pl.lodz.p.it.bges.inventory.exception.boardgame;

import pl.lodz.p.it.bges.inventory.exception.InventoryError;
import pl.lodz.p.it.bges.inventory.exception.InventoryException;

public class PublisherNotFoundException extends InventoryException {
    public PublisherNotFoundException() {
        super(InventoryError.PUBLISHER_NOT_FOUND.toString());
    }
}
