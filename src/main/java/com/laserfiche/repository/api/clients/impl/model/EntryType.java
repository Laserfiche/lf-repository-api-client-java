package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.*;

/**
 * Gets or Sets EntryType
 */
public enum EntryType {

    FOLDER("Folder"), RECORD_SERIES("RecordSeries"), DOCUMENT("Document"), SHORTCUT("Shortcut");

    private String value;

    EntryType(String value) {
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
    public static EntryType fromValue(String input) {
        for (EntryType b : EntryType.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }
}
