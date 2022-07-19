package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.File;
import java.util.Objects;
/**
 * ParentEntryIdFileNameBody
 */

public class ParentEntryIdFileNameBody {
  @SerializedName("electronicDocument")
  private File electronicDocument = null;

  @SerializedName("request")
  private PostEntryWithEdocMetadataRequest request = null;

  public ParentEntryIdFileNameBody electronicDocument(File electronicDocument) {
    this.electronicDocument = electronicDocument;
    return this;
  }

   /**
   * Get electronicDocument
   * @return electronicDocument
  **/
  @Schema(description = "")
  public File getElectronicDocument() {
    return electronicDocument;
  }

  public void setElectronicDocument(File electronicDocument) {
    this.electronicDocument = electronicDocument;
  }

  public ParentEntryIdFileNameBody request(PostEntryWithEdocMetadataRequest request) {
    this.request = request;
    return this;
  }

   /**
   * Get request
   * @return request
  **/
  @Schema(description = "")
  public PostEntryWithEdocMetadataRequest getRequest() {
    return request;
  }

  public void setRequest(PostEntryWithEdocMetadataRequest request) {
    this.request = request;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParentEntryIdFileNameBody parentEntryIdFileNameBody = (ParentEntryIdFileNameBody) o;
    return Objects.equals(this.electronicDocument, parentEntryIdFileNameBody.electronicDocument) &&
        Objects.equals(this.request, parentEntryIdFileNameBody.request);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Objects.hashCode(electronicDocument), request);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParentEntryIdFileNameBody {\n");
    
    sb.append("    electronicDocument: ").append(toIndentedString(electronicDocument)).append("\n");
    sb.append("    request: ").append(toIndentedString(request)).append("\n");
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
