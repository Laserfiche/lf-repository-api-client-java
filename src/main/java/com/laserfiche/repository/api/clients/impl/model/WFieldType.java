package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum WFieldType {

    @JsonProperty("DateTime")
    DATETIME("DateTime"),

    @JsonProperty("Blob")
    BLOB("Blob"),

    @JsonProperty("Date")
    DATE("Date"),

    @JsonProperty("ShortInteger")
    SHORTINTEGER("ShortInteger"),

    @JsonProperty("LongInteger")
    LONGINTEGER("LongInteger"),

    @JsonProperty("List")
    LIST("List"),

    @JsonProperty("Number")
    NUMBER("Number"),

    @JsonProperty("String")
    STRING("String"),

    @JsonProperty("Time")
    TIME("Time");

    public String value;

    WFieldType(String value) {
        this.value = value;
    }
}
