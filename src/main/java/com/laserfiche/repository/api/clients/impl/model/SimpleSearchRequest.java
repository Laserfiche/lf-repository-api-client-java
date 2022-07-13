package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
/**
 * SimpleSearchRequest
 */

public class SimpleSearchRequest {
  @SerializedName("searchCommand")
  private String searchCommand = null;

  public SimpleSearchRequest searchCommand(String searchCommand) {
    this.searchCommand = searchCommand;
    return this;
  }

   /**
   * Search command for simple search
   * @return searchCommand
  **/
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
