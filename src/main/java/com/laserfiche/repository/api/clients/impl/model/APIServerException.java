package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
public class APIServerException {

    @JsonProperty("operationId")
    public String operationId = null;

    @JsonProperty("message")
    public String message = null;

    @JsonProperty("errorCode")
    public Integer errorCode = null;

    @JsonProperty("errorClass")
    public String errorClass = null;

    @JsonProperty("statusCode")
    public Integer statusCode = null;

    @JsonProperty("errorSource")
    public String errorSource = null;
}
