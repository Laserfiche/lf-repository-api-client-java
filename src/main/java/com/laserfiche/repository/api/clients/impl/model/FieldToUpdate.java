// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a field that will be assigned to the entry.
 */
@Schema(description = "Represents a field that will be assigned to the entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldToUpdate {

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("values")
    private List<String> values = null;

    public FieldToUpdate name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Returns the name of the field that will be assigned to the entry.
     *
     * @return name
     */
    @Schema(required = true, description = "The name of the field that will be assigned to the entry.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FieldToUpdate values(List<String> values) {
        this.values = values;
        return this;
    }

    public FieldToUpdate addValuesItem(String valuesItem) {
        if (this.values == null) {
            this.values = new ArrayList<String>();
        }
        this.values.add(valuesItem);
        return this;
    }

    /**
     * Returns the field values that will be assigned to the field.
     *
     * @return values
     */
    @Schema(description = "The field values that will be assigned to the field.")
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
        FieldToUpdate fieldToUpdate = (FieldToUpdate) o;
        return Objects.equals(this.name, fieldToUpdate.name) && Objects.equals(this.values, fieldToUpdate.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, values);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class FieldToUpdate {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
