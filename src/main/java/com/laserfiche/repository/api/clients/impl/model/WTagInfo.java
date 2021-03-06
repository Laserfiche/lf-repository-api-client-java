package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
/**
 * WTagInfo
 */

public class WTagInfo {
  @SerializedName("id")
  private Integer id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("displayName")
  private String displayName = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("isSecure")
  private Boolean isSecure = null;

  @SerializedName("watermark")
  private Watermark watermark = null;

  public WTagInfo id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * The ID of the tag definition.
   * @return id
  **/
  @Schema(description = "The ID of the tag definition.")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public WTagInfo name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The name of the tag definition.
   * @return name
  **/
  @Schema(description = "The name of the tag definition.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public WTagInfo displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

   /**
   * The localized name of the tag definition.
   * @return displayName
  **/
  @Schema(description = "The localized name of the tag definition.")
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public WTagInfo description(String description) {
    this.description = description;
    return this;
  }

   /**
   * The description of the tag definition.
   * @return description
  **/
  @Schema(description = "The description of the tag definition.")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public WTagInfo isSecure(Boolean isSecure) {
    this.isSecure = isSecure;
    return this;
  }

   /**
   * A boolean indicating whether or not the tag definition is classified as a security tag (true) or an informational tag (false).
   * @return isSecure
  **/
  @Schema(description = "A boolean indicating whether or not the tag definition is classified as a security tag (true) or an informational tag (false).")
  public Boolean isIsSecure() {
    return isSecure;
  }

  public void setIsSecure(Boolean isSecure) {
    this.isSecure = isSecure;
  }

  public WTagInfo watermark(Watermark watermark) {
    this.watermark = watermark;
    return this;
  }

   /**
   * The watermark properties associated with the tag definition.
   * @return watermark
  **/
  @Schema(description = "The watermark properties associated with the tag definition.")
  public Watermark getWatermark() {
    return watermark;
  }

  public void setWatermark(Watermark watermark) {
    this.watermark = watermark;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WTagInfo wtagInfo = (WTagInfo) o;
    return Objects.equals(this.id, wtagInfo.id) &&
        Objects.equals(this.name, wtagInfo.name) &&
        Objects.equals(this.displayName, wtagInfo.displayName) &&
        Objects.equals(this.description, wtagInfo.description) &&
        Objects.equals(this.isSecure, wtagInfo.isSecure) &&
        Objects.equals(this.watermark, wtagInfo.watermark);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, displayName, description, isSecure, watermark);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WTagInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    isSecure: ").append(toIndentedString(isSecure)).append("\n");
    sb.append("    watermark: ").append(toIndentedString(watermark)).append("\n");
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
