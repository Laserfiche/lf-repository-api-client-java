package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.laserfiche.api.client.model.EntryCreate;
import com.laserfiche.api.client.model.SetEdoc;
import com.laserfiche.api.client.model.SetFields;
import com.laserfiche.api.client.model.SetLinks;
import com.laserfiche.api.client.model.SetTags;
import com.laserfiche.api.client.model.SetTemplate;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "The results of each operation needed in order to create the electronic document with optional template and fields.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
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
