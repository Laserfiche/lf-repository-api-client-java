package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * Request body for exporting an entry.
 */
@Schema(description = "Request body for exporting an entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExportEntryRequest {

    @JsonProperty("auditReasonId")
    private Integer auditReasonId = null;

    @JsonProperty("auditReasonComment")
    private String auditReasonComment = "";

    @JsonProperty("part")
    private ExportEntryRequestPart part = null;

    @JsonProperty("imageOptions")
    private ExportEntryRequestImageOptions imageOptions = null;

    @JsonProperty("textOptions")
    private ExportEntryRequestTextOptions textOptions = null;

    public ExportEntryRequest auditReasonId(Integer auditReasonId) {
        this.auditReasonId = auditReasonId;
        return this;
    }

    /**
     * Returns the reason id for this audit event.
     *
     * @return auditReasonId
     */
    @Schema(description = "The reason id for this audit event.")
    public Integer getAuditReasonId() {
        return auditReasonId;
    }

    public void setAuditReasonId(Integer auditReasonId) {
        this.auditReasonId = auditReasonId;
    }

    public ExportEntryRequest auditReasonComment(String auditReasonComment) {
        this.auditReasonComment = auditReasonComment;
        return this;
    }

    /**
     * Returns the comment for this audit event.
     *
     * @return auditReasonComment
     */
    @Schema(description = "The comment for this audit event.")
    public String getAuditReasonComment() {
        return auditReasonComment;
    }

    public void setAuditReasonComment(String auditReasonComment) {
        this.auditReasonComment = auditReasonComment;
    }

    public ExportEntryRequest part(ExportEntryRequestPart part) {
        this.part = part;
        return this;
    }

    /**
     * Returns part
     *
     * @return part
     */
    @Schema(required = true, description = "")
    public ExportEntryRequestPart getPart() {
        return part;
    }

    public void setPart(ExportEntryRequestPart part) {
        this.part = part;
    }

    public ExportEntryRequest imageOptions(ExportEntryRequestImageOptions imageOptions) {
        this.imageOptions = imageOptions;
        return this;
    }

    /**
     * Returns imageOptions
     *
     * @return imageOptions
     */
    @Schema(description = "")
    public ExportEntryRequestImageOptions getImageOptions() {
        return imageOptions;
    }

    public void setImageOptions(ExportEntryRequestImageOptions imageOptions) {
        this.imageOptions = imageOptions;
    }

    public ExportEntryRequest textOptions(ExportEntryRequestTextOptions textOptions) {
        this.textOptions = textOptions;
        return this;
    }

    /**
     * Returns textOptions
     *
     * @return textOptions
     */
    @Schema(description = "")
    public ExportEntryRequestTextOptions getTextOptions() {
        return textOptions;
    }

    public void setTextOptions(ExportEntryRequestTextOptions textOptions) {
        this.textOptions = textOptions;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExportEntryRequest exportEntryRequest = (ExportEntryRequest) o;
        return Objects.equals(this.auditReasonId, exportEntryRequest.auditReasonId) && Objects.equals(this.auditReasonComment, exportEntryRequest.auditReasonComment) && Objects.equals(this.part, exportEntryRequest.part) && Objects.equals(this.imageOptions, exportEntryRequest.imageOptions) && Objects.equals(this.textOptions, exportEntryRequest.textOptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auditReasonId, auditReasonComment, part, imageOptions, textOptions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExportEntryRequest {\n");
        sb.append("    auditReasonId: ").append(toIndentedString(auditReasonId)).append("\n");
        sb.append("    auditReasonComment: ").append(toIndentedString(auditReasonComment)).append("\n");
        sb.append("    part: ").append(toIndentedString(part)).append("\n");
        sb.append("    imageOptions: ").append(toIndentedString(imageOptions)).append("\n");
        sb.append("    textOptions: ").append(toIndentedString(textOptions)).append("\n");
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
