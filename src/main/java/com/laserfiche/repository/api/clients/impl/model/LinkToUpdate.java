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
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
/**
 * LinkToUpdate
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-07-07T17:21:52.208737600-04:00[America/New_York]")
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
