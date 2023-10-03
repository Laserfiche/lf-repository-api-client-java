package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * Represents the watermark added to the images when exporting an entry.
 */
@Schema(description = "Represents the watermark added to the images when exporting an entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExportEntryRequestWatermark {

    @JsonProperty("text")
    private String text = "";

    @JsonProperty("position")
    private WatermarkPosition position = null;

    @JsonProperty("rotationAngle")
    private Integer rotationAngle = 0;

    @JsonProperty("pageSpanPercentage")
    private Integer pageSpanPercentage = 50;

    public ExportEntryRequestWatermark text(String text) {
        this.text = text;
        return this;
    }

    /**
     * Returns the text of the watermark. The value must be a string with a length of at most 100 characters and must not be all whitespace characters.
     * @return text
     */
    @Schema(description = "The text of the watermark. The value must be a string with a length of at most 100 characters and must not be all whitespace characters.")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ExportEntryRequestWatermark position(WatermarkPosition position) {
        this.position = position;
        return this;
    }

    /**
     * Returns position
     * @return position
     */
    @Schema(description = "")
    public WatermarkPosition getPosition() {
        return position;
    }

    public void setPosition(WatermarkPosition position) {
        this.position = position;
    }

    public ExportEntryRequestWatermark rotationAngle(Integer rotationAngle) {
        this.rotationAngle = rotationAngle;
        return this;
    }

    /**
     * Returns the rotation angle of the watermark. The value must be between 0 and 360 (inclusive). The default value is 0.
     * @return rotationAngle
     */
    @Schema(description = "The rotation angle of the watermark. The value must be between 0 and 360 (inclusive). The default value is 0.")
    public Integer getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(Integer rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public ExportEntryRequestWatermark pageSpanPercentage(Integer pageSpanPercentage) {
        this.pageSpanPercentage = pageSpanPercentage;
        return this;
    }

    /**
     * Returns the percentage of the page that the watermark spans on. The value must be between 1 and 100 (inclusive). The default value is 50.
     * @return pageSpanPercentage
     */
    @Schema(description = "The percentage of the page that the watermark spans on. The value must be between 1 and 100 (inclusive). The default value is 50.")
    public Integer getPageSpanPercentage() {
        return pageSpanPercentage;
    }

    public void setPageSpanPercentage(Integer pageSpanPercentage) {
        this.pageSpanPercentage = pageSpanPercentage;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExportEntryRequestWatermark exportEntryRequestWatermark = (ExportEntryRequestWatermark) o;
        return Objects.equals(this.text, exportEntryRequestWatermark.text) && Objects.equals(this.position, exportEntryRequestWatermark.position) && Objects.equals(this.rotationAngle, exportEntryRequestWatermark.rotationAngle) && Objects.equals(this.pageSpanPercentage, exportEntryRequestWatermark.pageSpanPercentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, position, rotationAngle, pageSpanPercentage);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExportEntryRequestWatermark {\n");
        sb.append("    text: ").append(toIndentedString(text)).append("\n");
        sb.append("    position: ").append(toIndentedString(position)).append("\n");
        sb.append("    rotationAngle: ").append(toIndentedString(rotationAngle)).append("\n");
        sb.append("    pageSpanPercentage: ").append(toIndentedString(pageSpanPercentage)).append("\n");
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
