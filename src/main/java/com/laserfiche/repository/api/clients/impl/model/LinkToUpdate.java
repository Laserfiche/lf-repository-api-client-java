package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen",
    date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LinkToUpdate {

  @JsonProperty("linkTypeId")
  private Integer linkTypeId = null;

  @JsonProperty("otherSourceId")
  private Integer otherSourceId = null;

  @JsonProperty("isSource")
  private Boolean isSource = null;

  public LinkToUpdate linkTypeId(Integer linkTypeId) {
    this.linkTypeId = linkTypeId;
    return this;
  }

  /**
   * Returns the id of the link assigned to the entry.
   *
   * @return linkTypeId
   */
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
   * Returns the id of the other source linked to the entry.
   *
   * @return otherSourceId
   */
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
   * Returns whether the entry is the source for the link.
   *
   * @return isSource
   */
  @Schema(description = "Whether the entry is the source for the link.")
  @JsonProperty("isSource")
  public Boolean isSource() {
    return isSource;
  }

  public void setIsSource(Boolean isSource) {
    this.isSource = isSource;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LinkToUpdate linkToUpdate = (LinkToUpdate) o;
    return Objects.equals(this.linkTypeId, linkToUpdate.linkTypeId)
        && Objects.equals(this.otherSourceId, linkToUpdate.otherSourceId)
        && Objects.equals(this.isSource, linkToUpdate.isSource);
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

  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
