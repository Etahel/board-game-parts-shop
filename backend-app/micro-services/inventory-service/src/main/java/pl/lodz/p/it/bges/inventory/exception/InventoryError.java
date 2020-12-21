package pl.lodz.p.it.bges.inventory.exception;

public enum InventoryError {

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
