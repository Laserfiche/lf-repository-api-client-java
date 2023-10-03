package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumeration for the image types when generating pages.
 */
public enum GeneratePagesImageType {

    BLACK_AND_WHITE("BlackAndWhite"), STANDARD_COLOR("StandardColor"), HIGH_QUALITY_COLOR("HighQualityColor");

    private final String value;

    GeneratePagesImageType(String value) {
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
    public static GeneratePagesImageType fromValue(String input) {
        for (GeneratePagesImageType b : GeneratePagesImageType.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }
}
