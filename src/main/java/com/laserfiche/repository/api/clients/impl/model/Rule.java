package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Rule
 */

public class Rule {
  @SerializedName("ancestors")
  private List<Integer> ancestors = null;

  public Rule ancestors(List<Integer> ancestors) {
    this.ancestors = ancestors;
    return this;
  }

  public Rule addAncestorsItem(Integer ancestorsItem) {
    if (this.ancestors == null) {
      this.ancestors = new ArrayList<Integer>();
    }
    this.ancestors.add(ancestorsItem);
    return this;
  }

   /**
   * The IDs of the parent fields in the template according to the form logic rule.
   * @return ancestors
  **/
  @Schema(description = "The IDs of the parent fields in the template according to the form logic rule.")
  public List<Integer> getAncestors() {
    return ancestors;
  }

  public void setAncestors(List<Integer> ancestors) {
    this.ancestors = ancestors;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rule rule = (Rule) o;
    return Objects.equals(this.ancestors, rule.ancestors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ancestors);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Rule {\n");
    
    sb.append("    ancestors: ").append(toIndentedString(ancestors)).append("\n");
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
