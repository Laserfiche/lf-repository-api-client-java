package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Response containing a collection of Tag.
 */
@Schema(description = "Response containing a collection of Tag.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagCollectionResponse {

    @JsonProperty("@odata.nextLink")
    private String odataNextLink = null;

    @JsonProperty("@odata.count")
    private Integer odataCount = null;

    @JsonProperty("value")
    private List<Tag> value = null;

    public TagCollectionResponse odataNextLink(String odataNextLink) {
        this.odataNextLink = odataNextLink;
        return this;
    }

    /**
     * Returns a URL to retrieve the next page of the requested collection.
     *
     * @return _atOdataNextLink
     */
    @Schema(description = "A URL to retrieve the next page of the requested collection.")
    public String getOdataNextLink() {
        return odataNextLink;
    }

    public void setOdataNextLink(String odataNextLink) {
        this.odataNextLink = odataNextLink;
    }

    public TagCollectionResponse odataCount(Integer odataCount) {
        this.odataCount = odataCount;
        return this;
    }

    /**
     * Returns the total count of items within a collection.
     *
     * @return _atOdataCount
     */
    @Schema(description = "The total count of items within a collection.")
    public Integer getOdataCount() {
        return odataCount;
    }

    public void setOdataCount(Integer odataCount) {
        this.odataCount = odataCount;
    }

    public TagCollectionResponse value(List<Tag> value) {
        this.value = value;
        return this;
    }

    public TagCollectionResponse addValueItem(Tag valueItem) {
        if (this.value == null) {
            this.value = new ArrayList<Tag>();
        }
        this.value.add(valueItem);
        return this;
    }

    /**
     * Returns value
     *
     * @return value
     */
    @Schema(description = "")
    public List<Tag> getValue() {
        return value;
    }

    public void setValue(List<Tag> value) {
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
        TagCollectionResponse tagCollectionResponse = (TagCollectionResponse) o;
        return Objects.equals(this.odataNextLink, tagCollectionResponse.odataNextLink) && Objects.equals(this.odataCount, tagCollectionResponse.odataCount) && Objects.equals(this.value, tagCollectionResponse.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(odataNextLink, odataCount, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TagCollectionResponse {\n");
        sb.append("    odataNextLink: ").append(toIndentedString(odataNextLink)).append("\n");
        sb.append("    odataCount: ").append(toIndentedString(odataCount)).append("\n");
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
