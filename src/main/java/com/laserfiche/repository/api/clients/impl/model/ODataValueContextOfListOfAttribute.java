package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ODataValueContextOfListOfAttribute extends ODataValueOfListOfAttribute {

    @JsonProperty("@odata.nextLink")
    private String _atOdataNextLink = null;

    @JsonProperty("@odata.count")
    private Integer _atOdataCount = null;

    public ODataValueContextOfListOfAttribute _atOdataNextLink(String _atOdataNextLink) {
        this._atOdataNextLink = _atOdataNextLink;
        return this;
    }

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
        return Objects.equals(this._atOdataNextLink,
                odataValueContextOfListOfAttribute._atOdataNextLink) && Objects.equals(this._atOdataCount,
                odataValueContextOfListOfAttribute._atOdataCount) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_atOdataNextLink, _atOdataCount, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ODataValueContextOfListOfAttribute {\n");
        sb
                .append("    ")
                .append(toIndentedString(super.toString()))
                .append("\n");
        sb
                .append("    _atOdataNextLink: ")
                .append(toIndentedString(_atOdataNextLink))
                .append("\n");
        sb
                .append("    _atOdataCount: ")
                .append(toIndentedString(_atOdataCount))
                .append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o
                .toString()
                .replace("\n", "\n    ");
    }
}
