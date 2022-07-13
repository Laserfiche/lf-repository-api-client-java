package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
/**
 * WTemplateInfo
 */

public class WTemplateInfo {
  @SerializedName("id")
  private Integer id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("displayName")
  private String displayName = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("color")
  private LFColor color = null;

  @SerializedName("fieldCount")
  private Integer fieldCount = null;

  public WTemplateInfo id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * The ID of the template definition.
   * @return id
  **/
  @Schema(description = "The ID of the template definition.")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public WTemplateInfo name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The name of the template definition.
   * @return name
  **/
  @Schema(description = "The name of the template definition.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public WTemplateInfo displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

   /**
   * The localized name of the template definition.
   * @return displayName
  **/
  @Schema(description = "The localized name of the template definition.")
  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public WTemplateInfo description(String description) {
    this.description = description;
    return this;
  }

   /**
   * The description of the template definition.
   * @return description
  **/
  @Schema(description = "The description of the template definition.")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public WTemplateInfo color(LFColor color) {
    this.color = color;
    return this;
  }

   /**
   * The color assigned to the template definition.
   * @return color
  **/
  @Schema(description = "The color assigned to the template definition.")
  public LFColor getColor() {
    return color;
  }

  public void setColor(LFColor color) {
    this.color = color;
  }

  public WTemplateInfo fieldCount(Integer fieldCount) {
    this.fieldCount = fieldCount;
    return this;
  }

   /**
   * The number of fields assigned to the template.
   * @return fieldCount
  **/
  @Schema(description = "The number of fields assigned to the template.")
  public Integer getFieldCount() {
    return fieldCount;
  }

  public void setFieldCount(Integer fieldCount) {
    this.fieldCount = fieldCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WTemplateInfo wtemplateInfo = (WTemplateInfo) o;
    return Objects.equals(this.id, wtemplateInfo.id) &&
        Objects.equals(this.name, wtemplateInfo.name) &&
        Objects.equals(this.displayName, wtemplateInfo.displayName) &&
        Objects.equals(this.description, wtemplateInfo.description) &&
        Objects.equals(this.color, wtemplateInfo.color) &&
        Objects.equals(this.fieldCount, wtemplateInfo.fieldCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, displayName, description, color, fieldCount);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WTemplateInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    fieldCount: ").append(toIndentedString(fieldCount)).append("\n");
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
