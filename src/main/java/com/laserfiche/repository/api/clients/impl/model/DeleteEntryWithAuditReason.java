package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * DeleteEntryWithAuditReason
 */

public class DeleteEntryWithAuditReason {
  @SerializedName("auditReasonId")
  private Integer auditReasonId = null;

  @SerializedName("comment")
  private String comment = null;

  public DeleteEntryWithAuditReason auditReasonId(Integer auditReasonId) {
    this.auditReasonId = auditReasonId;
    return this;
  }

   /**
   * The reason id for this audit event.
   * @return auditReasonId
  **/
  @Schema(description = "The reason id for this audit event.")
  public Integer getAuditReasonId() {
    return auditReasonId;
  }

  public void setAuditReasonId(Integer auditReasonId) {
    this.auditReasonId = auditReasonId;
  }

  public DeleteEntryWithAuditReason comment(String comment) {
    this.comment = comment;
    return this;
  }

   /**
   * The comment for this audit event.
   * @return comment
  **/
  @Schema(description = "The comment for this audit event.")
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeleteEntryWithAuditReason deleteEntryWithAuditReason = (DeleteEntryWithAuditReason) o;
    return Objects.equals(this.auditReasonId, deleteEntryWithAuditReason.auditReasonId) &&
        Objects.equals(this.comment, deleteEntryWithAuditReason.comment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(auditReasonId, comment);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeleteEntryWithAuditReason {\n");
    
    sb.append("    auditReasonId: ").append(toIndentedString(auditReasonId)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
