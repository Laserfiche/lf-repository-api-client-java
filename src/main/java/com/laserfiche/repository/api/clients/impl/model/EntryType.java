package com.laserfiche.repository.api.clients.impl.model;

public enum EntryType {
    
    FOLDER("Folder"),
    RECORDSERIES("RecordSeries"),
    DOCUMENT("Document"),
    SHORTCUT("Shortcut");

    public String value;

    EntryType(String value) {
        this.value = value;
    }
}
