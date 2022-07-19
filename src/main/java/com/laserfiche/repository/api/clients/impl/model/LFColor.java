package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
/**
 * LFColor
 */

public class LFColor {
  @SerializedName("a")
  private Integer a = null;

  @SerializedName("r")
  private Integer r = null;

  @SerializedName("g")
  private Integer g = null;

  @SerializedName("b")
  private Integer b = null;

  public LFColor a(Integer a) {
    this.a = a;
    return this;
  }

   /**
   * Get a
   * @return a
  **/
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
   * Get r
   * @return r
  **/
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
   * Get g
   * @return g
  **/
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
   * Get b
   * @return b
  **/
  @Schema(description = "")
  public Integer getB() {
    return b;
  }

  public void setB(Integer b) {
    this.b = b;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LFColor lfColor = (LFColor) o;
    return Objects.equals(this.a, lfColor.a) &&
        Objects.equals(this.r, lfColor.r) &&
        Objects.equals(this.g, lfColor.g) &&
        Objects.equals(this.b, lfColor.b);
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
