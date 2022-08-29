package com.laserfiche.repository.api.clients.impl.model;

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
