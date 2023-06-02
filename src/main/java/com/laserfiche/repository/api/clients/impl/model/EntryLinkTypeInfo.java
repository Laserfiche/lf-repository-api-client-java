package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen",
    date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntryLinkTypeInfo {

  @JsonProperty("linkTypeId")
  private Integer linkTypeId = null;

  @JsonProperty("sourceLabel")
  private String sourceLabel = null;

  @JsonProperty("targetLabel")
  private String targetLabel = null;

  @JsonProperty("linkTypeDescription")
  private String linkTypeDescription = null;

  public EntryLinkTypeInfo linkTypeId(Integer linkTypeId) {
    this.linkTypeId = linkTypeId;
    return this;
  }

  /**
   * Returns the ID of the entry link type.
   *
   * @return linkTypeId
   */
  @Schema(description = "The ID of the entry link type.")
  public Integer getLinkTypeId() {
    return linkTypeId;
  }

  public void setLinkTypeId(Integer linkTypeId) {
    this.linkTypeId = linkTypeId;
  }

  public EntryLinkTypeInfo sourceLabel(String sourceLabel) {
    this.sourceLabel = sourceLabel;
    return this;
  }

  /**
   * Returns the label for the source entry in the link type.
   *
   * @return sourceLabel
   */
  @Schema(description = "The label for the source entry in the link type.")
  public String getSourceLabel() {
    return sourceLabel;
  }

  public void setSourceLabel(String sourceLabel) {
    this.sourceLabel = sourceLabel;
  }

  public EntryLinkTypeInfo targetLabel(String targetLabel) {
    this.targetLabel = targetLabel;
    return this;
  }

  /**
   * Returns the label for the target entry in the link type.
   *
   * @return targetLabel
   */
  @Schema(description = "The label for the target entry in the link type.")
  public String getTargetLabel() {
    return targetLabel;
  }

  public void setTargetLabel(String targetLabel) {
    this.targetLabel = targetLabel;
  }

  public EntryLinkTypeInfo linkTypeDescription(String linkTypeDescription) {
    this.linkTypeDescription = linkTypeDescription;
    return this;
  }

  /**
   * Returns the description of the link type.
   *
   * @return linkTypeDescription
   */
  @Schema(description = "The description of the link type.")
  public String getLinkTypeDescription() {
    return linkTypeDescription;
  }

  public void setLinkTypeDescription(String linkTypeDescription) {
    this.linkTypeDescription = linkTypeDescription;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EntryLinkTypeInfo entryLinkTypeInfo = (EntryLinkTypeInfo) o;
    return Objects.equals(this.linkTypeId, entryLinkTypeInfo.linkTypeId)
        && Objects.equals(this.sourceLabel, entryLinkTypeInfo.sourceLabel)
        && Objects.equals(this.targetLabel, entryLinkTypeInfo.targetLabel)
        && Objects.equals(this.linkTypeDescription, entryLinkTypeInfo.linkTypeDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(linkTypeId, sourceLabel, targetLabel, linkTypeDescription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EntryLinkTypeInfo {\n");
    sb.append("    linkTypeId: ").append(toIndentedString(linkTypeId)).append("\n");
    sb.append("    sourceLabel: ").append(toIndentedString(sourceLabel)).append("\n");
    sb.append("    targetLabel: ").append(toIndentedString(targetLabel)).append("\n");
    sb.append("    linkTypeDescription: ")
        .append(toIndentedString(linkTypeDescription))
        .append("\n");
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
