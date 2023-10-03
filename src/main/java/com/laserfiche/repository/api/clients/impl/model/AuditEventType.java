package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumeration of Laserfiche audit event types.
 */
public enum AuditEventType {

    DELETE_ENTRY("DeleteEntry"), EXPORT_DOCUMENT("ExportDocument");

    private String value;

    AuditEventType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static AuditEventType fromValue(String input) {
        for (AuditEventType b : AuditEventType.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }
}
