package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
/**
 * Shortcut
 */

public class Shortcut extends Entry {
  @SerializedName("targetId")
  private Integer targetId = null;

  @SerializedName("extension")
  private String extension = null;

  @SerializedName("targetType")
  private EntryType targetType = null;

  public Shortcut targetId(Integer targetId) {
    this.targetId = targetId;
    return this;
  }

   /**
   * The entry ID of the shortcut target.
   * @return targetId
  **/
  @Schema(description = "The entry ID of the shortcut target.")
  public Integer getTargetId() {
    return targetId;
  }

  public void setTargetId(Integer targetId) {
    this.targetId = targetId;
  }

  public Shortcut extension(String extension) {
    this.extension = extension;
    return this;
  }

   /**
   * The extension of the shortcut target.
   * @return extension
  **/
  @Schema(description = "The extension of the shortcut target.")
  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  public Shortcut targetType(EntryType targetType) {
    this.targetType = targetType;
    return this;
  }

   /**
   * Get targetType
   * @return targetType
  **/
  @Schema(description = "")
  public EntryType getTargetType() {
    return targetType;
  }

  public void setTargetType(EntryType targetType) {
    this.targetType = targetType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Shortcut shortcut = (Shortcut) o;
    return Objects.equals(this.targetId, shortcut.targetId) &&
        Objects.equals(this.extension, shortcut.extension) &&
        Objects.equals(this.targetType, shortcut.targetType) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(targetId, extension, targetType, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Shortcut {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    targetId: ").append(toIndentedString(targetId)).append("\n");
    sb.append("    extension: ").append(toIndentedString(extension)).append("\n");
    sb.append("    targetType: ").append(toIndentedString(targetType)).append("\n");
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
