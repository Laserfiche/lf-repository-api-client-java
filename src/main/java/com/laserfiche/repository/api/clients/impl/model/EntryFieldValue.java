package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/**
 * EntryFieldValue
 */

public class EntryFieldValue {
  @SerializedName("fieldName")
  private String fieldName = null;

  @SerializedName("values")
  private List<Map<String, Object>> values = null;

  @SerializedName("fieldType")
  private WFieldType fieldType = null;

  @SerializedName("fieldId")
  private Integer fieldId = null;

  @SerializedName("isMultiValue")
  private Boolean isMultiValue = null;

  @SerializedName("isRequired")
  private Boolean isRequired = null;

  @SerializedName("hasMoreValues")
  private Boolean hasMoreValues = null;

  public EntryFieldValue fieldName(String fieldName) {
    this.fieldName = fieldName;
    return this;
  }

   /**
   * The name of the field.
   * @return fieldName
  **/
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

   /**
   * The values assigned to the field.
   * @return values
  **/
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

   /**
   * The type of the field. The possible field types are listed below.
   * @return fieldType
  **/
  @Schema(description = "The type of the field. The possible field types are listed below.")
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

   /**
   * The ID of the field.
   * @return fieldId
  **/
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

   /**
   * A boolean indicating if the represented field supports multiple values.
   * @return isMultiValue
  **/
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

   /**
   * A boolean indicating if the represented field must have a value set on entries assigned to a template that the field is a member of.
   * @return isRequired
  **/
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

   /**
   * A boolean indicating if there are more field values.
   * @return hasMoreValues
  **/
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
    return Objects.equals(this.fieldName, entryFieldValue.fieldName) &&
        Objects.equals(this.values, entryFieldValue.values) &&
        Objects.equals(this.fieldType, entryFieldValue.fieldType) &&
        Objects.equals(this.fieldId, entryFieldValue.fieldId) &&
        Objects.equals(this.isMultiValue, entryFieldValue.isMultiValue) &&
        Objects.equals(this.isRequired, entryFieldValue.isRequired) &&
        Objects.equals(this.hasMoreValues, entryFieldValue.hasMoreValues);
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
