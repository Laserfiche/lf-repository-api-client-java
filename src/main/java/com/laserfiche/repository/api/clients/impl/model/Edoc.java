package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Edoc {

    @Override
public boolean equals(java.lang.Object o) {
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    return true;
}

    @Override
public int hashCode() {
    return Objects.hash();
}

    @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Edoc {\n");
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
