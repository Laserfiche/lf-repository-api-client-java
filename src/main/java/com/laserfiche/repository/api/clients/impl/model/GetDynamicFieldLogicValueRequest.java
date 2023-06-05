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
public class GetDynamicFieldLogicValueRequest {

    @JsonProperty("templateId")
    private Integer templateId = null;

    @JsonProperty("fieldValues")
    private Map<String, String> fieldValues = null;

    public GetDynamicFieldLogicValueRequest templateId(Integer templateId) {
        this.templateId = templateId;
        return this;
    }

    /**
     * Returns the template id.
     *
     * @return templateId
     */
    @Schema(description = "The template id.")
    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public GetDynamicFieldLogicValueRequest fieldValues(Map<String, String> fieldValues) {
        this.fieldValues = fieldValues;
        return this;
    }

    public GetDynamicFieldLogicValueRequest putFieldValuesItem(String key, String fieldValuesItem) {
        if (this.fieldValues == null) {
            this.fieldValues = new HashMap<String, String>();
        }
        this.fieldValues.put(key, fieldValuesItem);
        return this;
    }

    /**
     * Returns the dynamic fields.
     *
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetDynamicFieldLogicValueRequest getDynamicFieldLogicValueRequest = (GetDynamicFieldLogicValueRequest) o;
        return Objects.equals(this.templateId, getDynamicFieldLogicValueRequest.templateId)
                && Objects.equals(this.fieldValues, getDynamicFieldLogicValueRequest.fieldValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(templateId, fieldValues);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetDynamicFieldLogicValueRequest {\n");
        sb.append("    templateId: ").append(toIndentedString(templateId)).append("\n");
        sb.append("    fieldValues: ").append(toIndentedString(fieldValues)).append("\n");
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
