package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-10-17T11:38:41.655-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PutLinksRequest {

    @JsonProperty("targetId")
    private Integer targetId = null;

    @JsonProperty("linkTypeId")
    private Integer linkTypeId = null;

    @JsonProperty("customProperties")
    private Map<String, String> customProperties = null;

    public PutLinksRequest targetId(Integer targetId) {
        this.targetId = targetId;
        return this;
    }

    @Schema(description = "The target entry ID to create a link to.")
    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public PutLinksRequest linkTypeId(Integer linkTypeId) {
        this.linkTypeId = linkTypeId;
        return this;
    }

    @Schema(description = "The link type ID to create the link with.")
    public Integer getLinkTypeId() {
        return linkTypeId;
    }

    public void setLinkTypeId(Integer linkTypeId) {
        this.linkTypeId = linkTypeId;
    }

    public PutLinksRequest customProperties(Map<String, String> customProperties) {
        this.customProperties = customProperties;
        return this;
    }

    public PutLinksRequest putCustomPropertiesItem(String key, String customPropertiesItem) {
        if (this.customProperties == null) {
            this.customProperties = new HashMap<String, String>();
        }
        this.customProperties.put(key, customPropertiesItem);
        return this;
    }

    @Schema(description = "Custom properties (key, value pairs) to be added to the link")
    public Map<String, String> getCustomProperties() {
        return customProperties;
    }

    public void setCustomProperties(Map<String, String> customProperties) {
        this.customProperties = customProperties;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PutLinksRequest putLinksRequest = (PutLinksRequest) o;
        return Objects.equals(this.targetId, putLinksRequest.targetId) && Objects.equals(this.linkTypeId,
                putLinksRequest.linkTypeId) && Objects.equals(this.customProperties, putLinksRequest.customProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetId, linkTypeId, customProperties);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PutLinksRequest {\n");
        sb
                .append("    targetId: ")
                .append(toIndentedString(targetId))
                .append("\n");
        sb
                .append("    linkTypeId: ")
                .append(toIndentedString(linkTypeId))
                .append("\n");
        sb
                .append("    customProperties: ")
                .append(toIndentedString(customProperties))
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
