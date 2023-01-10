package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Attribute {

    @JsonProperty("key")
    private String key = null;

    @JsonProperty("value")
    private String value = null;

    public Attribute key(String key) {
        this.key = key;
        return this;
    }

    /**
     * Returns key
     *
     * @return key
     */
    @Schema(description = "")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Attribute value(String value) {
        this.value = value;
        return this;
    }

    /**
     * Returns value
     *
     * @return value
     */
    @Schema(description = "")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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
        Attribute attribute = (Attribute) o;
        return Objects.equals(this.key, attribute.key) && Objects.equals(this.value, attribute.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Attribute {\n");
        sb
                .append("    key: ")
                .append(toIndentedString(key))
                .append("\n");
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
