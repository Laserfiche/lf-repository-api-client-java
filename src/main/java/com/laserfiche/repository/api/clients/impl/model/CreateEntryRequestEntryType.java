// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumeration of entry types for CreateEntry.
 */
public enum CreateEntryRequestEntryType {

    FOLDER("Folder"), SHORTCUT("Shortcut");

    private final String value;

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
