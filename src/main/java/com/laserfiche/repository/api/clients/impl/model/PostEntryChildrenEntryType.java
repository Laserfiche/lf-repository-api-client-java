package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum PostEntryChildrenEntryType {
    
    FOLDER("Folder"),
    SHORTCUT("Shortcut");

    private String value;

    PostEntryChildrenEntryType(String value) {
        this.value = value;
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

    @JsonValue
public String getValue() {
    return value;
}

    @Override
public String toString() {
    return String.valueOf(value);
}
}
