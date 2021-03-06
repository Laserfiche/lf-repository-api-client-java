package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/**
 * PutTemplateRequest
 */

public class PutTemplateRequest {
  @SerializedName("templateName")
  private String templateName = null;

  @SerializedName("fields")
  private Map<String, FieldToUpdate> fields = null;

  public PutTemplateRequest templateName(String templateName) {
    this.templateName = templateName;
    return this;
  }

   /**
   * The template that will be assigned to the entry.
   * @return templateName
  **/
  @Schema(description = "The template that will be assigned to the entry.")
  public String getTemplateName() {
    return templateName;
  }

  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }

  public PutTemplateRequest fields(Map<String, FieldToUpdate> fields) {
    this.fields = fields;
    return this;
  }

  public PutTemplateRequest putFieldsItem(String key, FieldToUpdate fieldsItem) {
    if (this.fields == null) {
      this.fields = new HashMap<String, FieldToUpdate>();
    }
    this.fields.put(key, fieldsItem);
    return this;
  }

   /**
   * The template fields that will be assigned to the entry.
   * @return fields
  **/
  @Schema(description = "The template fields that will be assigned to the entry.")
  public Map<String, FieldToUpdate> getFields() {
    return fields;
  }

  public void setFields(Map<String, FieldToUpdate> fields) {
    this.fields = fields;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PutTemplateRequest putTemplateRequest = (PutTemplateRequest) o;
    return Objects.equals(this.templateName, putTemplateRequest.templateName) &&
        Objects.equals(this.fields, putTemplateRequest.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(templateName, fields);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PutTemplateRequest {\n");
    
    sb.append("    templateName: ").append(toIndentedString(templateName)).append("\n");
    sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
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
