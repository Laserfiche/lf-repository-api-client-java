package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@javax.annotation.Generated(
        value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen",
        date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Document extends Entry {

    @JsonProperty("elecDocumentSize")
    private Long elecDocumentSize = null;

    @JsonProperty("extension")
    private String extension = null;

    @JsonProperty("isElectronicDocument")
    private Boolean isElectronicDocument = null;

    @JsonProperty("isRecord")
    private Boolean isRecord = null;

    @JsonProperty("mimeType")
    private String mimeType = null;

    @JsonProperty("pageCount")
    private Integer pageCount = null;

    @JsonProperty("isCheckedOut")
    private Boolean isCheckedOut = null;

    @JsonProperty("isUnderVersionControl")
    private Boolean isUnderVersionControl = null;

    @JsonProperty("edoc")
    private Edoc edoc = null;

    public Document elecDocumentSize(Long elecDocumentSize) {
        this.elecDocumentSize = elecDocumentSize;
        return this;
    }

    /**
     * Returns the size of the electronic document attached to the represented document, if there is
     * one, in bytes.
     *
     * @return elecDocumentSize
     */
    @Schema(
            description =
                    "The size of the electronic document attached to the represented document, if there is one, in bytes.")
    public Long getElecDocumentSize() {
        return elecDocumentSize;
    }

    public void setElecDocumentSize(Long elecDocumentSize) {
        this.elecDocumentSize = elecDocumentSize;
    }

    public Document extension(String extension) {
        this.extension = extension;
        return this;
    }

    /**
     * Returns the extension for the document.
     *
     * @return extension
     */
    @Schema(description = "The extension for the document.")
    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Document isElectronicDocument(Boolean isElectronicDocument) {
        this.isElectronicDocument = isElectronicDocument;
        return this;
    }

    /**
     * Returns a boolean indicating if there is an electronic document attached to the represented
     * document.
     *
     * @return isElectronicDocument
     */
    @Schema(
            description =
                    "A boolean indicating if there is an electronic document attached to the represented document.")
    @JsonProperty("isElectronicDocument")
    public Boolean isElectronicDocument() {
        return isElectronicDocument;
    }

    public void setIsElectronicDocument(Boolean isElectronicDocument) {
        this.isElectronicDocument = isElectronicDocument;
    }

    public Document isRecord(Boolean isRecord) {
        this.isRecord = isRecord;
        return this;
    }

    /**
     * Returns a boolean indicating if the represented document is a record.
     *
     * @return isRecord
     */
    @Schema(description = "A boolean indicating if the represented document is a record.")
    @JsonProperty("isRecord")
    public Boolean isRecord() {
        return isRecord;
    }

    public void setIsRecord(Boolean isRecord) {
        this.isRecord = isRecord;
    }

    public Document mimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    /**
     * Returns the MIME type of the electronic document.
     *
     * @return mimeType
     */
    @Schema(description = "The MIME type of the electronic document.")
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Document pageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    /**
     * Returns the page count of the represented document.
     *
     * @return pageCount
     */
    @Schema(description = "The page count of the represented document.")
    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Document isCheckedOut(Boolean isCheckedOut) {
        this.isCheckedOut = isCheckedOut;
        return this;
    }

    /**
     * Returns a boolean indicating if the represented document is checked out.
     *
     * @return isCheckedOut
     */
    @Schema(description = "A boolean indicating if the represented document is checked out.")
    @JsonProperty("isCheckedOut")
    public Boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setIsCheckedOut(Boolean isCheckedOut) {
        this.isCheckedOut = isCheckedOut;
    }

    public Document isUnderVersionControl(Boolean isUnderVersionControl) {
        this.isUnderVersionControl = isUnderVersionControl;
        return this;
    }

    /**
     * Returns a boolean indicating if the represented document is under version control.
     *
     * @return isUnderVersionControl
     */
    @Schema(
            description =
                    "A boolean indicating if the represented document is under version control.")
    @JsonProperty("isUnderVersionControl")
    public Boolean isUnderVersionControl() {
        return isUnderVersionControl;
    }

    public void setIsUnderVersionControl(Boolean isUnderVersionControl) {
        this.isUnderVersionControl = isUnderVersionControl;
    }

    public Document edoc(Edoc edoc) {
        this.edoc = edoc;
        return this;
    }

    /**
     * Returns edoc
     *
     * @return edoc
     */
    @Schema(description = "")
    public Edoc getEdoc() {
        return edoc;
    }

    public void setEdoc(Edoc edoc) {
        this.edoc = edoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Document document = (Document) o;
        return Objects.equals(this.elecDocumentSize, document.elecDocumentSize)
                && Objects.equals(this.extension, document.extension)
                && Objects.equals(this.isElectronicDocument, document.isElectronicDocument)
                && Objects.equals(this.isRecord, document.isRecord)
                && Objects.equals(this.mimeType, document.mimeType)
                && Objects.equals(this.pageCount, document.pageCount)
                && Objects.equals(this.isCheckedOut, document.isCheckedOut)
                && Objects.equals(this.isUnderVersionControl, document.isUnderVersionControl)
                && Objects.equals(this.edoc, document.edoc)
                && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                elecDocumentSize,
                extension,
                isElectronicDocument,
                isRecord,
                mimeType,
                pageCount,
                isCheckedOut,
                isUnderVersionControl,
                edoc,
                super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Document {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    elecDocumentSize: ").append(toIndentedString(elecDocumentSize)).append("\n");
        sb.append("    extension: ").append(toIndentedString(extension)).append("\n");
        sb.append("    isElectronicDocument: ")
                .append(toIndentedString(isElectronicDocument))
                .append("\n");
        sb.append("    isRecord: ").append(toIndentedString(isRecord)).append("\n");
        sb.append("    mimeType: ").append(toIndentedString(mimeType)).append("\n");
        sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
        sb.append("    isCheckedOut: ").append(toIndentedString(isCheckedOut)).append("\n");
        sb.append("    isUnderVersionControl: ")
                .append(toIndentedString(isUnderVersionControl))
                .append("\n");
        sb.append("    edoc: ").append(toIndentedString(edoc)).append("\n");
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
