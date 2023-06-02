package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen",
    date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PutTemplateRequest {

  @JsonProperty("templateName")
  private String templateName = null;

  @JsonProperty("fields")
  private Map<String, FieldToUpdate> fields = null;

  public PutTemplateRequest templateName(String templateName) {
    this.templateName = templateName;
    return this;
  }

  /**
   * Returns the template that will be assigned to the entry.
   *
   * @return templateName
   */
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
   * Returns the template fields that will be assigned to the entry.
   *
   * @return fields
   */
  @Schema(description = "The template fields that will be assigned to the entry.")
  public Map<String, FieldToUpdate> getFields() {
    return fields;
  }

  public void setFields(Map<String, FieldToUpdate> fields) {
    this.fields = fields;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PutTemplateRequest putTemplateRequest = (PutTemplateRequest) o;
    return Objects.equals(this.templateName, putTemplateRequest.templateName)
        && Objects.equals(this.fields, putTemplateRequest.fields);
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

  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
