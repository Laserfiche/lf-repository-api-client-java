package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
public class LinkToUpdate {

    @JsonProperty("linkTypeId")
    public Integer linkTypeId = null;

    @JsonProperty("otherSourceId")
    public Integer otherSourceId = null;

    @JsonProperty("isSource")
    public Boolean isSource = null;
}
