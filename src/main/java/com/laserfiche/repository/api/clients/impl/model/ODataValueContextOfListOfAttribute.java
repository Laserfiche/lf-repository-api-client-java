/*
 * Laserfiche Repository API
 * Welcome to the Laserfiche API Swagger Playground. You can try out any of our API calls against your live Laserfiche Cloud account. Visit the developer center for more details: <a href=\"https://developer.laserfiche.com\">https://developer.laserfiche.com</a><p><strong>Build# : </strong>eb72c77d640efc5f986310ccc43114fc25742dab_.20220610.1</p>
 *
 * OpenAPI spec version: 1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ODataValueContextOfListOfAttribute
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-06-21T09:31:30.649462900-04:00[America/New_York]")
public class ODataValueContextOfListOfAttribute extends ODataValueOfListOfAttribute {
  @SerializedName("@odata.nextLink")
  private String _atOdataNextLink = null;

  @SerializedName("@odata.count")
  private Integer _atOdataCount = null;

  public ODataValueContextOfListOfAttribute _atOdataNextLink(String _atOdataNextLink) {
    this._atOdataNextLink = _atOdataNextLink;
    return this;
  }

   /**
   * It contains a URL that allows retrieving the next subset of the requested collection.
   * @return _atOdataNextLink
  **/
  @Schema(description = "It contains a URL that allows retrieving the next subset of the requested collection.")
  public String getAtOdataNextLink() {
    return _atOdataNextLink;
  }

  public void setAtOdataNextLink(String _atOdataNextLink) {
    this._atOdataNextLink = _atOdataNextLink;
  }

  public ODataValueContextOfListOfAttribute _atOdataCount(Integer _atOdataCount) {
    this._atOdataCount = _atOdataCount;
    return this;
  }

   /**
   * It contains the count of a collection of entities or a collection of entity references.
   * @return _atOdataCount
  **/
  @Schema(description = "It contains the count of a collection of entities or a collection of entity references.")
  public Integer getAtOdataCount() {
    return _atOdataCount;
  }

  public void setAtOdataCount(Integer _atOdataCount) {
    this._atOdataCount = _atOdataCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ODataValueContextOfListOfAttribute odataValueContextOfListOfAttribute = (ODataValueContextOfListOfAttribute) o;
    return Objects.equals(this._atOdataNextLink, odataValueContextOfListOfAttribute._atOdataNextLink) &&
        Objects.equals(this._atOdataCount, odataValueContextOfListOfAttribute._atOdataCount) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(_atOdataNextLink, _atOdataCount, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ODataValueContextOfListOfAttribute {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    _atOdataNextLink: ").append(toIndentedString(_atOdataNextLink)).append("\n");
    sb.append("    _atOdataCount: ").append(toIndentedString(_atOdataCount)).append("\n");
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
