package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum WFieldFormat {

    NONE("None"),
    SHORTDATE("ShortDate"),
    LONGDATE("LongDate"),
    SHORTDATETIME("ShortDateTime"),
    LONGDATETIME("LongDateTime"),
    SHORTTIME("ShortTime"),
    LONGTIME("LongTime"),
    GENERALNUMBER("GeneralNumber"),
    CURRENCY("Currency"),
    PERCENT("Percent"),
    SCIENTIFIC("Scientific"),
    CUSTOM("Custom");

    private String value;

    WFieldFormat(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static WFieldFormat fromValue(String input) {
        for (WFieldFormat b : WFieldFormat.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }
}
