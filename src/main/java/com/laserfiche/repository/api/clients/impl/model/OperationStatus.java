package com.laserfiche.repository.api.clients.impl.model;

public enum OperationStatus {
    
    NOTSTARTED("NotStarted"),
    INPROGRESS("InProgress"),
    COMPLETED("Completed"),
    FAILED("Failed"),
    CANCELLED("Cancelled");

    public String value;

    OperationStatus(String value) {
        this.value = value;
    }
}
