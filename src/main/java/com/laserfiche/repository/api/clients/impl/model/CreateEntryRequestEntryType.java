package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.*;

/**
 * Enumeration of entry types for CreateEntry.
 */
public enum CreateEntryRequestEntryType {

    FOLDER("Folder"), SHORTCUT("Shortcut");

    private String value;

    CreateEntryRequestEntryType(String value) {
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
    public static CreateEntryRequestEntryType fromValue(String input) {
        for (CreateEntryRequestEntryType b : CreateEntryRequestEntryType.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }
}
