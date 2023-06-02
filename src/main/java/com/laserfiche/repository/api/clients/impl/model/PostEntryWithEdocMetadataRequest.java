package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen",
    date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostEntryWithEdocMetadataRequest {

  @JsonProperty("template")
  private String template = null;

  @JsonProperty("metadata")
  private PutFieldValsRequest metadata = null;

  @JsonProperty("volumeName")
  private String volumeName = null;

  public PostEntryWithEdocMetadataRequest template(String template) {
    this.template = template;
    return this;
  }

  /**
   * Returns the name of the template assigned to the entry.
   *
   * @return template
   */
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
   * Returns metadata
   *
   * @return metadata
   */
  @Schema(description = "")
  public PutFieldValsRequest getMetadata() {
    return metadata;
  }

  public void setMetadata(PutFieldValsRequest metadata) {
    this.metadata = metadata;
  }

  public PostEntryWithEdocMetadataRequest volumeName(String volumeName) {
    this.volumeName = volumeName;
    return this;
  }

  /**
   * Returns the name of the volume to use. Will use the default parent entry volume if not
   * specified. This is ignored in Laserfiche Cloud.
   *
   * @return volumeName
   */
  @Schema(
      description =
          "The name of the volume to use. Will use the default parent entry volume if not specified. This is ignored in Laserfiche Cloud.")
  public String getVolumeName() {
    return volumeName;
  }

  public void setVolumeName(String volumeName) {
    this.volumeName = volumeName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostEntryWithEdocMetadataRequest postEntryWithEdocMetadataRequest =
        (PostEntryWithEdocMetadataRequest) o;
    return Objects.equals(this.template, postEntryWithEdocMetadataRequest.template)
        && Objects.equals(this.metadata, postEntryWithEdocMetadataRequest.metadata)
        && Objects.equals(this.volumeName, postEntryWithEdocMetadataRequest.volumeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(template, metadata, volumeName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostEntryWithEdocMetadataRequest {\n");
    sb.append("    template: ").append(toIndentedString(template)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    volumeName: ").append(toIndentedString(volumeName)).append("\n");
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
