package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumeration of Laserfiche template field types.
 */
public enum FieldType {

    DATE_TIME("DateTime"),
    BLOB("Blob"),
    DATE("Date"),
    SHORT_INTEGER("ShortInteger"),
    LONG_INTEGER("LongInteger"),
    LIST("List"),
    NUMBER("Number"),
    STRING("String"),
    TIME("Time");

    private final String value;

    FieldType(String value) {
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
    public static FieldType fromValue(String input) {
        for (FieldType b : FieldType.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }
}
