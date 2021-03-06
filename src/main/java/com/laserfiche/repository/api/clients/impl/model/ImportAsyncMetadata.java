package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.*;
/**
 * ImportAsyncMetadata
 */

public class ImportAsyncMetadata {
  @SerializedName("fields")
  private Map<String, FieldToUpdate> fields = null;

  @SerializedName("tags")
  private List<String> tags = null;

  @SerializedName("links")
  private List<LinkToUpdate> links = null;

  public ImportAsyncMetadata fields(Map<String, FieldToUpdate> fields) {
    this.fields = fields;
    return this;
  }

  public ImportAsyncMetadata putFieldsItem(String key, FieldToUpdate fieldsItem) {
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

  public ImportAsyncMetadata tags(List<String> tags) {
    this.tags = tags;
    return this;
  }

  public ImportAsyncMetadata addTagsItem(String tagsItem) {
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

  public ImportAsyncMetadata links(List<LinkToUpdate> links) {
    this.links = links;
    return this;
  }

  public ImportAsyncMetadata addLinksItem(LinkToUpdate linksItem) {
    if (this.links == null) {
      this.links = new ArrayList<LinkToUpdate>();
    }
    this.links.add(linksItem);
    return this;
  }

   /**
   * The links that will be assigned to the entry.
   * @return links
  **/
  @Schema(description = "The links that will be assigned to the entry.")
  public List<LinkToUpdate> getLinks() {
    return links;
  }

  public void setLinks(List<LinkToUpdate> links) {
    this.links = links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImportAsyncMetadata importAsyncMetadata = (ImportAsyncMetadata) o;
    return Objects.equals(this.fields, importAsyncMetadata.fields) &&
        Objects.equals(this.tags, importAsyncMetadata.tags) &&
        Objects.equals(this.links, importAsyncMetadata.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fields, tags, links);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ImportAsyncMetadata {\n");
    
    sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
    sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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
