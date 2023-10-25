// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Watermark {

    @JsonProperty("watermarkText")
    private String watermarkText = null;

    @JsonProperty("watermarkTextSize")
    private Integer watermarkTextSize = null;

    @JsonProperty("watermarkPosition")
    private WatermarkPosition watermarkPosition = null;

    @JsonProperty("watermarkRotationAngle")
    private Integer watermarkRotationAngle = null;

    @JsonProperty("isWatermarkMandatory")
    private Boolean isWatermarkMandatory = null;

    @JsonProperty("watermarkIntensity")
    private Integer watermarkIntensity = null;

    public Watermark watermarkText(String watermarkText) {
        this.watermarkText = watermarkText;
        return this;
    }

    /**
     * Returns the watermark text associated with the tag defintion.
     * @return watermarkText
     */
    @Schema(description = "The watermark text associated with the tag defintion.")
    public String getWatermarkText() {
        return watermarkText;
    }

    public void setWatermarkText(String watermarkText) {
        this.watermarkText = watermarkText;
    }

    public Watermark watermarkTextSize(Integer watermarkTextSize) {
        this.watermarkTextSize = watermarkTextSize;
        return this;
    }

    /**
     * Returns the size of the watermark text, in points, associated with the tag definition.
     * @return watermarkTextSize
     */
    @Schema(description = "The size of the watermark text, in points, associated with the tag definition.")
    public Integer getWatermarkTextSize() {
        return watermarkTextSize;
    }

    public void setWatermarkTextSize(Integer watermarkTextSize) {
        this.watermarkTextSize = watermarkTextSize;
    }

    public Watermark watermarkPosition(WatermarkPosition watermarkPosition) {
        this.watermarkPosition = watermarkPosition;
        return this;
    }

    /**
     * Returns watermarkPosition
     * @return watermarkPosition
     */
    @Schema(description = "")
    public WatermarkPosition getWatermarkPosition() {
        return watermarkPosition;
    }

    public void setWatermarkPosition(WatermarkPosition watermarkPosition) {
        this.watermarkPosition = watermarkPosition;
    }

    public Watermark watermarkRotationAngle(Integer watermarkRotationAngle) {
        this.watermarkRotationAngle = watermarkRotationAngle;
        return this;
    }

    /**
     * Returns the rotation angle, in degrees, of the watermark associated with the tag definition.
     * @return watermarkRotationAngle
     */
    @Schema(description = "The rotation angle, in degrees, of the watermark associated with the tag definition.")
    public Integer getWatermarkRotationAngle() {
        return watermarkRotationAngle;
    }

    public void setWatermarkRotationAngle(Integer watermarkRotationAngle) {
        this.watermarkRotationAngle = watermarkRotationAngle;
    }

    public Watermark isWatermarkMandatory(Boolean isWatermarkMandatory) {
        this.isWatermarkMandatory = isWatermarkMandatory;
        return this;
    }

    /**
     * Returns a boolean indicating whether or not the watermark associated with the tag is mandatory.
     * @return isWatermarkMandatory
     */
    @Schema(description = "A boolean indicating whether or not the watermark associated with the tag is mandatory.")
    @JsonProperty("isWatermarkMandatory")
    public Boolean isWatermarkMandatory() {
        return isWatermarkMandatory;
    }

    public void setIsWatermarkMandatory(Boolean isWatermarkMandatory) {
        this.isWatermarkMandatory = isWatermarkMandatory;
    }

    public Watermark watermarkIntensity(Integer watermarkIntensity) {
        this.watermarkIntensity = watermarkIntensity;
        return this;
    }

    /**
     * Returns the intensity of the watermark associated with the tag definition. Valid value  ranges from 0 to 100, with -1 as the default values.
     * @return watermarkIntensity
     */
    @Schema(
            description =
                    "The intensity of the watermark associated with the tag definition. Valid value  ranges from 0 to 100, with -1 as the default values.")
    public Integer getWatermarkIntensity() {
        return watermarkIntensity;
    }

    public void setWatermarkIntensity(Integer watermarkIntensity) {
        this.watermarkIntensity = watermarkIntensity;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Watermark watermark = (Watermark) o;
        return Objects.equals(this.watermarkText, watermark.watermarkText)
                && Objects.equals(this.watermarkTextSize, watermark.watermarkTextSize)
                && Objects.equals(this.watermarkPosition, watermark.watermarkPosition)
                && Objects.equals(this.watermarkRotationAngle, watermark.watermarkRotationAngle)
                && Objects.equals(this.isWatermarkMandatory, watermark.isWatermarkMandatory)
                && Objects.equals(this.watermarkIntensity, watermark.watermarkIntensity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                watermarkText,
                watermarkTextSize,
                watermarkPosition,
                watermarkRotationAngle,
                isWatermarkMandatory,
                watermarkIntensity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Watermark {\n");
        sb.append("    watermarkText: ").append(toIndentedString(watermarkText)).append("\n");
        sb.append("    watermarkTextSize: ")
                .append(toIndentedString(watermarkTextSize))
                .append("\n");
        sb.append("    watermarkPosition: ")
                .append(toIndentedString(watermarkPosition))
                .append("\n");
        sb.append("    watermarkRotationAngle: ")
                .append(toIndentedString(watermarkRotationAngle))
                .append("\n");
        sb.append("    isWatermarkMandatory: ")
                .append(toIndentedString(isWatermarkMandatory))
                .append("\n");
        sb.append("    watermarkIntensity: ")
                .append(toIndentedString(watermarkIntensity))
                .append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
