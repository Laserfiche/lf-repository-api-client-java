package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EntryType {

    FOLDER("Folder"),
    RECORDSERIES("RecordSeries"),
    DOCUMENT("Document"),
    SHORTCUT("Shortcut");

    private String value;

    EntryType(String value) {
        this.value = value;
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

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
