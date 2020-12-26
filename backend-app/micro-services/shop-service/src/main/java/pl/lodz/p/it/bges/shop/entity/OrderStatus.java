package pl.lodz.p.it.bges.shop.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {

    OPEN("O"), CANCELLED("C"), FINALIZED("F");

    private String code;

    private OrderStatus(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return getCode();
    }
}
