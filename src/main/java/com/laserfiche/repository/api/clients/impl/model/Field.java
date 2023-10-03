package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a field set on an entry.
 */
@Schema(description = "Represents a field set on an entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Field {

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("fieldType")
    private FieldType fieldType = null;

    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("isMultiValue")
    private Boolean isMultiValue = null;

    @JsonProperty("isRequired")
    private Boolean isRequired = null;

    @JsonProperty("hasMoreValues")
    private Boolean hasMoreValues = null;

    @JsonProperty("groupId")
    private Integer groupId = null;

    @JsonProperty("values")
    private List<String> values = null;

    public Field name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Returns the name of the field.
     *
     * @return name
     */
    @Schema(description = "The name of the field.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Field fieldType(FieldType fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    /**
     * Returns fieldType
     *
     * @return fieldType
     */
    @Schema(description = "")
    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public Field id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Returns the ID of the field.
     *
     * @return id
     */
    @Schema(description = "The ID of the field.")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Field isMultiValue(Boolean isMultiValue) {
        this.isMultiValue = isMultiValue;
        return this;
    }

    /**
     * Returns a boolean indicating if the represented field supports multiple values.
     *
     * @return isMultiValue
     */
    @Schema(description = "A boolean indicating if the represented field supports multiple values.")
    @JsonProperty("isMultiValue")
    public Boolean isMultiValue() {
        return isMultiValue;
    }

    public void setIsMultiValue(Boolean isMultiValue) {
        this.isMultiValue = isMultiValue;
    }

    public Field isRequired(Boolean isRequired) {
        this.isRequired = isRequired;
        return this;
    }

    /**
     * Returns a boolean indicating if the represented field must have a value set on entries assigned to a template that the field is a member of.
     *
     * @return isRequired
     */
    @Schema(description = "A boolean indicating if the represented field must have a value set on entries assigned to a template that the field is a member of.")
    @JsonProperty("isRequired")
    public Boolean isRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public Field hasMoreValues(Boolean hasMoreValues) {
        this.hasMoreValues = hasMoreValues;
        return this;
    }

    /**
     * Returns a boolean indicating if there are more field values.
     *
     * @return hasMoreValues
     */
    @Schema(description = "A boolean indicating if there are more field values.")
    public Boolean isHasMoreValues() {
        return hasMoreValues;
    }

    public void setHasMoreValues(Boolean hasMoreValues) {
        this.hasMoreValues = hasMoreValues;
    }

    public Field groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * Returns the group id of the multi value field group. If the field is not a part of a multi value field group, then there is no group id.
     *
     * @return groupId
     */
    @Schema(description = "The group id of the multi value field group. If the field is not a part of a multi value field group, then there is no group id.")
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Field values(List<String> values) {
        this.values = values;
        return this;
    }

    public Field addValuesItem(String valuesItem) {
        if (this.values == null) {
            this.values = new ArrayList<String>();
        }
        this.values.add(valuesItem);
        return this;
    }

    /**
     * Returns the values assigned to the field.
     *
     * @return values
     */
    @Schema(description = "The values assigned to the field.")
    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Field field = (Field) o;
        return Objects.equals(this.name, field.name) && Objects.equals(this.fieldType, field.fieldType) && Objects.equals(this.id, field.id) && Objects.equals(this.isMultiValue, field.isMultiValue) && Objects.equals(this.isRequired, field.isRequired) && Objects.equals(this.hasMoreValues, field.hasMoreValues) && Objects.equals(this.groupId, field.groupId) && Objects.equals(this.values, field.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fieldType, id, isMultiValue, isRequired, hasMoreValues, groupId, values);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Field {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    fieldType: ").append(toIndentedString(fieldType)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    isMultiValue: ").append(toIndentedString(isMultiValue)).append("\n");
        sb.append("    isRequired: ").append(toIndentedString(isRequired)).append("\n");
        sb.append("    hasMoreValues: ").append(toIndentedString(hasMoreValues)).append("\n");
        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    values: ").append(toIndentedString(values)).append("\n");
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
