package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-10-17T11:38:41.655-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValueToUpdate {

    @JsonProperty("value")
    private String value = null;

    @JsonProperty("position")
    private Integer position = null;

    public ValueToUpdate value(String value) {
        this.value = value;
        return this;
    }

    @Schema(description = "The value assigned to the field at the position specified.")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ValueToUpdate position(Integer position) {
        this.position = position;
        return this;
    }

    @Schema(description = "The position of the value in the field. This is 1-indexed for multi value field. It will be ignored for single value field.")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ValueToUpdate valueToUpdate = (ValueToUpdate) o;
        return Objects.equals(this.value, valueToUpdate.value) && Objects.equals(this.position, valueToUpdate.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, position);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ValueToUpdate {\n");
        sb
                .append("    value: ")
                .append(toIndentedString(value))
                .append("\n");
        sb
                .append("    position: ")
                .append(toIndentedString(position))
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
