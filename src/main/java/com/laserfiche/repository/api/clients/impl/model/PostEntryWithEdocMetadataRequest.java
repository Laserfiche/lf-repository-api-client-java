package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
/**
 * PostEntryWithEdocMetadataRequest
 */

public class PostEntryWithEdocMetadataRequest {
  @SerializedName("template")
  private String template = null;

  @SerializedName("metadata")
  private PutFieldValsRequest metadata = null;

  public PostEntryWithEdocMetadataRequest template(String template) {
    this.template = template;
    return this;
  }

   /**
   * The name of the template assigned to the entry.
   * @return template
  **/
  @Schema(description = "The name of the template assigned to the entry.")
  public String getTemplate() {
    return template;
  }

  public void setTemplate(String template) {
    this.template = template;
  }

  public PostEntryWithEdocMetadataRequest metadata(PutFieldValsRequest metadata) {
    this.metadata = metadata;
    return this;
  }

   /**
   * Get metadata
   * @return metadata
  **/
  @Schema(description = "")
  public PutFieldValsRequest getMetadata() {
    return metadata;
  }

  public void setMetadata(PutFieldValsRequest metadata) {
    this.metadata = metadata;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostEntryWithEdocMetadataRequest postEntryWithEdocMetadataRequest = (PostEntryWithEdocMetadataRequest) o;
    return Objects.equals(this.template, postEntryWithEdocMetadataRequest.template) &&
        Objects.equals(this.metadata, postEntryWithEdocMetadataRequest.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(template, metadata);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostEntryWithEdocMetadataRequest {\n");
    
    sb.append("    template: ").append(toIndentedString(template)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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
