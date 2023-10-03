package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.*;

/**
 * Represents the options when exporting the image part of an entry.
 */
@Schema(description = "Represents the options when exporting the image part of an entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExportEntryRequestImageOptions {

    @JsonProperty("format")
    private ExportEntryRequestImageFormat format = null;

    @JsonProperty("jpegCompressionLevel")
    private Integer jpegCompressionLevel = 70;

    @JsonProperty("includeAnnotations")
    private Boolean includeAnnotations = true;

    @JsonProperty("convertPdfAnnotations")
    private Boolean convertPdfAnnotations = true;

    @JsonProperty("pagePrefix")
    private String pagePrefix = ", Page ";

    @JsonProperty("includeRedactions")
    private Boolean includeRedactions = true;

    @JsonProperty("watermark")
    private ExportEntryRequestWatermark watermark = null;

    public ExportEntryRequestImageOptions format(ExportEntryRequestImageFormat format) {
        this.format = format;
        return this;
    }

    /**
     * Returns format
     * @return format
     */
    @Schema(description = "")
    public ExportEntryRequestImageFormat getFormat() {
        return format;
    }

    public void setFormat(ExportEntryRequestImageFormat format) {
        this.format = format;
    }

    public ExportEntryRequestImageOptions jpegCompressionLevel(Integer jpegCompressionLevel) {
        this.jpegCompressionLevel = jpegCompressionLevel;
        return this;
    }

    /**
     * Returns the quality level for JPEG compression when exporting images. The value must be between 0 and 100 (inclusive). The default value is 70.
     * @return jpegCompressionLevel
     */
    @Schema(description = "The quality level for JPEG compression when exporting images. The value must be between 0 and 100 (inclusive). The default value is 70.")
    public Integer getJpegCompressionLevel() {
        return jpegCompressionLevel;
    }

    public void setJpegCompressionLevel(Integer jpegCompressionLevel) {
        this.jpegCompressionLevel = jpegCompressionLevel;
    }

    public ExportEntryRequestImageOptions includeAnnotations(Boolean includeAnnotations) {
        this.includeAnnotations = includeAnnotations;
        return this;
    }

    /**
     * Returns indicates if the annotations need to be included. The default value is true.
     * @return includeAnnotations
     */
    @Schema(description = "Indicates if the annotations need to be included. The default value is true.")
    public Boolean isIncludeAnnotations() {
        return includeAnnotations;
    }

    public void setIncludeAnnotations(Boolean includeAnnotations) {
        this.includeAnnotations = includeAnnotations;
    }

    public ExportEntryRequestImageOptions convertPdfAnnotations(Boolean convertPdfAnnotations) {
        this.convertPdfAnnotations = convertPdfAnnotations;
        return this;
    }

    /**
     * Returns indicates if the annotations on the image need to be converted to PDF annotations when exporting to PDF format. The default value is true. This option is only applicable when exporting to PDF format and IncludeAnnotations is true.
     * @return convertPdfAnnotations
     */
    @Schema(description = "Indicates if the annotations on the image need to be converted to PDF annotations when exporting to PDF format. The default value is true. This option is only applicable when exporting to PDF format and IncludeAnnotations is true.")
    public Boolean isConvertPdfAnnotations() {
        return convertPdfAnnotations;
    }

    public void setConvertPdfAnnotations(Boolean convertPdfAnnotations) {
        this.convertPdfAnnotations = convertPdfAnnotations;
    }

    public ExportEntryRequestImageOptions pagePrefix(String pagePrefix) {
        this.pagePrefix = pagePrefix;
        return this;
    }

    /**
     * Returns the page prefix of the individual files, when exporting to multi-file format (e.g.zip). The value must have a length of at most 10 characters and only valid characters that can be included in file names are allowed. The default value is \&quot;, Page \&quot;.
     * @return pagePrefix
     */
    @Schema(description = "The page prefix of the individual files, when exporting to multi-file format (e.g.zip). The value must have a length of at most 10 characters and only valid characters that can be included in file names are allowed. The default value is \", Page \".")
    public String getPagePrefix() {
        return pagePrefix;
    }

    public void setPagePrefix(String pagePrefix) {
        this.pagePrefix = pagePrefix;
    }

    public ExportEntryRequestImageOptions includeRedactions(Boolean includeRedactions) {
        this.includeRedactions = includeRedactions;
        return this;
    }

    /**
     * Returns indicates if redactions are included. The default value is true.
     * @return includeRedactions
     */
    @Schema(description = "Indicates if redactions are included. The default value is true.")
    public Boolean isIncludeRedactions() {
        return includeRedactions;
    }

    public void setIncludeRedactions(Boolean includeRedactions) {
        this.includeRedactions = includeRedactions;
    }

    public ExportEntryRequestImageOptions watermark(ExportEntryRequestWatermark watermark) {
        this.watermark = watermark;
        return this;
    }

    /**
     * Returns watermark
     * @return watermark
     */
    @Schema(description = "")
    public ExportEntryRequestWatermark getWatermark() {
        return watermark;
    }

    public void setWatermark(ExportEntryRequestWatermark watermark) {
        this.watermark = watermark;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExportEntryRequestImageOptions exportEntryRequestImageOptions = (ExportEntryRequestImageOptions) o;
        return Objects.equals(this.format, exportEntryRequestImageOptions.format) && Objects.equals(this.jpegCompressionLevel, exportEntryRequestImageOptions.jpegCompressionLevel) && Objects.equals(this.includeAnnotations, exportEntryRequestImageOptions.includeAnnotations) && Objects.equals(this.convertPdfAnnotations, exportEntryRequestImageOptions.convertPdfAnnotations) && Objects.equals(this.pagePrefix, exportEntryRequestImageOptions.pagePrefix) && Objects.equals(this.includeRedactions, exportEntryRequestImageOptions.includeRedactions) && Objects.equals(this.watermark, exportEntryRequestImageOptions.watermark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(format, jpegCompressionLevel, includeAnnotations, convertPdfAnnotations, pagePrefix, includeRedactions, watermark);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExportEntryRequestImageOptions {\n");
        sb.append("    format: ").append(toIndentedString(format)).append("\n");
        sb.append("    jpegCompressionLevel: ").append(toIndentedString(jpegCompressionLevel)).append("\n");
        sb.append("    includeAnnotations: ").append(toIndentedString(includeAnnotations)).append("\n");
        sb.append("    convertPdfAnnotations: ").append(toIndentedString(convertPdfAnnotations)).append("\n");
        sb.append("    pagePrefix: ").append(toIndentedString(pagePrefix)).append("\n");
        sb.append("    includeRedactions: ").append(toIndentedString(includeRedactions)).append("\n");
        sb.append("    watermark: ").append(toIndentedString(watermark)).append("\n");
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
