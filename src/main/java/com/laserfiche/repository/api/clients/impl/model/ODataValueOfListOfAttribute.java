package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * ODataValueOfListOfAttribute
 */

public class ODataValueOfListOfAttribute {
  @SerializedName("value")
  private List<Attribute> value = null;

  public ODataValueOfListOfAttribute value(List<Attribute> value) {
    this.value = value;
    return this;
  }

  public ODataValueOfListOfAttribute addValueItem(Attribute valueItem) {
    if (this.value == null) {
      this.value = new ArrayList<Attribute>();
    }
    this.value.add(valueItem);
    return this;
  }

   /**
   * Get value
   * @return value
  **/
  @Schema(description = "")
  public List<Attribute> getValue() {
    return value;
  }

  public void setValue(List<Attribute> value) {
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
    ODataValueOfListOfAttribute odataValueOfListOfAttribute = (ODataValueOfListOfAttribute) o;
    return Objects.equals(this.value, odataValueOfListOfAttribute.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ODataValueOfListOfAttribute {\n");
    
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
