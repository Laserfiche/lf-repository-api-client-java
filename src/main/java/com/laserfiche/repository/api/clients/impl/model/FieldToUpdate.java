package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * The request body containing fields that will be assigned to the entry.
 */

public class FieldToUpdate {
  @SerializedName("values")
  private List<ValueToUpdate> values = null;

  public FieldToUpdate values(List<ValueToUpdate> values) {
    this.values = values;
    return this;
  }

  public FieldToUpdate addValuesItem(ValueToUpdate valuesItem) {
    if (this.values == null) {
      this.values = new ArrayList<ValueToUpdate>();
    }
    this.values.add(valuesItem);
    return this;
  }

   /**
   * The field values that will be assigned to the field.
   * @return values
  **/
  @Schema(description = "The field values that will be assigned to the field.")
  public List<ValueToUpdate> getValues() {
    return values;
  }

  public void setValues(List<ValueToUpdate> values) {
    this.values = values;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FieldToUpdate fieldToUpdate = (FieldToUpdate) o;
    return Objects.equals(this.values, fieldToUpdate.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(values);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FieldToUpdate {\n");
    
    sb.append("    values: ").append(toIndentedString(values)).append("\n");
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
