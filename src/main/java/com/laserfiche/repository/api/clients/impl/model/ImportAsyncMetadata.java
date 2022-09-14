package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImportAsyncMetadata {

    @JsonProperty("fields")
    public Map<String, FieldToUpdate> fields = null;

    @JsonProperty("tags")
    public List<String> tags = null;

    @JsonProperty("links")
    public List<LinkToUpdate> links = null;
}
