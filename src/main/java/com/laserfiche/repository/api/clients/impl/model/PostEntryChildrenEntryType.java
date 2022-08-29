package com.laserfiche.repository.api.clients.impl.model;

public enum PostEntryChildrenEntryType {
    
    FOLDER("Folder"),
    SHORTCUT("Shortcut");

    public String value;

    PostEntryChildrenEntryType(String value) {
        this.value = value;
    }
}
