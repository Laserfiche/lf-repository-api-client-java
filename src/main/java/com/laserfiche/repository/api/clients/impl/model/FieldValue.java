package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.*;
import com.laserfiche.api.client.model.EntryFieldValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldValue extends EntryFieldValue {

  @JsonProperty("groupId")
  private Integer groupId = null;

  public FieldValue groupId(Integer groupId) {
    this.groupId = groupId;
    return this;
  }

  /**
   * Returns he group id of the multi value field group. If the field is not a part of a multi value
   * field group, then there is no group id.
   *
   * @return groupId
   */
  @Schema(
      description =
          "The group id of the multi value field group. If the field is not a part of a multi value field group, then there is no group id.")
  public Integer getGroupId() {
    return groupId;
  }

  public void setGroupId(Integer groupId) {
    this.groupId = groupId;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FieldValue fieldValue = (FieldValue) o;
    return Objects.equals(this.groupId, fieldValue.groupId) && super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupId, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FieldValue {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
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
