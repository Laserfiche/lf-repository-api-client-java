package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
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
