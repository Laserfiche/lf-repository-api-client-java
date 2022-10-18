package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-10-17T11:38:41.655-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WFieldInfo {

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("displayName")
    private String displayName = null;

    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("fieldType")
    private WFieldType fieldType = null;

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
    private WFieldFormat format = null;

    @JsonProperty("currency")
    private String currency = null;

    @JsonProperty("formatPattern")
    private String formatPattern = null;

    public WFieldInfo name(String name) {
        this.name = name;
        return this;
    }

    @Schema(description = "The name of the field.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WFieldInfo displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    @Schema(description = "The localized name of the field.")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public WFieldInfo id(Integer id) {
        this.id = id;
        return this;
    }

    @Schema(description = "The ID of the field.")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WFieldInfo description(String description) {
        this.description = description;
        return this;
    }

    @Schema(description = "The description of the field.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WFieldInfo fieldType(WFieldType fieldType) {
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

    public WFieldInfo length(Integer length) {
        this.length = length;
        return this;
    }

    @Schema(description = "The length of the field for variable length data types.")
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public WFieldInfo defaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    @Schema(description = "The default value of the field for new entries that are assigned to a template the represented field is a member of.")
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public WFieldInfo isMultiValue(Boolean isMultiValue) {
        this.isMultiValue = isMultiValue;
        return this;
    }

    @Schema(description = "A boolean indicating if the represented template field supports multiple values.")
    public Boolean getIsMultiValue() {
        return isMultiValue;
    }

    public void setIsMultiValue(Boolean isMultiValue) {
        this.isMultiValue = isMultiValue;
    }

    public WFieldInfo isRequired(Boolean isRequired) {
        this.isRequired = isRequired;
        return this;
    }

    @Schema(description = "A boolean indicating if the represented field must have a value set on entries assigned to a template that the field is a member of.")
    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public WFieldInfo constraint(String constraint) {
        this.constraint = constraint;
        return this;
    }

    @Schema(description = "The constraint for values stored in the represented field.")
    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    public WFieldInfo constraintError(String constraintError) {
        this.constraintError = constraintError;
        return this;
    }

    @Schema(description = "The error string that will be returned when the field constraint is violated when setting a value for this field.")
    public String getConstraintError() {
        return constraintError;
    }

    public void setConstraintError(String constraintError) {
        this.constraintError = constraintError;
    }

    public WFieldInfo listValues(List<String> listValues) {
        this.listValues = listValues;
        return this;
    }

    public WFieldInfo addListValuesItem(String listValuesItem) {
        if (this.listValues == null) {
            this.listValues = new ArrayList<String>();
        }
        this.listValues.add(listValuesItem);
        return this;
    }

    @Schema(description = "The list of items assigned to the represented field.")
    public List<String> getListValues() {
        return listValues;
    }

    public void setListValues(List<String> listValues) {
        this.listValues = listValues;
    }

    public WFieldInfo format(WFieldFormat format) {
        this.format = format;
        return this;
    }

    @Schema(description = "")
    public WFieldFormat getFormat() {
        return format;
    }

    public void setFormat(WFieldFormat format) {
        this.format = format;
    }

    public WFieldInfo currency(String currency) {
        this.currency = currency;
        return this;
    }

    @Schema(description = "The name of the currency that will be using when formatting the represented field when the Format property is set to the Currency member of the WFieldFormat enumeration.")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public WFieldInfo formatPattern(String formatPattern) {
        this.formatPattern = formatPattern;
        return this;
    }

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
        WFieldInfo wfieldInfo = (WFieldInfo) o;
        return Objects.equals(this.name, wfieldInfo.name) && Objects.equals(this.displayName,
                wfieldInfo.displayName) && Objects.equals(this.id, wfieldInfo.id) && Objects.equals(this.description,
                wfieldInfo.description) && Objects.equals(this.fieldType, wfieldInfo.fieldType) && Objects.equals(
                this.length, wfieldInfo.length) && Objects.equals(this.defaultValue,
                wfieldInfo.defaultValue) && Objects.equals(this.isMultiValue,
                wfieldInfo.isMultiValue) && Objects.equals(this.isRequired, wfieldInfo.isRequired) && Objects.equals(
                this.constraint, wfieldInfo.constraint) && Objects.equals(this.constraintError,
                wfieldInfo.constraintError) && Objects.equals(this.listValues, wfieldInfo.listValues) && Objects.equals(
                this.format, wfieldInfo.format) && Objects.equals(this.currency, wfieldInfo.currency) && Objects.equals(
                this.formatPattern, wfieldInfo.formatPattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, displayName, id, description, fieldType, length, defaultValue, isMultiValue,
                isRequired, constraint, constraintError, listValues, format, currency, formatPattern);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WFieldInfo {\n");
        sb
                .append("    name: ")
                .append(toIndentedString(name))
                .append("\n");
        sb
                .append("    displayName: ")
                .append(toIndentedString(displayName))
                .append("\n");
        sb
                .append("    id: ")
                .append(toIndentedString(id))
                .append("\n");
        sb
                .append("    description: ")
                .append(toIndentedString(description))
                .append("\n");
        sb
                .append("    fieldType: ")
                .append(toIndentedString(fieldType))
                .append("\n");
        sb
                .append("    length: ")
                .append(toIndentedString(length))
                .append("\n");
        sb
                .append("    defaultValue: ")
                .append(toIndentedString(defaultValue))
                .append("\n");
        sb
                .append("    isMultiValue: ")
                .append(toIndentedString(isMultiValue))
                .append("\n");
        sb
                .append("    isRequired: ")
                .append(toIndentedString(isRequired))
                .append("\n");
        sb
                .append("    constraint: ")
                .append(toIndentedString(constraint))
                .append("\n");
        sb
                .append("    constraintError: ")
                .append(toIndentedString(constraintError))
                .append("\n");
        sb
                .append("    listValues: ")
                .append(toIndentedString(listValues))
                .append("\n");
        sb
                .append("    format: ")
                .append(toIndentedString(format))
                .append("\n");
        sb
                .append("    currency: ")
                .append(toIndentedString(currency))
                .append("\n");
        sb
                .append("    formatPattern: ")
                .append(toIndentedString(formatPattern))
                .append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o
                .toString()
                .replace("\n", "\n    ");
    }
}
