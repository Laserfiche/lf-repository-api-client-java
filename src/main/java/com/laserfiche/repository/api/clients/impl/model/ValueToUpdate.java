package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@javax.annotation.Generated(
        value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen",
        date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
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

    /**
     * Returns the value assigned to the field at the position specified.
     *
     * @return value
     */
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

    /**
     * Returns the position of the value in the field. This is 1-indexed for multi value field. It will be ignored for single value field.
     *
     * @return position
     */
    @Schema(
            description =
                    "The position of the value in the field. This is 1-indexed for multi value field. It will be ignored for single value field.")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
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
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("    position: ").append(toIndentedString(position)).append("\n");
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
