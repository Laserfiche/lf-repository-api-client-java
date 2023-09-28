package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.*;

/**
 * An enumeration of possible statuses for a long operation task.
 */
public enum TaskStatus {

    NOT_STARTED("NotStarted"), IN_PROGRESS("InProgress"), COMPLETED("Completed"), FAILED("Failed"), CANCELLED("Cancelled");

    private String value;

    TaskStatus(String value) {
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
    public static TaskStatus fromValue(String input) {
        for (TaskStatus b : TaskStatus.values()) {
            if (b.value.equals(input)) {
                return b;
            }
        }
        return null;
    }
}
