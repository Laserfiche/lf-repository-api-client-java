package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostEntryChildrenRequest {

    @JsonProperty("name")
    public String name = null;

    @JsonProperty("entryType")
    public PostEntryChildrenEntryType entryType = null;

    @JsonProperty("targetId")
    public Integer targetId = null;

    @JsonProperty("sourceId")
    public Integer sourceId = null;
}
