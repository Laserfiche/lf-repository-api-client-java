package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;
import org.threeten.bp.OffsetDateTime;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ODataValueOfDateTime {

  @JsonProperty("value")
  private OffsetDateTime value = null;

  public ODataValueOfDateTime value(OffsetDateTime value) {
    this.value = value;
    return this;
  }

  /**
   * Returns value
   *
   * @return value
   */
  @Schema(description = "")
  public OffsetDateTime getValue() {
    return value;
  }

  public void setValue(OffsetDateTime value) {
    this.value = value;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ODataValueOfDateTime odataValueOfDateTime = (ODataValueOfDateTime) o;
    return Objects.equals(this.value, odataValueOfDateTime.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ODataValueOfDateTime {\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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
