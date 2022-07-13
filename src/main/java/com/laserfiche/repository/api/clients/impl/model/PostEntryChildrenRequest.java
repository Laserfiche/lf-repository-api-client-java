package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
/**
 * PostEntryChildrenRequest
 */

public class PostEntryChildrenRequest {
  @SerializedName("name")
  private String name = null;

  @SerializedName("entryType")
  private PostEntryChildrenEntryType entryType = null;

  @SerializedName("targetId")
  private Integer targetId = null;

  @SerializedName("sourceId")
  private Integer sourceId = null;

  public PostEntryChildrenRequest name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The name of the entry.
   * @return name
  **/
  @Schema(description = "The name of the entry.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PostEntryChildrenRequest entryType(PostEntryChildrenEntryType entryType) {
    this.entryType = entryType;
    return this;
  }

   /**
   * The type of the entry.
   * @return entryType
  **/
  @Schema(description = "The type of the entry.")
  public PostEntryChildrenEntryType getEntryType() {
    return entryType;
  }

  public void setEntryType(PostEntryChildrenEntryType entryType) {
    this.entryType = entryType;
  }

  public PostEntryChildrenRequest targetId(Integer targetId) {
    this.targetId = targetId;
    return this;
  }

   /**
   * The TargetId is only needed for creating a shortcut. This will be the entry ID of the shortcut target.
   * @return targetId
  **/
  @Schema(description = "The TargetId is only needed for creating a shortcut. This will be the entry ID of the shortcut target.")
  public Integer getTargetId() {
    return targetId;
  }

  public void setTargetId(Integer targetId) {
    this.targetId = targetId;
  }

  public PostEntryChildrenRequest sourceId(Integer sourceId) {
    this.sourceId = sourceId;
    return this;
  }

   /**
   * The SourceId is needed for some operations that require a source/destination. One example is the Copy operation.
   * @return sourceId
  **/
  @Schema(description = "The SourceId is needed for some operations that require a source/destination. One example is the Copy operation.")
  public Integer getSourceId() {
    return sourceId;
  }

  public void setSourceId(Integer sourceId) {
    this.sourceId = sourceId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostEntryChildrenRequest postEntryChildrenRequest = (PostEntryChildrenRequest) o;
    return Objects.equals(this.name, postEntryChildrenRequest.name) &&
        Objects.equals(this.entryType, postEntryChildrenRequest.entryType) &&
        Objects.equals(this.targetId, postEntryChildrenRequest.targetId) &&
        Objects.equals(this.sourceId, postEntryChildrenRequest.sourceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, entryType, targetId, sourceId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostEntryChildrenRequest {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    entryType: ").append(toIndentedString(entryType)).append("\n");
    sb.append("    targetId: ").append(toIndentedString(targetId)).append("\n");
    sb.append("    sourceId: ").append(toIndentedString(sourceId)).append("\n");
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
