package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WEntryLinkInfo {

    @JsonProperty("linkId")
    public Integer linkId = null;

    @JsonProperty("sourceId")
    public Integer sourceId = null;

    @JsonProperty("sourceFullPath")
    public String sourceFullPath = null;

    @JsonProperty("sourceLabel")
    public String sourceLabel = null;

    @JsonProperty("targetId")
    public Integer targetId = null;

    @JsonProperty("targetFullPath")
    public String targetFullPath = null;

    @JsonProperty("targetLabel")
    public String targetLabel = null;

    @JsonProperty("description")
    public String description = null;

    @JsonProperty("linkTypeDescription")
    public String linkTypeDescription = null;

    @JsonProperty("linkTypeId")
    public Integer linkTypeId = null;

    @JsonProperty("linkProperties")
    public Map<String, String> linkProperties = null;

    @JsonProperty("sourceLink")
    public String sourceLink = null;

    @JsonProperty("targetLink")
    public String targetLink = null;
}
