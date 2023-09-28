package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;

/**
 * Response containing a collection of AuditReason.
 */
@Schema(description = "Response containing a collection of AuditReason.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuditReasonCollectionResponse {

    @JsonProperty("@odata.nextLink")
    private String odataNextLink = null;

    @JsonProperty("@odata.count")
    private Integer odataCount = null;

    @JsonProperty("value")
    private List<AuditReason> value = null;

    public AuditReasonCollectionResponse odataNextLink(String odataNextLink) {
        this.odataNextLink = odataNextLink;
        return this;
    }

    /**
     * Returns a URL to retrieve the next page of the requested collection.
     * @return _atOdataNextLink
     */
    @Schema(description = "A URL to retrieve the next page of the requested collection.")
    public String getOdataNextLink() {
        return odataNextLink;
    }

    public void setOdataNextLink(String odataNextLink) {
        this.odataNextLink = odataNextLink;
    }

    public AuditReasonCollectionResponse odataCount(Integer odataCount) {
        this.odataCount = odataCount;
        return this;
    }

    /**
     * Returns the total count of items within a collection.
     * @return _atOdataCount
     */
    @Schema(description = "The total count of items within a collection.")
    public Integer getOdataCount() {
        return odataCount;
    }

    public void setOdataCount(Integer odataCount) {
        this.odataCount = odataCount;
    }

    public AuditReasonCollectionResponse value(List<AuditReason> value) {
        this.value = value;
        return this;
    }

    public AuditReasonCollectionResponse addValueItem(AuditReason valueItem) {
        if (this.value == null) {
            this.value = new ArrayList<AuditReason>();
        }
        this.value.add(valueItem);
        return this;
    }

    /**
     * Returns value
     * @return value
     */
    @Schema(description = "")
    public List<AuditReason> getValue() {
        return value;
    }

    public void setValue(List<AuditReason> value) {
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
        AuditReasonCollectionResponse auditReasonCollectionResponse = (AuditReasonCollectionResponse) o;
        return Objects.equals(this.odataNextLink, auditReasonCollectionResponse.odataNextLink) && Objects.equals(this.odataCount, auditReasonCollectionResponse.odataCount) && Objects.equals(this.value, auditReasonCollectionResponse.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(odataNextLink, odataCount, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AuditReasonCollectionResponse {\n");
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
