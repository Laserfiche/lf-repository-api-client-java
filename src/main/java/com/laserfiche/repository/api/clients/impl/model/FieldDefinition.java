package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a field definition.
 */
@Schema(description = "Represents a field definition.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldDefinition {

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("displayName")
    private String displayName = null;

    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("fieldType")
    private FieldType fieldType = null;

    @JsonProperty("length")
    private Integer length = null;

    @JsonProperty("defaultValue")
    private String defaultValue = null;

    @JsonProperty("isMultiValue")
    private Boolean isMultiValue = null;

    @JsonProperty("isRequired")
    private Boolean isRequired = null;

    @JsonProperty("constraint")
    private String constraint = null;

    @JsonProperty("constraintError")
    private String constraintError = null;

    @JsonProperty("listValues")
    private List<String> listValues = null;

    @JsonProperty("format")
    private FieldFormat format = null;

    @JsonProperty("currency")
    private String currency = null;

    @JsonProperty("formatPattern")
    private String formatPattern = null;

    public FieldDefinition name(String name) {
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

    public FieldDefinition displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Returns the localized name of the field.
     *
     * @return displayName
     */
    @Schema(description = "The localized name of the field.")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public FieldDefinition id(Integer id) {
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

    public FieldDefinition description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Returns the description of the field.
     *
     * @return description
     */
    @Schema(description = "The description of the field.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FieldDefinition fieldType(FieldType fieldType) {
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

    public FieldDefinition length(Integer length) {
        this.length = length;
        return this;
    }

    /**
     * Returns the length of the field for variable length data types.
     *
     * @return length
     */
    @Schema(description = "The length of the field for variable length data types.")
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public FieldDefinition defaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     * Returns the default value of the field for new entries that are assigned to a template the represented field is a member of.
     *
     * @return defaultValue
     */
    @Schema(description = "The default value of the field for new entries that are assigned to a template the represented field is a member of.")
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public FieldDefinition isMultiValue(Boolean isMultiValue) {
        this.isMultiValue = isMultiValue;
        return this;
    }

    /**
     * Returns a boolean indicating if the represented template field supports multiple values.
     *
     * @return isMultiValue
     */
    @Schema(description = "A boolean indicating if the represented template field supports multiple values.")
    @JsonProperty("isMultiValue")
    public Boolean isMultiValue() {
        return isMultiValue;
    }

    public void setIsMultiValue(Boolean isMultiValue) {
        this.isMultiValue = isMultiValue;
    }

    public FieldDefinition isRequired(Boolean isRequired) {
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

    public FieldDefinition constraint(String constraint) {
        this.constraint = constraint;
        return this;
    }

    /**
     * Returns the constraint for values stored in the represented field.
     *
     * @return constraint
     */
    @Schema(description = "The constraint for values stored in the represented field.")
    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    public FieldDefinition constraintError(String constraintError) {
        this.constraintError = constraintError;
        return this;
    }

    /**
     * Returns the error string that will be returned when the field constraint is violated when setting a value for this field.
     *
     * @return constraintError
     */
    @Schema(description = "The error string that will be returned when the field constraint is violated when setting a value for this field.")
    public String getConstraintError() {
        return constraintError;
    }

    public void setConstraintError(String constraintError) {
        this.constraintError = constraintError;
    }

    public FieldDefinition listValues(List<String> listValues) {
        this.listValues = listValues;
        return this;
    }

    public FieldDefinition addListValuesItem(String listValuesItem) {
        if (this.listValues == null) {
            this.listValues = new ArrayList<String>();
        }
        this.listValues.add(listValuesItem);
        return this;
    }

    /**
     * Returns the list of items assigned to the represented field.
     *
     * @return listValues
     */
    @Schema(description = "The list of items assigned to the represented field.")
    public List<String> getListValues() {
        return listValues;
    }

    public void setListValues(List<String> listValues) {
        this.listValues = listValues;
    }

    public FieldDefinition format(FieldFormat format) {
        this.format = format;
        return this;
    }

    /**
     * Returns format
     *
     * @return format
     */
    @Schema(description = "")
    public FieldFormat getFormat() {
        return format;
    }

    public void setFormat(FieldFormat format) {
        this.format = format;
    }

    public FieldDefinition currency(String currency) {
        this.currency = currency;
        return this;
    }

    /**
     * Returns the name of the currency that will be using when formatting the represented field when the Format property is set to the Currency member of the WFieldFormat enumeration.
     *
     * @return currency
     */
    @Schema(description = "The name of the currency that will be using when formatting the represented field when the Format property is set to the Currency member of the WFieldFormat enumeration.")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public FieldDefinition formatPattern(String formatPattern) {
        this.formatPattern = formatPattern;
        return this;
    }

    /**
     * Returns the custom format pattern for fields that are configured to use a custom format.
     *
     * @return formatPattern
     */
    @Schema(description = "The custom format pattern for fields that are configured to use a custom format.")
    public String getFormatPattern() {
        return formatPattern;
    }

    public void setFormatPattern(String formatPattern) {
        this.formatPattern = formatPattern;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FieldDefinition fieldDefinition = (FieldDefinition) o;
        return Objects.equals(this.name, fieldDefinition.name) && Objects.equals(this.displayName, fieldDefinition.displayName) && Objects.equals(this.id, fieldDefinition.id) && Objects.equals(this.description, fieldDefinition.description) && Objects.equals(this.fieldType, fieldDefinition.fieldType) && Objects.equals(this.length, fieldDefinition.length) && Objects.equals(this.defaultValue, fieldDefinition.defaultValue) && Objects.equals(this.isMultiValue, fieldDefinition.isMultiValue) && Objects.equals(this.isRequired, fieldDefinition.isRequired) && Objects.equals(this.constraint, fieldDefinition.constraint) && Objects.equals(this.constraintError, fieldDefinition.constraintError) && Objects.equals(this.listValues, fieldDefinition.listValues) && Objects.equals(this.format, fieldDefinition.format) && Objects.equals(this.currency, fieldDefinition.currency) && Objects.equals(this.formatPattern, fieldDefinition.formatPattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, displayName, id, description, fieldType, length, defaultValue, isMultiValue, isRequired, constraint, constraintError, listValues, format, currency, formatPattern);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class FieldDefinition {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    fieldType: ").append(toIndentedString(fieldType)).append("\n");
        sb.append("    length: ").append(toIndentedString(length)).append("\n");
        sb.append("    defaultValue: ").append(toIndentedString(defaultValue)).append("\n");
        sb.append("    isMultiValue: ").append(toIndentedString(isMultiValue)).append("\n");
        sb.append("    isRequired: ").append(toIndentedString(isRequired)).append("\n");
        sb.append("    constraint: ").append(toIndentedString(constraint)).append("\n");
        sb.append("    constraintError: ").append(toIndentedString(constraintError)).append("\n");
        sb.append("    listValues: ").append(toIndentedString(listValues)).append("\n");
        sb.append("    format: ").append(toIndentedString(format)).append("\n");
        sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
        sb.append("    formatPattern: ").append(toIndentedString(formatPattern)).append("\n");
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
