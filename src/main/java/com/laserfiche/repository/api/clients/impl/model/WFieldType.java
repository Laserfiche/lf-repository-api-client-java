package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum WFieldType {
    
    DATETIME("DateTime"),
    BLOB("Blob"),
    DATE("Date"),
    SHORTINTEGER("ShortInteger"),
    LONGINTEGER("LongInteger"),
    LIST("List"),
    NUMBER("Number"),
    STRING("String"),
    TIME("Time");

    public String value;

    WFieldType(String value) {
        this.value = value;
    }
}
