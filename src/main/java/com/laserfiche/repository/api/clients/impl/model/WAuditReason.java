package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WAuditReason {

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  public WAuditReason id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Returns the audit reason id.
   *
   * @return id
   */
  @Schema(description = "The audit reason id.")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public WAuditReason name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Returns the audit reason text.
   *
   * @return name
   */
  @Schema(description = "The audit reason text.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WAuditReason wauditReason = (WAuditReason) o;
    return Objects.equals(this.id, wauditReason.id) && Objects.equals(this.name, wauditReason.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WAuditReason {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
