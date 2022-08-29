package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.laserfiche.api.client.model.LFColor;
import io.swagger.v3.oas.annotations.media.Schema;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
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
