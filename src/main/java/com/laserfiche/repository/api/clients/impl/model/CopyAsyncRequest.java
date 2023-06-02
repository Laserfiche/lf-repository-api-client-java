package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CopyAsyncRequest {

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("sourceId")
  private Integer sourceId = null;

  @JsonProperty("volumeName")
  private String volumeName = null;

  public CopyAsyncRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Returns he name of the entry.
   *
   * @return name
   */
  @Schema(description = "The name of the entry.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CopyAsyncRequest sourceId(Integer sourceId) {
    this.sourceId = sourceId;
    return this;
  }

  /**
   * Returns he source entry Id to copy.
   *
   * @return sourceId
   */
  @Schema(description = "The source entry Id to copy.")
  public Integer getSourceId() {
    return sourceId;
  }

  public void setSourceId(Integer sourceId) {
    this.sourceId = sourceId;
  }

  public CopyAsyncRequest volumeName(String volumeName) {
    this.volumeName = volumeName;
    return this;
  }

  /**
   * Returns he name of the volume to use. Will use the default parent entry volume if not
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
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CopyAsyncRequest copyAsyncRequest = (CopyAsyncRequest) o;
    return Objects.equals(this.name, copyAsyncRequest.name)
        && Objects.equals(this.sourceId, copyAsyncRequest.sourceId)
        && Objects.equals(this.volumeName, copyAsyncRequest.volumeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, sourceId, volumeName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CopyAsyncRequest {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    sourceId: ").append(toIndentedString(sourceId)).append("\n");
    sb.append("    volumeName: ").append(toIndentedString(volumeName)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
