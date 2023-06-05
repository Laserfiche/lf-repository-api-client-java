package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(
        value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen",
        date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuditReasons {

    @JsonProperty("deleteEntry")
    private List<WAuditReason> deleteEntry = null;

    @JsonProperty("exportDocument")
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
     * Returns the audit reasons associated with delete entry.
     *
     * @return deleteEntry
     */
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
     * Returns the audit reasons associated with export document.
     *
     * @return exportDocument
     */
    @Schema(description = "The audit reasons associated with export document.")
    public List<WAuditReason> getExportDocument() {
        return exportDocument;
    }

    public void setExportDocument(List<WAuditReason> exportDocument) {
        this.exportDocument = exportDocument;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuditReasons auditReasons = (AuditReasons) o;
        return Objects.equals(this.deleteEntry, auditReasons.deleteEntry)
                && Objects.equals(this.exportDocument, auditReasons.exportDocument);
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

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
