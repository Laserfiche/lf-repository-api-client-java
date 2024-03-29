// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * An enumeration of possible types for a long operation task.
 */
public enum TaskType {

    COPY_ENTRY("CopyEntry"), DELETE_ENTRY("DeleteEntry"), EXPORT_ENTRY("ExportEntry"), IMPORT_UPLOADED_PARTS("ImportUploadedParts"), SEARCH_ENTRY("SearchEntry");

    private final String value;

    TaskType(String value) {
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
    public static TaskType fromValue(String input) {
        for (TaskType b : TaskType.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }
}
