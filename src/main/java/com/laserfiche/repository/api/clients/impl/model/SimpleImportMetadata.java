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

package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * SimpleImportMetadata
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-06-21T09:31:30.649462900-04:00[America/New_York]")
public class SimpleImportMetadata {
  @SerializedName("fields")
  private Map<String, FieldToUpdate> fields = null;

  @SerializedName("tags")
  private List<String> tags = null;

  public SimpleImportMetadata fields(Map<String, FieldToUpdate> fields) {
    this.fields = fields;
    return this;
  }

  public SimpleImportMetadata putFieldsItem(String key, FieldToUpdate fieldsItem) {
    if (this.fields == null) {
      this.fields = new HashMap<String, FieldToUpdate>();
    }
    this.fields.put(key, fieldsItem);
    return this;
  }

   /**
   * The fields that will be assigned to the entry.
   * @return fields
  **/
  @Schema(description = "The fields that will be assigned to the entry.")
  public Map<String, FieldToUpdate> getFields() {
    return fields;
  }

  public void setFields(Map<String, FieldToUpdate> fields) {
    this.fields = fields;
  }

  public SimpleImportMetadata tags(List<String> tags) {
    this.tags = tags;
    return this;
  }

  public SimpleImportMetadata addTagsItem(String tagsItem) {
    if (this.tags == null) {
      this.tags = new ArrayList<String>();
    }
    this.tags.add(tagsItem);
    return this;
  }

   /**
   * The tags that will be assigned to the entry.
   * @return tags
  **/
  @Schema(description = "The tags that will be assigned to the entry.")
  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SimpleImportMetadata simpleImportMetadata = (SimpleImportMetadata) o;
    return Objects.equals(this.fields, simpleImportMetadata.fields) &&
        Objects.equals(this.tags, simpleImportMetadata.tags);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fields, tags);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SimpleImportMetadata {\n");
    
    sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
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