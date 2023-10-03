package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.*;

/**
 * Enumeration of the entry parts to export.
 */
public enum ExportEntryRequestPart {
    IMAGE("Image"),
    TEXT("Text"),
    EDOC("Edoc");

    private String value;

    ExportEntryRequestPart(String value) {
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
    public static ExportEntryRequestPart fromValue(String input) {
        for (ExportEntryRequestPart b : ExportEntryRequestPart.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }
}
