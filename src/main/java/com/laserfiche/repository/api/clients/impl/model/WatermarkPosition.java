package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum WatermarkPosition {

    TOPLEFT("TopLeft"),
    TOPCENTER("TopCenter"),
    TOPRIGHT("TopRight"),
    MIDDLELEFT("MiddleLeft"),
    DEADCENTER("DeadCenter"),
    MIDDLERIGHT("MiddleRight"),
    BOTTOMLEFT("BottomLeft"),
    BOTTOMCENTER("BottomCenter"),
    BOTTOMRIGHT("BottomRight");

    private String value;

    WatermarkPosition(String value) {
        this.value = value;
    }

    @JsonCreator
    public static WatermarkPosition fromValue(String input) {
        for (WatermarkPosition b : WatermarkPosition.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
