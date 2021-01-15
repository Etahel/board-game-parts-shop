package pl.lodz.p.it.bges.shop.exception;

public enum ShopError {

    //CLIENT
    CLIENT_NOT_FOUND("client.not_found"),
    //ORDER
    ORDER_NOT_FOUND("order.not_found"),
    ORDER_ELEMENT_NOT_FOUND("order.element_not_found"),
    ORDER_ELEMENT_CHANGED("order.element_changed"),
    ORDER_CANCELLED("order.cancelled"),
    ORDER_FINALIZED("order.finalized"),
    ORDER_STOCK_UNAVAILABLE("order.stock_unavailable"),
    ORDER_STOCK_INSUFFICIENT("order.stock_insufficient"),
    ORDER_ADDRESS_INCOMPLETE("order.address_incomplete"),
    ORDER_EMPTY("order.empty");


    private final String code;

    ShopError(final String code) {
        this.code = "shop." + code;
    }

    @Override
    public String toString() {
        return code;
    }

}
