package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntryFieldValue {

    @JsonProperty("fieldName")
    private String fieldName = null;

    @JsonProperty("values")
    private List<Map<String, Object>> values = null;

    @JsonProperty("fieldType")
    private WFieldType fieldType = null;

    @JsonProperty("fieldId")
    private Integer fieldId = null;

    @JsonProperty("isMultiValue")
    private Boolean isMultiValue = null;

    @JsonProperty("isRequired")
    private Boolean isRequired = null;

    @JsonProperty("hasMoreValues")
    private Boolean hasMoreValues = null;

    public EntryFieldValue fieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    @Schema(description = "The name of the field.")
public String getFieldName() {
    return fieldName;
}

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public EntryFieldValue values(List<Map<String, Object>> values) {
        this.values = values;
        return this;
    }

    public EntryFieldValue addValuesItem(Map<String, Object> valuesItem) {
        if (this.values == null) {
            this.values = new ArrayList<Map<String, Object>>();
        }
        this.values.add(valuesItem);
        return this;
    }

    @Schema(description = "The values assigned to the field.")
public List<Map<String, Object>> getValues() {
    return values;
}

    public void setValues(List<Map<String, Object>> values) {
        this.values = values;
    }

    public EntryFieldValue fieldType(WFieldType fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    @Schema(description = "")
public WFieldType getFieldType() {
    return fieldType;
}

    public void setFieldType(WFieldType fieldType) {
        this.fieldType = fieldType;
    }

    public EntryFieldValue fieldId(Integer fieldId) {
        this.fieldId = fieldId;
        return this;
    }

    @Schema(description = "The ID of the field.")
public Integer getFieldId() {
    return fieldId;
}

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public EntryFieldValue isMultiValue(Boolean isMultiValue) {
        this.isMultiValue = isMultiValue;
        return this;
    }

    @Schema(description = "A boolean indicating if the represented field supports multiple values.")
public Boolean isIsMultiValue() {
    return isMultiValue;
}

    public void setIsMultiValue(Boolean isMultiValue) {
        this.isMultiValue = isMultiValue;
    }

    public EntryFieldValue isRequired(Boolean isRequired) {
        this.isRequired = isRequired;
        return this;
    }

    @Schema(description = "A boolean indicating if the represented field must have a value set on entries assigned to a template that the field is a member of.")
public Boolean isIsRequired() {
    return isRequired;
}

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public EntryFieldValue hasMoreValues(Boolean hasMoreValues) {
        this.hasMoreValues = hasMoreValues;
        return this;
    }

    @Schema(description = "A boolean indicating if there are more field values.")
public Boolean isHasMoreValues() {
    return hasMoreValues;
}

    public void setHasMoreValues(Boolean hasMoreValues) {
        this.hasMoreValues = hasMoreValues;
    }

    @Override
public boolean equals(java.lang.Object o) {
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    EntryFieldValue entryFieldValue = (EntryFieldValue) o;
    return Objects.equals(this.fieldName, entryFieldValue.fieldName) && Objects.equals(this.values, entryFieldValue.values) && Objects.equals(this.fieldType, entryFieldValue.fieldType) && Objects.equals(this.fieldId, entryFieldValue.fieldId) && Objects.equals(this.isMultiValue, entryFieldValue.isMultiValue) && Objects.equals(this.isRequired, entryFieldValue.isRequired) && Objects.equals(this.hasMoreValues, entryFieldValue.hasMoreValues);
}

    @Override
public int hashCode() {
    return Objects.hash(fieldName, values, fieldType, fieldId, isMultiValue, isRequired, hasMoreValues);
}

    @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EntryFieldValue {\n");
    sb.append("    fieldName: ").append(toIndentedString(fieldName)).append("\n");
    sb.append("    values: ").append(toIndentedString(values)).append("\n");
    sb.append("    fieldType: ").append(toIndentedString(fieldType)).append("\n");
    sb.append("    fieldId: ").append(toIndentedString(fieldId)).append("\n");
    sb.append("    isMultiValue: ").append(toIndentedString(isMultiValue)).append("\n");
    sb.append("    isRequired: ").append(toIndentedString(isRequired)).append("\n");
    sb.append("    hasMoreValues: ").append(toIndentedString(hasMoreValues)).append("\n");
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
