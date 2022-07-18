package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
/**
 * AuditReasons
 */

public class AuditReasons {
  @SerializedName("deleteEntry")
  private List<WAuditReason> deleteEntry = null;

  @SerializedName("exportDocument")
  private List<WAuditReason> exportDocument = null;

  public AuditReasons deleteEntry(List<WAuditReason> deleteEntry) {
    this.deleteEntry = deleteEntry;
    return this;
  }

  public AuditReasons addDeleteEntryItem(WAuditReason deleteEntryItem) {
    if (this.deleteEntry == null) {
      this.deleteEntry = new ArrayList<WAuditReason>();
    }
    this.deleteEntry.add(deleteEntryItem);
    return this;
  }

   /**
   * The audit reasons associated with delete entry.
   * @return deleteEntry
  **/
  @Schema(description = "The audit reasons associated with delete entry.")
  public List<WAuditReason> getDeleteEntry() {
    return deleteEntry;
  }

  public void setDeleteEntry(List<WAuditReason> deleteEntry) {
    this.deleteEntry = deleteEntry;
  }

  public AuditReasons exportDocument(List<WAuditReason> exportDocument) {
    this.exportDocument = exportDocument;
    return this;
  }

  public AuditReasons addExportDocumentItem(WAuditReason exportDocumentItem) {
    if (this.exportDocument == null) {
      this.exportDocument = new ArrayList<WAuditReason>();
    }
    this.exportDocument.add(exportDocumentItem);
    return this;
  }

   /**
   * The audit reasons associated with export document.
   * @return exportDocument
  **/
  @Schema(description = "The audit reasons associated with export document.")
  public List<WAuditReason> getExportDocument() {
    return exportDocument;
  }

  public void setExportDocument(List<WAuditReason> exportDocument) {
    this.exportDocument = exportDocument;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuditReasons auditReasons = (AuditReasons) o;
    return Objects.equals(this.deleteEntry, auditReasons.deleteEntry) &&
        Objects.equals(this.exportDocument, auditReasons.exportDocument);
  }

  @Override
  public int hashCode() {
    return Objects.hash(deleteEntry, exportDocument);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuditReasons {\n");
    
    sb.append("    deleteEntry: ").append(toIndentedString(deleteEntry)).append("\n");
    sb.append("    exportDocument: ").append(toIndentedString(exportDocument)).append("\n");
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
