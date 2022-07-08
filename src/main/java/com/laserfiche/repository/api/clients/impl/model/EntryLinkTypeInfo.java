/*
 * Laserfiche Repository API
 * Welcome to the Laserfiche API Swagger Playground. You can try out any of our API calls against your live Laserfiche Cloud account. Visit the developer center for more details: <a href=\"https://developer.laserfiche.com\">https://developer.laserfiche.com</a><p><strong>Build# : </strong>cebe86be04beaa6b58c2370e4409705464caf74f_.20220704.3</p>
 *
 * OpenAPI spec version: 1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * EntryLinkTypeInfo
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-07-07T17:21:52.208737600-04:00[America/New_York]")
public class EntryLinkTypeInfo {
  @SerializedName("linkTypeId")
  private Integer linkTypeId = null;

  @SerializedName("sourceLabel")
  private String sourceLabel = null;

  @SerializedName("targetLabel")
  private String targetLabel = null;

  @SerializedName("linkTypeDescription")
  private String linkTypeDescription = null;

  public EntryLinkTypeInfo linkTypeId(Integer linkTypeId) {
    this.linkTypeId = linkTypeId;
    return this;
  }

   /**
   * The ID of the entry link type.
   * @return linkTypeId
  **/
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
   * The label for the source entry in the link type.
   * @return sourceLabel
  **/
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
   * The label for the target entry in the link type.
   * @return targetLabel
  **/
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
   * The description of the link type.
   * @return linkTypeDescription
  **/
  @Schema(description = "The description of the link type.")
  public String getLinkTypeDescription() {
    return linkTypeDescription;
  }

  public void setLinkTypeDescription(String linkTypeDescription) {
    this.linkTypeDescription = linkTypeDescription;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EntryLinkTypeInfo entryLinkTypeInfo = (EntryLinkTypeInfo) o;
    return Objects.equals(this.linkTypeId, entryLinkTypeInfo.linkTypeId) &&
        Objects.equals(this.sourceLabel, entryLinkTypeInfo.sourceLabel) &&
        Objects.equals(this.targetLabel, entryLinkTypeInfo.targetLabel) &&
        Objects.equals(this.linkTypeDescription, entryLinkTypeInfo.linkTypeDescription);
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
    sb.append("    linkTypeDescription: ").append(toIndentedString(linkTypeDescription)).append("\n");
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