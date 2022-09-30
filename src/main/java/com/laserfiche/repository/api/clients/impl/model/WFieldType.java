package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum WFieldType {

    DATETIME("DateTime"),
    BLOB("Blob"),
    DATE("Date"),
    SHORTINTEGER("ShortInteger"),
    LONGINTEGER("LongInteger"),
    LIST("List"),
    NUMBER("Number"),
    STRING("String"),
    TIME("Time");

    private String value;

    WFieldType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static WFieldType fromValue(String input) {
        for (WFieldType b : WFieldType.values()) {
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
