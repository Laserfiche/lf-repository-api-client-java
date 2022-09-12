package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EntryType {

    @JsonProperty("Folder")
    FOLDER("Folder"),
    @JsonProperty("RecordSeries")
    RECORDSERIES("RecordSeries"),
    @JsonProperty("Document")
    DOCUMENT("Document"),
    @JsonProperty("Shortcut")
    SHORTCUT("Shortcut");

    public String value;

    EntryType(String value) {
        this.value = value;
    }
}
