package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
public class LFColor {

    @JsonProperty("a")
    public Integer a = null;

    @JsonProperty("r")
    public Integer r = null;

    @JsonProperty("g")
    public Integer g = null;

    @JsonProperty("b")
    public Integer b = null;
}
