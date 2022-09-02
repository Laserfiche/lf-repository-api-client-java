package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "The results of each operation needed in order to create the electronic document with optional template and fields.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateEntryOperations {

    @JsonProperty("entryCreate")
    public EntryCreate entryCreate = null;

    @JsonProperty("setEdoc")
    public SetEdoc setEdoc = null;

    @JsonProperty("setTemplate")
    public SetTemplate setTemplate = null;

    @JsonProperty("setFields")
    public SetFields setFields = null;

    @JsonProperty("setTags")
    public SetTags setTags = null;

    @JsonProperty("setLinks")
    public SetLinks setLinks = null;
}
