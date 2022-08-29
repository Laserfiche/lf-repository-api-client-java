package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.laserfiche.api.client.model.OperationErrorItem;
import com.laserfiche.api.client.model.OperationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.OffsetDateTime;

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
