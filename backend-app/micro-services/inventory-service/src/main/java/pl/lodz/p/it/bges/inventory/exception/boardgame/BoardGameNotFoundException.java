package pl.lodz.p.it.bges.inventory.exception.boardgame;

import pl.lodz.p.it.bges.inventory.exception.InventoryError;
import pl.lodz.p.it.bges.inventory.exception.InventoryException;

public class BoardGameNotFoundException extends InventoryException {
    public BoardGameNotFoundException() {
        super(InventoryError.BOARD_GAME_NOT_FOUND.toString());
    }
}
