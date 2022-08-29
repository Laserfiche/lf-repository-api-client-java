package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

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
