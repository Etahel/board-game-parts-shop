package pl.lodz.p.it.bges.inventory.exception.boardgame;

import pl.lodz.p.it.bges.inventory.exception.InventoryError;
import pl.lodz.p.it.bges.inventory.exception.InventoryException;

public class TagNotFoundException extends InventoryException {
    public TagNotFoundException() {
        super(InventoryError.TAG_NOT_FOUND.toString());
    }
}
