package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "The result of trying to assign fields to the entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
public class SetTags {

    @JsonProperty("exceptions")
    public List<APIServerException> exceptions = null;

    @JsonProperty("assignedTags")
    public List<String> assignedTags = null;
}
