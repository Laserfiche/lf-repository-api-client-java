/*
 * Laserfiche Repository API
 * Welcome to the Laserfiche API Swagger Playground. You can try out any of our API calls against your live Laserfiche Cloud account. Visit the developer center for more details: <a href=\"https://developer.laserfiche.com\">https://developer.laserfiche.com</a><p><strong>Build# : </strong>eb72c77d640efc5f986310ccc43114fc25742dab_.20220610.1</p>
 *
 * OpenAPI spec version: 2-alpha
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * SimpleExportWatermark
 */

public class SimpleExportWatermark {
  @SerializedName("watermarkText")
  private String watermarkText = "";

  @SerializedName("watermarkPosition")
  private WatermarkPosition watermarkPosition = null;

  @SerializedName("watermarkRotationAngle")
  private Integer watermarkRotationAngle = 0;

  @SerializedName("watermarkPageSpanPercentage")
  private Integer watermarkPageSpanPercentage = 50;

  public SimpleExportWatermark watermarkText(String watermarkText) {
    this.watermarkText = watermarkText;
    return this;
  }

   /**
   * The text of the watermark. The value must be a string with a length of atmost 100 characters.
   * @return watermarkText
  **/
  @Schema(description = "The text of the watermark. The value must be a string with a length of atmost 100 characters.")
  public String getWatermarkText() {
    return watermarkText;
  }

  public void setWatermarkText(String watermarkText) {
    this.watermarkText = watermarkText;
  }

  public SimpleExportWatermark watermarkPosition(WatermarkPosition watermarkPosition) {
    this.watermarkPosition = watermarkPosition;
    return this;
  }

   /**
   * The position of the watermark.
   * @return watermarkPosition
  **/
  @Schema(description = "The position of the watermark.")
  public WatermarkPosition getWatermarkPosition() {
    return watermarkPosition;
  }

  public void setWatermarkPosition(WatermarkPosition watermarkPosition) {
    this.watermarkPosition = watermarkPosition;
  }

  public SimpleExportWatermark watermarkRotationAngle(Integer watermarkRotationAngle) {
    this.watermarkRotationAngle = watermarkRotationAngle;
    return this;
  }

   /**
   * The rotation angle of the watermark. The value must be between 0 and 360 (inclusive).
   * @return watermarkRotationAngle
  **/
  @Schema(description = "The rotation angle of the watermark. The value must be between 0 and 360 (inclusive).")
  public Integer getWatermarkRotationAngle() {
    return watermarkRotationAngle;
  }

  public void setWatermarkRotationAngle(Integer watermarkRotationAngle) {
    this.watermarkRotationAngle = watermarkRotationAngle;
  }

  public SimpleExportWatermark watermarkPageSpanPercentage(Integer watermarkPageSpanPercentage) {
    this.watermarkPageSpanPercentage = watermarkPageSpanPercentage;
    return this;
  }

   /**
   * The percentage of the page that the watermark spans on. The value must be between 1 and 100 (inclusive).
   * @return watermarkPageSpanPercentage
  **/
  @Schema(description = "The percentage of the page that the watermark spans on. The value must be between 1 and 100 (inclusive).")
  public Integer getWatermarkPageSpanPercentage() {
    return watermarkPageSpanPercentage;
  }

  public void setWatermarkPageSpanPercentage(Integer watermarkPageSpanPercentage) {
    this.watermarkPageSpanPercentage = watermarkPageSpanPercentage;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SimpleExportWatermark simpleExportWatermark = (SimpleExportWatermark) o;
    return Objects.equals(this.watermarkText, simpleExportWatermark.watermarkText) &&
        Objects.equals(this.watermarkPosition, simpleExportWatermark.watermarkPosition) &&
        Objects.equals(this.watermarkRotationAngle, simpleExportWatermark.watermarkRotationAngle) &&
        Objects.equals(this.watermarkPageSpanPercentage, simpleExportWatermark.watermarkPageSpanPercentage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(watermarkText, watermarkPosition, watermarkRotationAngle, watermarkPageSpanPercentage);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SimpleExportWatermark {\n");
    
    sb.append("    watermarkText: ").append(toIndentedString(watermarkText)).append("\n");
    sb.append("    watermarkPosition: ").append(toIndentedString(watermarkPosition)).append("\n");
    sb.append("    watermarkRotationAngle: ").append(toIndentedString(watermarkRotationAngle)).append("\n");
    sb.append("    watermarkPageSpanPercentage: ").append(toIndentedString(watermarkPageSpanPercentage)).append("\n");
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
