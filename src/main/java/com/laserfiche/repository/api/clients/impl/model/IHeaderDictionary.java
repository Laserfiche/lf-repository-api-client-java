/*
 * Laserfiche Repository API
 * Welcome to the Laserfiche API Swagger Playground. You can try out any of our API calls against your live Laserfiche Cloud account. Visit the developer center for more details: <a href=\"https://developer.laserfiche.com\">https://developer.laserfiche.com</a><p><strong>Build# : </strong>eb72c77d640efc5f986310ccc43114fc25742dab_.20220610.1</p>
 *
 * OpenAPI spec version: 1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
/**
 * IHeaderDictionary
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-06-21T09:31:30.649462900-04:00[America/New_York]")
public class IHeaderDictionary {
  @SerializedName("Item")
  private List<Object> item = null;

  @SerializedName("ContentLength")
  private Long contentLength = null;

  public IHeaderDictionary item(List<Object> item) {
    this.item = item;
    return this;
  }

  public IHeaderDictionary addItemItem(Object itemItem) {
    if (this.item == null) {
      this.item = new ArrayList<Object>();
    }
    this.item.add(itemItem);
    return this;
  }

   /**
   * Get item
   * @return item
  **/
  @Schema(description = "")
  public List<Object> getItem() {
    return item;
  }

  public void setItem(List<Object> item) {
    this.item = item;
  }

  public IHeaderDictionary contentLength(Long contentLength) {
    this.contentLength = contentLength;
    return this;
  }

   /**
   * Get contentLength
   * @return contentLength
  **/
  @Schema(description = "")
  public Long getContentLength() {
    return contentLength;
  }

  public void setContentLength(Long contentLength) {
    this.contentLength = contentLength;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IHeaderDictionary iheaderDictionary = (IHeaderDictionary) o;
    return Objects.equals(this.item, iheaderDictionary.item) &&
        Objects.equals(this.contentLength, iheaderDictionary.contentLength);
  }

  @Override
  public int hashCode() {
    return Objects.hash(item, contentLength);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IHeaderDictionary {\n");
    
    sb.append("    item: ").append(toIndentedString(item)).append("\n");
    sb.append("    contentLength: ").append(toIndentedString(contentLength)).append("\n");
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
