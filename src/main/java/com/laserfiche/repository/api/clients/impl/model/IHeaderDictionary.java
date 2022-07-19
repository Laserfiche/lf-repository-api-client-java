package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * IHeaderDictionary
 */

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
