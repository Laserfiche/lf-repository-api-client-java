package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * CopyAsyncRequest
 */

public class CopyAsyncRequest {
  @SerializedName("name")
  private String name = null;

  @SerializedName("sourceId")
  private Integer sourceId = null;

  public CopyAsyncRequest name(String name) {
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

  public CopyAsyncRequest sourceId(Integer sourceId) {
    this.sourceId = sourceId;
    return this;
  }

   /**
   * The source entry Id to copy.
   * @return sourceId
  **/
  @Schema(description = "The source entry Id to copy.")
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
    CopyAsyncRequest copyAsyncRequest = (CopyAsyncRequest) o;
    return Objects.equals(this.name, copyAsyncRequest.name) &&
        Objects.equals(this.sourceId, copyAsyncRequest.sourceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, sourceId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CopyAsyncRequest {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
