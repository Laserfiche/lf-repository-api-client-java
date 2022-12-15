package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.*;

/**
 * Enumeration of Laserfiche template field types.
 */
public enum WFieldType {

    DATE_TIME("DateTime"),
    BLOB("Blob"),
    DATE("Date"),
    SHORT_INTEGER("ShortInteger"),
    LONG_INTEGER("LongInteger"),
    LIST("List"),
    NUMBER("Number"),
    STRING("String"),
    TIME("Time");

    private String value;

    WFieldType(String value) {
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
    public static WFieldType fromValue(String input) {
        for (WFieldType b : WFieldType.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }
}
