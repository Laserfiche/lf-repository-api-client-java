package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.*;
import com.laserfiche.api.client.model.TemplateFieldInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ODataValueOfIListOfTemplateFieldInfo {

  @JsonProperty("value")
  private List<TemplateFieldInfo> value = null;

  public ODataValueOfIListOfTemplateFieldInfo value(List<TemplateFieldInfo> value) {
    this.value = value;
    return this;
  }

  public ODataValueOfIListOfTemplateFieldInfo addValueItem(TemplateFieldInfo valueItem) {
    if (this.value == null) {
      this.value = new ArrayList<TemplateFieldInfo>();
    }
    this.value.add(valueItem);
    return this;
  }

  /**
   * Returns et value
   *
   * @return value
   */
  @Schema(description = "")
  public List<TemplateFieldInfo> getValue() {
    return value;
  }

  public void setValue(List<TemplateFieldInfo> value) {
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
    ODataValueOfIListOfTemplateFieldInfo odataValueOfIListOfTemplateFieldInfo =
        (ODataValueOfIListOfTemplateFieldInfo) o;
    return Objects.equals(this.value, odataValueOfIListOfTemplateFieldInfo.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ODataValueOfIListOfTemplateFieldInfo {\n");
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
