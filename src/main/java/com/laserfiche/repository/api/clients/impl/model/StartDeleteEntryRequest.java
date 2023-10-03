package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * Request body for starting an asynchronous delete entry task.
 */
@Schema(description = "Request body for starting an asynchronous delete entry task.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StartDeleteEntryRequest {

    @JsonProperty("auditReasonId")
    private Integer auditReasonId = null;

    @JsonProperty("auditReasonComment")
    private String auditReasonComment = null;

    public StartDeleteEntryRequest auditReasonId(Integer auditReasonId) {
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

    public StartDeleteEntryRequest auditReasonComment(String auditReasonComment) {
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

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StartDeleteEntryRequest startDeleteEntryRequest = (StartDeleteEntryRequest) o;
        return Objects.equals(this.auditReasonId, startDeleteEntryRequest.auditReasonId) && Objects.equals(this.auditReasonComment, startDeleteEntryRequest.auditReasonComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auditReasonId, auditReasonComment);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StartDeleteEntryRequest {\n");
        sb.append("    auditReasonId: ").append(toIndentedString(auditReasonId)).append("\n");
        sb.append("    auditReasonComment: ").append(toIndentedString(auditReasonComment)).append("\n");
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
