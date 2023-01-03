package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
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

    /**
     * Returns the target entry ID to create a link to.
     *
     * @return targetId
     */
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

    /**
     * Returns the link type ID to create the link with.
     *
     * @return linkTypeId
     */
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

    /**
     * Returns custom properties (key, value pairs) to be added to the link
     *
     * @return customProperties
     */
    @Schema(description = "Custom properties (key, value pairs) to be added to the link")
    public Map<String, String> getCustomProperties() {
        return customProperties;
    }

    public void setCustomProperties(Map<String, String> customProperties) {
        this.customProperties = customProperties;
    }

    @Override
    public boolean equals(Object o) {
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

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o
                .toString()
                .replace("\n", "\n    ");
    }
}
