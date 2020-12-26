package pl.lodz.p.it.bges.inventory.exception.boardgame;

import pl.lodz.p.it.bges.inventory.exception.InventoryError;
import pl.lodz.p.it.bges.inventory.exception.InventoryException;

public class TagExistsException extends InventoryException {
    public TagExistsException() {
        super(InventoryError.TAG_EXISTS.toString());
    }
}
