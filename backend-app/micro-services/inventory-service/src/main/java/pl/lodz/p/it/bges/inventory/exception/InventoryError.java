package pl.lodz.p.it.bges.inventory.exception;

public enum InventoryError {

    TAG_NOT_FOUND("tag.not_found"),
    TAG_EXISTS("tag.exists"),
    BOARD_GAME_NOT_FOUND("boardgame.not_found"),
    STOCK_NEGATIVE("stock.negative"),
    ELEMENT_NOT_FOUND("element.not_found");

    private final String code;

    InventoryError(final String code) {
        this.code = "inventory." + code;
    }

    @Override
    public String toString() {
        return code;
    }
}
