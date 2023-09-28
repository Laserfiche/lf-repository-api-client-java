package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.*;

/**
 * Request body for searching entries.
 */
@Schema(description = "Request body for searching entries.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchEntryRequest {

    @JsonProperty("searchCommand")
    private String searchCommand = null;

    public SearchEntryRequest searchCommand(String searchCommand) {
        this.searchCommand = searchCommand;
        return this;
    }

    /**
     * Returns the search command to run. The search command should follow the Laserfiche search syntax. https://doc.laserfiche.com/laserfiche.documentation/en-us/Default.htm#Search_Syntax.htm
     * @return searchCommand
     */
    @Schema(required = true, description = "The search command to run. The search command should follow the Laserfiche search syntax. https://doc.laserfiche.com/laserfiche.documentation/en-us/Default.htm#Search_Syntax.htm")
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
        SearchEntryRequest searchEntryRequest = (SearchEntryRequest) o;
        return Objects.equals(this.searchCommand, searchEntryRequest.searchCommand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchCommand);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SearchEntryRequest {\n");
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
