package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.threeten.bp.OffsetDateTime;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
public class OperationProgress {

    @JsonProperty("operationToken")
    public String operationToken = null;

    @JsonProperty("operationType")
    public String operationType = null;

    @JsonProperty("percentComplete")
    public Integer percentComplete = null;

    @JsonProperty("status")
    public OperationStatus status = null;

    @JsonProperty("errors")
    public List<OperationErrorItem> errors = null;

    @JsonProperty("redirectUri")
    public String redirectUri = null;

    @JsonProperty("startTimestamp")
    public OffsetDateTime startTimestamp = null;

    @JsonProperty("statusTimestamp")
    public OffsetDateTime statusTimestamp = null;
}
