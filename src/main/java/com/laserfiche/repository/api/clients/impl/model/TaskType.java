package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.*;

/**
 * An enumeration of possible types for a long operation task.
 */
public enum TaskType {

    COPY_ENTRY("CopyEntry"), DELETE_ENTRY("DeleteEntry"), EXPORT_ENTRY("ExportEntry"), IMPORT_UPLOADED_PARTS("ImportUploadedParts"), SEARCH_ENTRY("SearchEntry");

    private String value;

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
