package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntryLinkTypeInfo {

    @JsonProperty("linkTypeId")
    public Integer linkTypeId = null;

    @JsonProperty("sourceLabel")
    public String sourceLabel = null;

    @JsonProperty("targetLabel")
    public String targetLabel = null;

    @JsonProperty("linkTypeDescription")
    public String linkTypeDescription = null;
}
