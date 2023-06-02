package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen",
    date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LFColor {

  @JsonProperty("a")
  private Integer a = null;

  @JsonProperty("r")
  private Integer r = null;

  @JsonProperty("g")
  private Integer g = null;

  @JsonProperty("b")
  private Integer b = null;

  public LFColor a(Integer a) {
    this.a = a;
    return this;
  }

  /**
   * Returns a
   *
   * @return a
   */
  @Schema(description = "")
  public Integer getA() {
    return a;
  }

  public void setA(Integer a) {
    this.a = a;
  }

  public LFColor r(Integer r) {
    this.r = r;
    return this;
  }

  /**
   * Returns r
   *
   * @return r
   */
  @Schema(description = "")
  public Integer getR() {
    return r;
  }

  public void setR(Integer r) {
    this.r = r;
  }

  public LFColor g(Integer g) {
    this.g = g;
    return this;
  }

  /**
   * Returns g
   *
   * @return g
   */
  @Schema(description = "")
  public Integer getG() {
    return g;
  }

  public void setG(Integer g) {
    this.g = g;
  }

  public LFColor b(Integer b) {
    this.b = b;
    return this;
  }

  /**
   * Returns b
   *
   * @return b
   */
  @Schema(description = "")
  public Integer getB() {
    return b;
  }

  public void setB(Integer b) {
    this.b = b;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LFColor lfColor = (LFColor) o;
    return Objects.equals(this.a, lfColor.a)
        && Objects.equals(this.r, lfColor.r)
        && Objects.equals(this.g, lfColor.g)
        && Objects.equals(this.b, lfColor.b);
  }

  @Override
  public int hashCode() {
    return Objects.hash(a, r, g, b);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LFColor {\n");
    sb.append("    a: ").append(toIndentedString(a)).append("\n");
    sb.append("    r: ").append(toIndentedString(r)).append("\n");
    sb.append("    g: ").append(toIndentedString(g)).append("\n");
    sb.append("    b: ").append(toIndentedString(b)).append("\n");
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
