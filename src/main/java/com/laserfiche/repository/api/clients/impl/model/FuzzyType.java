package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Gets or Sets FuzzyType */
public enum FuzzyType {
    NONE("None"),
    PERCENTAGE("Percentage"),
    NUMBER_OF_LETTERS("NumberOfLetters");

    private String value;

    FuzzyType(String value) {
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
    public static FuzzyType fromValue(String input) {
        for (FuzzyType b : FuzzyType.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }
}
