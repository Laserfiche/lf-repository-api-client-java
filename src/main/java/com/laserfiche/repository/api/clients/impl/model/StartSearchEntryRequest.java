package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * Request body for starting an asynchronous search entry task.
 */
@Schema(description = "Request body for starting an asynchronous search entry task.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StartSearchEntryRequest {

    @JsonProperty("searchCommand")
    private String searchCommand = null;

    @JsonProperty("fuzzyType")
    private FuzzyType fuzzyType = null;

    @JsonProperty("fuzzyFactor")
    private Integer fuzzyFactor = null;

    public StartSearchEntryRequest searchCommand(String searchCommand) {
        this.searchCommand = searchCommand;
        return this;
    }

    /**
     * Returns the search command to run. The search command should follow the Laserfiche search syntax. https://doc.laserfiche.com/laserfiche.documentation/en-us/Default.htm#Search_Syntax.htm
     *
     * @return searchCommand
     */
    @Schema(required = true, description = "The search command to run. The search command should follow the Laserfiche search syntax. https://doc.laserfiche.com/laserfiche.documentation/en-us/Default.htm#Search_Syntax.htm")
    public String getSearchCommand() {
        return searchCommand;
    }

    public void setSearchCommand(String searchCommand) {
        this.searchCommand = searchCommand;
    }

    public StartSearchEntryRequest fuzzyType(FuzzyType fuzzyType) {
        this.fuzzyType = fuzzyType;
        return this;
    }

    /**
     * Returns fuzzyType
     *
     * @return fuzzyType
     */
    @Schema(description = "")
    public FuzzyType getFuzzyType() {
        return fuzzyType;
    }

    public void setFuzzyType(FuzzyType fuzzyType) {
        this.fuzzyType = fuzzyType;
    }

    public StartSearchEntryRequest fuzzyFactor(Integer fuzzyFactor) {
        this.fuzzyFactor = fuzzyFactor;
        return this;
    }

    /**
     * Returns fuzzy factor (percentage as int or int value).
     *
     * @return fuzzyFactor
     */
    @Schema(description = "Fuzzy factor (percentage as int or int value).")
    public Integer getFuzzyFactor() {
        return fuzzyFactor;
    }

    public void setFuzzyFactor(Integer fuzzyFactor) {
        this.fuzzyFactor = fuzzyFactor;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StartSearchEntryRequest startSearchEntryRequest = (StartSearchEntryRequest) o;
        return Objects.equals(this.searchCommand, startSearchEntryRequest.searchCommand) && Objects.equals(this.fuzzyType, startSearchEntryRequest.fuzzyType) && Objects.equals(this.fuzzyFactor, startSearchEntryRequest.fuzzyFactor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchCommand, fuzzyType, fuzzyFactor);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StartSearchEntryRequest {\n");
        sb.append("    searchCommand: ").append(toIndentedString(searchCommand)).append("\n");
        sb.append("    fuzzyType: ").append(toIndentedString(fuzzyType)).append("\n");
        sb.append("    fuzzyFactor: ").append(toIndentedString(fuzzyFactor)).append("\n");
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
