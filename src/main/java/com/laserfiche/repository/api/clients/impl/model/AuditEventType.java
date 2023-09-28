package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.*;

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
