package pl.lodz.p.it.bges.shop.exception;

public enum ShopError {

    //

    ORDER_ELEMENT_NOT_FOUND("order.element_not_found"),
    ORDER_ELEMENT_CHANGED("order.element_changed"),
    ORDER_STOCK_UNAVAILABLE("order.stock_unavailable"),
    ORDER_STOCK_INSUFFICIENT("order.stock_insufficient");


    private final String code;

    ShopError(final String code) {
        this.code = "shop." + code;
    }

    @Override
    public String toString() {
        return code;
    }

}
