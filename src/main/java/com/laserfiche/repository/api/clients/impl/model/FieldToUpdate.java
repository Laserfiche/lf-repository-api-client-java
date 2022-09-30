package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(description = "The request body containing fields that will be assigned to the entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldToUpdate {

    @JsonProperty("values")
    private List<ValueToUpdate> values = null;

    public FieldToUpdate values(List<ValueToUpdate> values) {
        this.values = values;
        return this;
    }

    public FieldToUpdate addValuesItem(ValueToUpdate valuesItem) {
        if (this.values == null) {
            this.values = new ArrayList<ValueToUpdate>();
        }
        this.values.add(valuesItem);
        return this;
    }

    @Schema(description = "The field values that will be assigned to the field.")
    public List<ValueToUpdate> getValues() {
        return values;
    }

    public void setValues(List<ValueToUpdate> values) {
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
        return Objects.equals(this.values, fieldToUpdate.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class FieldToUpdate {\n");
        sb
                .append("    values: ")
                .append(toIndentedString(values))
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
