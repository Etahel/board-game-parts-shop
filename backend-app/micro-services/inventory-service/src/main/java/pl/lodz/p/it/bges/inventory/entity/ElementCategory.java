package pl.lodz.p.it.bges.inventory.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ElementCategory {
    TOKENS_AND_MARKERS("T"),
    BOARDS_BOXES_AND_CONTAINERS("B"),
    CARDS("C"),
    PIECES_AND_FIGURES("P"),
    DICES("D"),
    INSTRUCTIONS_AND_INFOGRAPHICS("I"),
    OTHER_ELEMENTS("O");

    private String code;

    private ElementCategory(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
