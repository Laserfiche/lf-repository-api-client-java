package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rule {

  @JsonProperty("ancestors")
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
   * Returns the IDs of the parent fields in the template according to the form logic rule.
   *
   * @return ancestors
   */
  @Schema(
      description =
          "The IDs of the parent fields in the template according to the form logic rule.")
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

  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
