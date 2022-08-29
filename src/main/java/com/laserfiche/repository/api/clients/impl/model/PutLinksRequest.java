package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
public class PutLinksRequest {

    @JsonProperty("targetId")
    public Integer targetId = null;

    @JsonProperty("linkTypeId")
    public Integer linkTypeId = null;

    @JsonProperty("customProperties")
    public Map<String, String> customProperties = null;
}
