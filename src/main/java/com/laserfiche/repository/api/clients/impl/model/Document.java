package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * Document
 */

public class Document extends Entry {
  @SerializedName("elecDocumentSize")
  private Long elecDocumentSize = null;

  @SerializedName("extension")
  private String extension = null;

  @SerializedName("isElectronicDocument")
  private Boolean isElectronicDocument = null;

  @SerializedName("isRecord")
  private Boolean isRecord = null;

  @SerializedName("mimeType")
  private String mimeType = null;

  @SerializedName("pageCount")
  private Integer pageCount = null;

  @SerializedName("isCheckedOut")
  private Boolean isCheckedOut = null;

  @SerializedName("isUnderVersionControl")
  private Boolean isUnderVersionControl = null;

  @SerializedName("edoc")
  private Edoc edoc = null;

  public Document elecDocumentSize(Long elecDocumentSize) {
    this.elecDocumentSize = elecDocumentSize;
    return this;
  }

   /**
   * The size of the electronic document attached to the represented document, if there is one, in bytes.
   * @return elecDocumentSize
  **/
  @Schema(description = "The size of the electronic document attached to the represented document, if there is one, in bytes.")
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
   * The extension for the document.
   * @return extension
  **/
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
   * A boolean indicating if there is an electronic document attached to the represented document.
   * @return isElectronicDocument
  **/
  @Schema(description = "A boolean indicating if there is an electronic document attached to the represented document.")
  public Boolean isIsElectronicDocument() {
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
   * A boolean indicating if the represented document is a record.
   * @return isRecord
  **/
  @Schema(description = "A boolean indicating if the represented document is a record.")
  public Boolean isIsRecord() {
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
   * The MIME type of the electronic document.
   * @return mimeType
  **/
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
   * The page count of the represented document.
   * @return pageCount
  **/
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
   * A boolean indicating if the represented document is checked out.
   * @return isCheckedOut
  **/
  @Schema(description = "A boolean indicating if the represented document is checked out.")
  public Boolean isIsCheckedOut() {
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
   * A boolean indicating if the represented document is under version control.
   * @return isUnderVersionControl
  **/
  @Schema(description = "A boolean indicating if the represented document is under version control.")
  public Boolean isIsUnderVersionControl() {
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
   * The electronic document attached to the represented document.
   * @return edoc
  **/
  @Schema(description = "")
  public Edoc getEdoc() {
    return edoc;
  }

  public void setEdoc(Edoc edoc) {
    this.edoc = edoc;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Document document = (Document) o;
    return Objects.equals(this.elecDocumentSize, document.elecDocumentSize) &&
        Objects.equals(this.extension, document.extension) &&
        Objects.equals(this.isElectronicDocument, document.isElectronicDocument) &&
        Objects.equals(this.isRecord, document.isRecord) &&
        Objects.equals(this.mimeType, document.mimeType) &&
        Objects.equals(this.pageCount, document.pageCount) &&
        Objects.equals(this.isCheckedOut, document.isCheckedOut) &&
        Objects.equals(this.isUnderVersionControl, document.isUnderVersionControl) &&
        Objects.equals(this.edoc, document.edoc) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(elecDocumentSize, extension, isElectronicDocument, isRecord, mimeType, pageCount, isCheckedOut, isUnderVersionControl, edoc, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Document {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    elecDocumentSize: ").append(toIndentedString(elecDocumentSize)).append("\n");
    sb.append("    extension: ").append(toIndentedString(extension)).append("\n");
    sb.append("    isElectronicDocument: ").append(toIndentedString(isElectronicDocument)).append("\n");
    sb.append("    isRecord: ").append(toIndentedString(isRecord)).append("\n");
    sb.append("    mimeType: ").append(toIndentedString(mimeType)).append("\n");
    sb.append("    pageCount: ").append(toIndentedString(pageCount)).append("\n");
    sb.append("    isCheckedOut: ").append(toIndentedString(isCheckedOut)).append("\n");
    sb.append("    isUnderVersionControl: ").append(toIndentedString(isUnderVersionControl)).append("\n");
    sb.append("    edoc: ").append(toIndentedString(edoc)).append("\n");
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
