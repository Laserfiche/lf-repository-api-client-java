package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WTemplateInfo {

    @JsonProperty("id")
    public Integer id = null;

    @JsonProperty("name")
    public String name = null;

    @JsonProperty("displayName")
    public String displayName = null;

    @JsonProperty("description")
    public String description = null;

    @JsonProperty("color")
    public LFColor color = null;

    @JsonProperty("fieldCount")
    public Integer fieldCount = null;
}
