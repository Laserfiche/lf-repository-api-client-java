package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * CreateEntryResult
 */

public class CreateEntryResult {
  @SerializedName("operations")
  private CreateEntryOperations operations = null;

  @SerializedName("documentLink")
  private String documentLink = null;

  public CreateEntryResult operations(CreateEntryOperations operations) {
    this.operations = operations;
    return this;
  }

   /**
   * Get operations
   * @return operations
  **/
  @Schema(description = "")
  public CreateEntryOperations getOperations() {
    return operations;
  }

  public void setOperations(CreateEntryOperations operations) {
    this.operations = operations;
  }

  public CreateEntryResult documentLink(String documentLink) {
    this.documentLink = documentLink;
    return this;
  }

   /**
   * A link to get the created entry.
   * @return documentLink
  **/
  @Schema(description = "A link to get the created entry.")
  public String getDocumentLink() {
    return documentLink;
  }

  public void setDocumentLink(String documentLink) {
    this.documentLink = documentLink;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateEntryResult createEntryResult = (CreateEntryResult) o;
    return Objects.equals(this.operations, createEntryResult.operations) &&
        Objects.equals(this.documentLink, createEntryResult.documentLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operations, documentLink);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateEntryResult {\n");
    
    sb.append("    operations: ").append(toIndentedString(operations)).append("\n");
    sb.append("    documentLink: ").append(toIndentedString(documentLink)).append("\n");
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
