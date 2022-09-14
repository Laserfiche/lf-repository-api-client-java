package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum OperationStatus {

    @JsonProperty("NotStarted")
    NOTSTARTED("NotStarted"),
    @JsonProperty("InProgress")
    INPROGRESS("InProgress"),
    @JsonProperty("Completed")
    COMPLETED("Completed"),
    @JsonProperty("Failed")
    FAILED("Failed"),
    @JsonProperty("Cancelled")
    CANCELLED("Cancelled");

    public String value;

    OperationStatus(String value) {
        this.value = value;
    }
}
