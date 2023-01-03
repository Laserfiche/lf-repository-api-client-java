package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
/**
 * Gets or Sets PostEntryChildrenEntryType
 */
public enum PostEntryChildrenEntryType {

    FOLDER("Folder"), SHORTCUT("Shortcut");

    private String value;

    PostEntryChildrenEntryType(String value) {
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
    public static PostEntryChildrenEntryType fromValue(String input) {
        for (PostEntryChildrenEntryType b : PostEntryChildrenEntryType.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }
}
