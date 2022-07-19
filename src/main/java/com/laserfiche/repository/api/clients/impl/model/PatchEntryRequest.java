package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
/**
 * PatchEntryRequest
 */

public class PatchEntryRequest {
  @SerializedName("parentId")
  private Integer parentId = null;

  @SerializedName("name")
  private String name = null;

  public PatchEntryRequest parentId(Integer parentId) {
    this.parentId = parentId;
    return this;
  }

   /**
   * The ID of the parent entry that the entry will be moved to.
   * @return parentId
  **/
  @Schema(description = "The ID of the parent entry that the entry will be moved to.")
  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public PatchEntryRequest name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The name that will be assigned to the entry.
   * @return name
  **/
  @Schema(description = "The name that will be assigned to the entry.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PatchEntryRequest patchEntryRequest = (PatchEntryRequest) o;
    return Objects.equals(this.parentId, patchEntryRequest.parentId) &&
        Objects.equals(this.name, patchEntryRequest.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parentId, name);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PatchEntryRequest {\n");
    
    sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
