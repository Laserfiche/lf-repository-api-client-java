package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
/**
 * ValueToUpdate
 */

public class ValueToUpdate {
  @SerializedName("value")
  private String value = null;

  @SerializedName("position")
  private Integer position = null;

  public ValueToUpdate value(String value) {
    this.value = value;
    return this;
  }

   /**
   * The value assigned to the field at the position specified.
   * @return value
  **/
  @Schema(description = "The value assigned to the field at the position specified.")
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public ValueToUpdate position(Integer position) {
    this.position = position;
    return this;
  }

   /**
   * The position of the value in the field. This is 1-indexed for multi value field. It will be ignored for single value field.
   * @return position
  **/
  @Schema(description = "The position of the value in the field. This is 1-indexed for multi value field. It will be ignored for single value field.")
  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValueToUpdate valueToUpdate = (ValueToUpdate) o;
    return Objects.equals(this.value, valueToUpdate.value) &&
        Objects.equals(this.position, valueToUpdate.position);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, position);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValueToUpdate {\n");
    
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
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
