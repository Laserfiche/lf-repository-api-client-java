/*
 * Laserfiche Repository API
 * Welcome to the Laserfiche API Swagger Playground. You can try out any of our API calls against your live Laserfiche Cloud account. Visit the developer center for more details: <a href=\"https://developer.laserfiche.com\">https://developer.laserfiche.com</a><p><strong>Build# : </strong>51c16645afa5983c3eb4a849158d6f1e355d2bb0_.20220512.1</p>
 *
 * OpenAPI spec version: 1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.laserfiche.api.client.model.WEntryLinkInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * ODataValueOfIListOfWEntryLinkInfo
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-05-16T11:14:25.016422500-04:00[America/New_York]")
public class ODataValueOfIListOfWEntryLinkInfo {
  @SerializedName("value")
  private List<WEntryLinkInfo> value = null;

  public ODataValueOfIListOfWEntryLinkInfo value(List<WEntryLinkInfo> value) {
    this.value = value;
    return this;
  }

  public ODataValueOfIListOfWEntryLinkInfo addValueItem(WEntryLinkInfo valueItem) {
    if (this.value == null) {
      this.value = new ArrayList<WEntryLinkInfo>();
    }
    this.value.add(valueItem);
    return this;
  }

   /**
   * Get value
   * @return value
  **/
  @Schema(description = "")
  public List<WEntryLinkInfo> getValue() {
    return value;
  }

  public void setValue(List<WEntryLinkInfo> value) {
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
    ODataValueOfIListOfWEntryLinkInfo odataValueOfIListOfWEntryLinkInfo = (ODataValueOfIListOfWEntryLinkInfo) o;
    return Objects.equals(this.value, odataValueOfIListOfWEntryLinkInfo.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ODataValueOfIListOfWEntryLinkInfo {\n");
    
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
