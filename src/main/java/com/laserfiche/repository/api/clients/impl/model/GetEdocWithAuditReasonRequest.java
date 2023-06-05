package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@javax.annotation.Generated(
        value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen",
        date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetEdocWithAuditReasonRequest {

    @JsonProperty("auditReasonId")
    private Integer auditReasonId = null;

    @JsonProperty("comment")
    private String comment = null;

    public GetEdocWithAuditReasonRequest auditReasonId(Integer auditReasonId) {
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

    public GetEdocWithAuditReasonRequest comment(String comment) {
        this.comment = comment;
        return this;
    }

    /**
     * Returns the comment for this audit event.
     *
     * @return comment
     */
    @Schema(description = "The comment for this audit event.")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetEdocWithAuditReasonRequest getEdocWithAuditReasonRequest =
                (GetEdocWithAuditReasonRequest) o;
        return Objects.equals(this.auditReasonId, getEdocWithAuditReasonRequest.auditReasonId)
                && Objects.equals(this.comment, getEdocWithAuditReasonRequest.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auditReasonId, comment);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetEdocWithAuditReasonRequest {\n");
        sb.append("    auditReasonId: ").append(toIndentedString(auditReasonId)).append("\n");
        sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
