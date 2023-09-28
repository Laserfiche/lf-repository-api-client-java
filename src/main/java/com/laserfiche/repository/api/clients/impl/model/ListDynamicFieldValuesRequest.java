package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.*;

/**
 * Request body for listing dynamic field values for an entry.
 */
@Schema(description = "Request body for listing dynamic field values for an entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListDynamicFieldValuesRequest {

    @JsonProperty("templateId")
    private Integer templateId = null;

    @JsonProperty("fieldValues")
    private Map<String, String> fieldValues = null;

    public ListDynamicFieldValuesRequest templateId(Integer templateId) {
        this.templateId = templateId;
        return this;
    }

    /**
     * Returns the template id.
     * @return templateId
     */
    @Schema(required = true, description = "The template id.")
    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public ListDynamicFieldValuesRequest fieldValues(Map<String, String> fieldValues) {
        this.fieldValues = fieldValues;
        return this;
    }

    public ListDynamicFieldValuesRequest putFieldValuesItem(String key, String fieldValuesItem) {
        if (this.fieldValues == null) {
            this.fieldValues = new HashMap<String, String>();
        }
        this.fieldValues.put(key, fieldValuesItem);
        return this;
    }

    /**
     * Returns the dynamic fields.
     * @return fieldValues
     */
    @Schema(description = "The dynamic fields.")
    public Map<String, String> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(Map<String, String> fieldValues) {
        this.fieldValues = fieldValues;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ListDynamicFieldValuesRequest listDynamicFieldValuesRequest = (ListDynamicFieldValuesRequest) o;
        return Objects.equals(this.templateId, listDynamicFieldValuesRequest.templateId) && Objects.equals(this.fieldValues, listDynamicFieldValuesRequest.fieldValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(templateId, fieldValues);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ListDynamicFieldValuesRequest {\n");
        sb.append("    templateId: ").append(toIndentedString(templateId)).append("\n");
        sb.append("    fieldValues: ").append(toIndentedString(fieldValues)).append("\n");
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
