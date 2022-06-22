/*
 * Laserfiche Repository API
 * Welcome to the Laserfiche API Swagger Playground. You can try out any of our API calls against your live Laserfiche Cloud account. Visit the developer center for more details: <a href=\"https://developer.laserfiche.com\">https://developer.laserfiche.com</a><p><strong>Build# : </strong>eb72c77d640efc5f986310ccc43114fc25742dab_.20220610.1</p>
 *
 * OpenAPI spec version: 2-alpha
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.laserfiche.repository.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.laserfiche.repository.api.client.model.SimpleImportRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.File;
import java.io.IOException;
/**
 * ParentEntryIdImportBody
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-06-21T09:31:30.649462900-04:00[America/New_York]")
public class ParentEntryIdImportBody {
  @SerializedName("file")
  private File file = null;

  @SerializedName("request")
  private SimpleImportRequest request = null;

  public ParentEntryIdImportBody file(File file) {
    this.file = file;
    return this;
  }

   /**
   * Get file
   * @return file
  **/
  @Schema(description = "")
  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public ParentEntryIdImportBody request(SimpleImportRequest request) {
    this.request = request;
    return this;
  }

   /**
   * Get request
   * @return request
  **/
  @Schema(description = "")
  public SimpleImportRequest getRequest() {
    return request;
  }

  public void setRequest(SimpleImportRequest request) {
    this.request = request;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ParentEntryIdImportBody parentEntryIdImportBody = (ParentEntryIdImportBody) o;
    return Objects.equals(this.file, parentEntryIdImportBody.file) &&
        Objects.equals(this.request, parentEntryIdImportBody.request);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Objects.hashCode(file), request);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ParentEntryIdImportBody {\n");
    
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
    sb.append("    request: ").append(toIndentedString(request)).append("\n");
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