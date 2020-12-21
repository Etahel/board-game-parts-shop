package pl.lodz.p.it.bges.inventory.exception.element;

import pl.lodz.p.it.bges.inventory.exception.InventoryError;
import pl.lodz.p.it.bges.inventory.exception.InventoryException;

public class ElementNotFoundException extends InventoryException {
    public ElementNotFoundException() {
        super(InventoryError.ELEMENT_NOT_FOUND.toString());
    }
}
