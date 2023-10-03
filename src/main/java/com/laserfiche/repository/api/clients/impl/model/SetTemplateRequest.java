package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Request body for assigning a template and template fields to an entry.
 */
@Schema(description = "Request body for assigning a template and template fields to an entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SetTemplateRequest {

    @JsonProperty("templateName")
    private String templateName = null;

    @JsonProperty("fields")
    private List<FieldToUpdate> fields = null;

    public SetTemplateRequest templateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    /**
     * Returns the template that will be assigned to the entry.
     * @return templateName
     */
    @Schema(required = true, description = "The template that will be assigned to the entry.")
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public SetTemplateRequest fields(List<FieldToUpdate> fields) {
        this.fields = fields;
        return this;
    }

    public SetTemplateRequest addFieldsItem(FieldToUpdate fieldsItem) {
        if (this.fields == null) {
            this.fields = new ArrayList<FieldToUpdate>();
        }
        this.fields.add(fieldsItem);
        return this;
    }

    /**
     * Returns the template fields that will be assigned to the entry.
     * @return fields
     */
    @Schema(description = "The template fields that will be assigned to the entry.")
    public List<FieldToUpdate> getFields() {
        return fields;
    }

    public void setFields(List<FieldToUpdate> fields) {
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
        SetTemplateRequest setTemplateRequest = (SetTemplateRequest) o;
        return Objects.equals(this.templateName, setTemplateRequest.templateName) && Objects.equals(this.fields, setTemplateRequest.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(templateName, fields);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SetTemplateRequest {\n");
        sb.append("    templateName: ").append(toIndentedString(templateName)).append("\n");
        sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
