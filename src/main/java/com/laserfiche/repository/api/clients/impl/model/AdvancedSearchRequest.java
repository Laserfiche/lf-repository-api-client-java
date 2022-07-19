package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * AdvancedSearchRequest
 */

public class AdvancedSearchRequest {
  @SerializedName("searchCommand")
  private String searchCommand = null;

  @SerializedName("fuzzyType")
  private FuzzyType fuzzyType = null;

  @SerializedName("fuzzyFactor")
  private Integer fuzzyFactor = null;

  public AdvancedSearchRequest searchCommand(String searchCommand) {
    this.searchCommand = searchCommand;
    return this;
  }

   /**
   * Search command for advanced search
   * @return searchCommand
  **/
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
   * Fuzzy type (None, Percentage, or NumberOfLetters)
   * @return fuzzyType
  **/
  @Schema(description = "Fuzzy type (None, Percentage, or NumberOfLetters)")
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
   * Fuzzy factor (percentage as int or int value)
   * @return fuzzyFactor
  **/
  @Schema(description = "Fuzzy factor (percentage as int or int value)")
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
    AdvancedSearchRequest advancedSearchRequest = (AdvancedSearchRequest) o;
    return Objects.equals(this.searchCommand, advancedSearchRequest.searchCommand) &&
        Objects.equals(this.fuzzyType, advancedSearchRequest.fuzzyType) &&
        Objects.equals(this.fuzzyFactor, advancedSearchRequest.fuzzyFactor);
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

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
