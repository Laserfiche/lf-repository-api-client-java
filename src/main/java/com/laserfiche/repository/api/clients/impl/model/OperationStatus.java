package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets OperationStatus
 */
public enum OperationStatus {
    NOT_STARTED("NotStarted"),
    IN_PROGRESS("InProgress"),
    COMPLETED("Completed"),
    FAILED("Failed"),
    CANCELLED("Cancelled");

    private String value;

    OperationStatus(String value) {
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
    public static OperationStatus fromValue(String input) {
        for (OperationStatus b : OperationStatus.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }
}
