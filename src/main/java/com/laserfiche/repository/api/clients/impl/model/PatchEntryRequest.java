package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
public class PatchEntryRequest {

    @JsonProperty("parentId")
    public Integer parentId = null;

    @JsonProperty("name")
    public String name = null;
}
