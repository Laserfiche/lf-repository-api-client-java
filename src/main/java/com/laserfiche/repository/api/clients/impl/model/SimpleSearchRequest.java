package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleSearchRequest {

    @JsonProperty("searchCommand")
    private String searchCommand = null;

    public SimpleSearchRequest searchCommand(String searchCommand) {
        this.searchCommand = searchCommand;
        return this;
    }

    @Schema(description = "Search command for simple search")
public String getSearchCommand() {
    return searchCommand;
}

    public void setSearchCommand(String searchCommand) {
        this.searchCommand = searchCommand;
    }

    @Override
public boolean equals(java.lang.Object o) {
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    SimpleSearchRequest simpleSearchRequest = (SimpleSearchRequest) o;
    return Objects.equals(this.searchCommand, simpleSearchRequest.searchCommand);
}

    @Override
public int hashCode() {
    return Objects.hash(searchCommand);
}

    @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SimpleSearchRequest {\n");
    sb.append("    searchCommand: ").append(toIndentedString(searchCommand)).append("\n");
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
