package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * ODataValueOfIListOfContextHit
 */

public class ODataValueOfIListOfContextHit {
  @SerializedName("value")
  private List<ContextHit> value = null;

  public ODataValueOfIListOfContextHit value(List<ContextHit> value) {
    this.value = value;
    return this;
  }

  public ODataValueOfIListOfContextHit addValueItem(ContextHit valueItem) {
    if (this.value == null) {
      this.value = new ArrayList<ContextHit>();
    }
    this.value.add(valueItem);
    return this;
  }

   /**
   * Get value
   * @return value
  **/
  @Schema(description = "")
  public List<ContextHit> getValue() {
    return value;
  }

  public void setValue(List<ContextHit> value) {
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
    ODataValueOfIListOfContextHit odataValueOfIListOfContextHit = (ODataValueOfIListOfContextHit) o;
    return Objects.equals(this.value, odataValueOfIListOfContextHit.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ODataValueOfIListOfContextHit {\n");
    
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
