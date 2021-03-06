package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * ODataValueOfIListOfWTemplateInfo
 */

public class ODataValueOfIListOfWTemplateInfo {
  @SerializedName("value")
  private List<WTemplateInfo> value = null;

  public ODataValueOfIListOfWTemplateInfo value(List<WTemplateInfo> value) {
    this.value = value;
    return this;
  }

  public ODataValueOfIListOfWTemplateInfo addValueItem(WTemplateInfo valueItem) {
    if (this.value == null) {
      this.value = new ArrayList<WTemplateInfo>();
    }
    this.value.add(valueItem);
    return this;
  }

   /**
   * Get value
   * @return value
  **/
  @Schema(description = "")
  public List<WTemplateInfo> getValue() {
    return value;
  }

  public void setValue(List<WTemplateInfo> value) {
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
    ODataValueOfIListOfWTemplateInfo odataValueOfIListOfWTemplateInfo = (ODataValueOfIListOfWTemplateInfo) o;
    return Objects.equals(this.value, odataValueOfIListOfWTemplateInfo.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ODataValueOfIListOfWTemplateInfo {\n");
    
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
