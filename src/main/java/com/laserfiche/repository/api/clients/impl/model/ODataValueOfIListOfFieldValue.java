package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * ODataValueOfIListOfFieldValue
 */

public class ODataValueOfIListOfFieldValue {
  @SerializedName("value")
  private List<FieldValue> value = null;

  public ODataValueOfIListOfFieldValue value(List<FieldValue> value) {
    this.value = value;
    return this;
  }

  public ODataValueOfIListOfFieldValue addValueItem(FieldValue valueItem) {
    if (this.value == null) {
      this.value = new ArrayList<FieldValue>();
    }
    this.value.add(valueItem);
    return this;
  }

   /**
   * Get value
   * @return value
  **/
  @Schema(description = "")
  public List<FieldValue> getValue() {
    return value;
  }

  public void setValue(List<FieldValue> value) {
    this.value = value;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ODataValueOfIListOfFieldValue odataValueOfIListOfFieldValue = (ODataValueOfIListOfFieldValue) o;
    return Objects.equals(this.value, odataValueOfIListOfFieldValue.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ODataValueOfIListOfFieldValue {\n");
    
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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
