package pl.lodz.p.it.bges.core.definitions;

public enum OrderStatus {

    OPEN("O"), CANCELLED("C"), FINALIZED("F");

    private String code;

    private OrderStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
