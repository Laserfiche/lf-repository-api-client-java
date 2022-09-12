package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PostEntryChildrenEntryType {

    @JsonProperty("Folder")
    FOLDER("Folder"),
    @JsonProperty("Shortcut")
    SHORTCUT("Shortcut");

    public String value;

    PostEntryChildrenEntryType(String value) {
        this.value = value;
    }
}
