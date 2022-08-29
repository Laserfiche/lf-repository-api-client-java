package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.laserfiche.api.client.model.WatermarkPosition;
import io.swagger.v3.oas.annotations.media.Schema;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
public class Watermark {

    @JsonProperty("watermarkText")
    public String watermarkText = null;

    @JsonProperty("watermarkTextSize")
    public Integer watermarkTextSize = null;

    @JsonProperty("watermarkPosition")
    public WatermarkPosition watermarkPosition = null;

    @JsonProperty("watermarkRotationAngle")
    public Integer watermarkRotationAngle = null;

    @JsonProperty("isWatermarkMandatory")
    public Boolean isWatermarkMandatory = null;

    @JsonProperty("watermarkIntensity")
    public Integer watermarkIntensity = null;
}
