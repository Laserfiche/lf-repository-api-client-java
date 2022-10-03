package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ODataValueContextOfIListOfWFieldInfo extends ODataValueOfIListOfWFieldInfo {

    @JsonProperty("@odata.nextLink")
    private String odataNextLink = null;

    @JsonProperty("@odata.count")
    private Integer odataCount = null;

    public ODataValueContextOfIListOfWFieldInfo odataNextLink(String odataNextLink) {
        this.odataNextLink = odataNextLink;
        return this;
    }

    @Schema(description = "It contains a URL that allows retrieving the next subset of the requested collection.")
    public String getOdataNextLink() {
        return odataNextLink;
    }

    public void setOdataNextLink(String odataNextLink) {
        this.odataNextLink = odataNextLink;
    }

    public ODataValueContextOfIListOfWFieldInfo odataCount(Integer odataCount) {
        this.odataCount = odataCount;
        return this;
    }

    @Schema(description = "It contains the count of a collection of entities or a collection of entity references.")
    public Integer getOdataCount() {
        return odataCount;
    }

    public void setOdataCount(Integer odataCount) {
        this.odataCount = odataCount;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ODataValueContextOfIListOfWFieldInfo odataValueContextOfIListOfWFieldInfo = (ODataValueContextOfIListOfWFieldInfo) o;
        return Objects.equals(this.odataNextLink, odataValueContextOfIListOfWFieldInfo.odataNextLink) && Objects.equals(
                this.odataCount, odataValueContextOfIListOfWFieldInfo.odataCount) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(odataNextLink, odataCount, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ODataValueContextOfIListOfWFieldInfo {\n");
        sb
                .append("    ")
                .append(toIndentedString(super.toString()))
                .append("\n");
        sb
                .append("    odataNextLink: ")
                .append(toIndentedString(odataNextLink))
                .append("\n");
        sb
                .append("    odataCount: ")
                .append(toIndentedString(odataCount))
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
