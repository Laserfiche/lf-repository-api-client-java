package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
/**
 * LinkToUpdate
 */

public class LinkToUpdate {
  @SerializedName("linkTypeId")
  private Integer linkTypeId = null;

  @SerializedName("otherSourceId")
  private Integer otherSourceId = null;

  @SerializedName("isSource")
  private Boolean isSource = null;

  public LinkToUpdate linkTypeId(Integer linkTypeId) {
    this.linkTypeId = linkTypeId;
    return this;
  }

   /**
   * The id of the link assigned to the entry.
   * @return linkTypeId
  **/
  @Schema(description = "The id of the link assigned to the entry.")
  public Integer getLinkTypeId() {
    return linkTypeId;
  }

  public void setLinkTypeId(Integer linkTypeId) {
    this.linkTypeId = linkTypeId;
  }

  public LinkToUpdate otherSourceId(Integer otherSourceId) {
    this.otherSourceId = otherSourceId;
    return this;
  }

   /**
   * The id of the other source linked to the entry.
   * @return otherSourceId
  **/
  @Schema(description = "The id of the other source linked to the entry.")
  public Integer getOtherSourceId() {
    return otherSourceId;
  }

  public void setOtherSourceId(Integer otherSourceId) {
    this.otherSourceId = otherSourceId;
  }

  public LinkToUpdate isSource(Boolean isSource) {
    this.isSource = isSource;
    return this;
  }

   /**
   * Whether the entry is the source for the link.
   * @return isSource
  **/
  @Schema(description = "Whether the entry is the source for the link.")
  public Boolean isIsSource() {
    return isSource;
  }

  public void setIsSource(Boolean isSource) {
    this.isSource = isSource;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LinkToUpdate linkToUpdate = (LinkToUpdate) o;
    return Objects.equals(this.linkTypeId, linkToUpdate.linkTypeId) &&
        Objects.equals(this.otherSourceId, linkToUpdate.otherSourceId) &&
        Objects.equals(this.isSource, linkToUpdate.isSource);
  }

  @Override
  public int hashCode() {
    return Objects.hash(linkTypeId, otherSourceId, isSource);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LinkToUpdate {\n");
    
    sb.append("    linkTypeId: ").append(toIndentedString(linkTypeId)).append("\n");
    sb.append("    otherSourceId: ").append(toIndentedString(otherSourceId)).append("\n");
    sb.append("    isSource: ").append(toIndentedString(isSource)).append("\n");
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
