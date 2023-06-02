package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen",
    date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvancedSearchRequest {

  @JsonProperty("searchCommand")
  private String searchCommand = null;

  @JsonProperty("fuzzyType")
  private FuzzyType fuzzyType = null;

  @JsonProperty("fuzzyFactor")
  private Integer fuzzyFactor = null;

  public AdvancedSearchRequest searchCommand(String searchCommand) {
    this.searchCommand = searchCommand;
    return this;
  }

  /**
   * Returns search command for advanced search
   *
   * @return searchCommand
   */
  @Schema(description = "Search command for advanced search")
  public String getSearchCommand() {
    return searchCommand;
  }

  public void setSearchCommand(String searchCommand) {
    this.searchCommand = searchCommand;
  }

  public AdvancedSearchRequest fuzzyType(FuzzyType fuzzyType) {
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

  public AdvancedSearchRequest fuzzyFactor(Integer fuzzyFactor) {
    this.fuzzyFactor = fuzzyFactor;
    return this;
  }

  /**
   * Returns fuzzy factor (percentage as int or int value)
   *
   * @return fuzzyFactor
   */
  @Schema(description = "Fuzzy factor (percentage as int or int value)")
  public Integer getFuzzyFactor() {
    return fuzzyFactor;
  }

  public void setFuzzyFactor(Integer fuzzyFactor) {
    this.fuzzyFactor = fuzzyFactor;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdvancedSearchRequest advancedSearchRequest = (AdvancedSearchRequest) o;
    return Objects.equals(this.searchCommand, advancedSearchRequest.searchCommand)
        && Objects.equals(this.fuzzyType, advancedSearchRequest.fuzzyType)
        && Objects.equals(this.fuzzyFactor, advancedSearchRequest.fuzzyFactor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(searchCommand, fuzzyType, fuzzyFactor);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdvancedSearchRequest {\n");
    sb.append("    searchCommand: ").append(toIndentedString(searchCommand)).append("\n");
    sb.append("    fuzzyType: ").append(toIndentedString(fuzzyType)).append("\n");
    sb.append("    fuzzyFactor: ").append(toIndentedString(fuzzyFactor)).append("\n");
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
