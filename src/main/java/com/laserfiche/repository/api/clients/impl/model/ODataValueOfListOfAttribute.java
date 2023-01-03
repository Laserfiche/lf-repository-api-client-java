package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ODataValueOfListOfAttribute {

    @JsonProperty("value")
    private List<Attribute> value = null;

    public ODataValueOfListOfAttribute value(List<Attribute> value) {
        this.value = value;
        return this;
    }

    public ODataValueOfListOfAttribute addValueItem(Attribute valueItem) {
        if (this.value == null) {
            this.value = new ArrayList<Attribute>();
        }
        this.value.add(valueItem);
        return this;
    }

    /**
     * Returns value
     *
     * @return value
     */
    @Schema(description = "")
    public List<Attribute> getValue() {
        return value;
    }

    public void setValue(List<Attribute> value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ODataValueOfListOfAttribute odataValueOfListOfAttribute = (ODataValueOfListOfAttribute) o;
        return Objects.equals(this.value, odataValueOfListOfAttribute.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ODataValueOfListOfAttribute {\n");
        sb
                .append("    value: ")
                .append(toIndentedString(value))
                .append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o
                .toString()
                .replace("\n", "\n    ");
    }
}
