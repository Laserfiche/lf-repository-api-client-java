// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumeration of Laserfiche template field formats.
 */
public enum FieldFormat {

    NONE("None"),
    SHORT_DATE("ShortDate"),
    LONG_DATE("LongDate"),
    SHORT_DATE_TIME("ShortDateTime"),
    LONG_DATE_TIME("LongDateTime"),
    SHORT_TIME("ShortTime"),
    LONG_TIME("LongTime"),
    GENERAL_NUMBER("GeneralNumber"),
    CURRENCY("Currency"),
    PERCENT("Percent"),
    SCIENTIFIC("Scientific"),
    CUSTOM("Custom");

    private final String value;

    FieldFormat(String value) {
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
    public static FieldFormat fromValue(String input) {
        for (FieldFormat b : FieldFormat.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }
}
