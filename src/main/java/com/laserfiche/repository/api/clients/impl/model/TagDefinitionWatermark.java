// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * Represents a watermark associated with a tag defintion.
 */
@Schema(description = "Represents a watermark associated with a tag defintion.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagDefinitionWatermark {

    @JsonProperty("text")
    private String text = null;

    @JsonProperty("pageSpanPercentage")
    private Integer pageSpanPercentage = null;

    @JsonProperty("position")
    private WatermarkPosition position = null;

    @JsonProperty("rotationAngle")
    private Integer rotationAngle = null;

    @JsonProperty("isMandatory")
    private Boolean isMandatory = null;

    @JsonProperty("opacity")
    private Integer opacity = null;

    public TagDefinitionWatermark text(String text) {
        this.text = text;
        return this;
    }

    /**
     * Returns the watermark text associated with the tag defintion.
     *
     * @return text
     */
    @Schema(description = "The watermark text associated with the tag defintion.")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TagDefinitionWatermark pageSpanPercentage(Integer pageSpanPercentage) {
        this.pageSpanPercentage = pageSpanPercentage;
        return this;
    }

    /**
     * Returns the percentage of the page that the watermark associated with the tag definition spans on.
     *
     * @return pageSpanPercentage
     */
    @Schema(description = "The percentage of the page that the watermark associated with the tag definition spans on.")
    public Integer getPageSpanPercentage() {
        return pageSpanPercentage;
    }

    public void setPageSpanPercentage(Integer pageSpanPercentage) {
        this.pageSpanPercentage = pageSpanPercentage;
    }

    public TagDefinitionWatermark position(WatermarkPosition position) {
        this.position = position;
        return this;
    }

    /**
     * Returns position
     *
     * @return position
     */
    @Schema(description = "")
    public WatermarkPosition getPosition() {
        return position;
    }

    public void setPosition(WatermarkPosition position) {
        this.position = position;
    }

    public TagDefinitionWatermark rotationAngle(Integer rotationAngle) {
        this.rotationAngle = rotationAngle;
        return this;
    }

    /**
     * Returns the rotation angle, in degrees, of the watermark associated with the tag definition.
     *
     * @return rotationAngle
     */
    @Schema(description = "The rotation angle, in degrees, of the watermark associated with the tag definition.")
    public Integer getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(Integer rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public TagDefinitionWatermark isMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
        return this;
    }

    /**
     * Returns a boolean indicating whether or not the watermark associated with the tag is mandatory.
     *
     * @return isMandatory
     */
    @Schema(description = "A boolean indicating whether or not the watermark associated with the tag is mandatory.")
    @JsonProperty("isMandatory")
    public Boolean isMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public TagDefinitionWatermark opacity(Integer opacity) {
        this.opacity = opacity;
        return this;
    }

    /**
     * Returns the opacity of the watermark associated with the tag definition. Valid value ranges from 0 to 100, with -1 as the default values.
     *
     * @return opacity
     */
    @Schema(description = "The opacity of the watermark associated with the tag definition. Valid value ranges from 0 to 100, with -1 as the default values.")
    public Integer getOpacity() {
        return opacity;
    }

    public void setOpacity(Integer opacity) {
        this.opacity = opacity;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TagDefinitionWatermark tagDefinitionWatermark = (TagDefinitionWatermark) o;
        return Objects.equals(this.text, tagDefinitionWatermark.text) && Objects.equals(this.pageSpanPercentage, tagDefinitionWatermark.pageSpanPercentage) && Objects.equals(this.position, tagDefinitionWatermark.position) && Objects.equals(this.rotationAngle, tagDefinitionWatermark.rotationAngle) && Objects.equals(this.isMandatory, tagDefinitionWatermark.isMandatory) && Objects.equals(this.opacity, tagDefinitionWatermark.opacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, pageSpanPercentage, position, rotationAngle, isMandatory, opacity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TagDefinitionWatermark {\n");
        sb.append("    text: ").append(toIndentedString(text)).append("\n");
        sb.append("    pageSpanPercentage: ").append(toIndentedString(pageSpanPercentage)).append("\n");
        sb.append("    position: ").append(toIndentedString(position)).append("\n");
        sb.append("    rotationAngle: ").append(toIndentedString(rotationAngle)).append("\n");
        sb.append("    isMandatory: ").append(toIndentedString(isMandatory)).append("\n");
        sb.append("    opacity: ").append(toIndentedString(opacity)).append("\n");
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
