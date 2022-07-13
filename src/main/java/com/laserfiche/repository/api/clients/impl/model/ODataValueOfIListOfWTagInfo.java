package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * ODataValueOfIListOfWTagInfo
 */

public class ODataValueOfIListOfWTagInfo {
  @SerializedName("value")
  private List<WTagInfo> value = null;

  public ODataValueOfIListOfWTagInfo value(List<WTagInfo> value) {
    this.value = value;
    return this;
  }

  public ODataValueOfIListOfWTagInfo addValueItem(WTagInfo valueItem) {
    if (this.value == null) {
      this.value = new ArrayList<WTagInfo>();
    }
    this.value.add(valueItem);
    return this;
  }

   /**
   * Get value
   * @return value
  **/
  @Schema(description = "")
  public List<WTagInfo> getValue() {
    return value;
  }

  public void setValue(List<WTagInfo> value) {
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
    ODataValueOfIListOfWTagInfo odataValueOfIListOfWTagInfo = (ODataValueOfIListOfWTagInfo) o;
    return Objects.equals(this.value, odataValueOfIListOfWTagInfo.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ODataValueOfIListOfWTagInfo {\n");
    
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
