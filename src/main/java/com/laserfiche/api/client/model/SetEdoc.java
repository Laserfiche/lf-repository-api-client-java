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
import com.laserfiche.api.client.model.APIServerException;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * The result of trying to create the electronic document.
 */
@Schema(description = "The result of trying to create the electronic document.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-05-16T11:14:25.016422500-04:00[America/New_York]")
public class SetEdoc {
  @SerializedName("exceptions")
  private List<APIServerException> exceptions = null;

  public SetEdoc exceptions(List<APIServerException> exceptions) {
    this.exceptions = exceptions;
    return this;
  }

  public SetEdoc addExceptionsItem(APIServerException exceptionsItem) {
    if (this.exceptions == null) {
      this.exceptions = new ArrayList<APIServerException>();
    }
    this.exceptions.add(exceptionsItem);
    return this;
  }

   /**
   * The list of exceptions that occured when trying to perform the operation.
   * @return exceptions
  **/
  @Schema(description = "The list of exceptions that occured when trying to perform the operation.")
  public List<APIServerException> getExceptions() {
    return exceptions;
  }

  public void setExceptions(List<APIServerException> exceptions) {
    this.exceptions = exceptions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SetEdoc setEdoc = (SetEdoc) o;
    return Objects.equals(this.exceptions, setEdoc.exceptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(exceptions);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SetEdoc {\n");
    
    sb.append("    exceptions: ").append(toIndentedString(exceptions)).append("\n");
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
