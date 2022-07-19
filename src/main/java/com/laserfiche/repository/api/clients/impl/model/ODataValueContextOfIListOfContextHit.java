package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
/**
 * ODataValueContextOfIListOfContextHit
 */

public class ODataValueContextOfIListOfContextHit extends ODataValueOfIListOfContextHit {
  @SerializedName("@odata.nextLink")
  private String _atOdataNextLink = null;

  @SerializedName("@odata.count")
  private Integer _atOdataCount = null;

  public ODataValueContextOfIListOfContextHit _atOdataNextLink(String _atOdataNextLink) {
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

  public ODataValueContextOfIListOfContextHit _atOdataCount(Integer _atOdataCount) {
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
    ODataValueContextOfIListOfContextHit odataValueContextOfIListOfContextHit = (ODataValueContextOfIListOfContextHit) o;
    return Objects.equals(this._atOdataNextLink, odataValueContextOfIListOfContextHit._atOdataNextLink) &&
        Objects.equals(this._atOdataCount, odataValueContextOfIListOfContextHit._atOdataCount) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(_atOdataNextLink, _atOdataCount, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ODataValueContextOfIListOfContextHit {\n");
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
