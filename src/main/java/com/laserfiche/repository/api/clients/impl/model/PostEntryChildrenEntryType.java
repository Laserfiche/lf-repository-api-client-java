package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PostEntryChildrenEntryType {
    
    FOLDER("Folder"),
    SHORTCUT("Shortcut");

    public String value;

    PostEntryChildrenEntryType(String value) {
        this.value = value;
    }
}
