package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.*;

/**
 * Represents a link that will be assigned to the entry.
 */
@Schema(description = "Represents a link that will be assigned to the entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LinkToUpdate {

    @JsonProperty("linkDefinitionId")
    private Integer linkDefinitionId = null;

    @JsonProperty("otherEntryId")
    private Integer otherEntryId = null;

    @JsonProperty("isSource")
    private Boolean isSource = true;

    @JsonProperty("customProperties")
    private Map<String, String> customProperties = null;

    public LinkToUpdate linkDefinitionId(Integer linkDefinitionId) {
        this.linkDefinitionId = linkDefinitionId;
        return this;
    }

    /**
     * Returns the id of the link definition to be assigned to the entry.
     * @return linkDefinitionId
     */
    @Schema(required = true, description = "The id of the link definition to be assigned to the entry.")
    public Integer getLinkDefinitionId() {
        return linkDefinitionId;
    }

    public void setLinkDefinitionId(Integer linkDefinitionId) {
        this.linkDefinitionId = linkDefinitionId;
    }

    public LinkToUpdate otherEntryId(Integer otherEntryId) {
        this.otherEntryId = otherEntryId;
        return this;
    }

    /**
     * Returns the id of the other entry to be linked to the entry.
     * @return otherEntryId
     */
    @Schema(required = true, description = "The id of the other entry to be linked to the entry.")
    public Integer getOtherEntryId() {
        return otherEntryId;
    }

    public void setOtherEntryId(Integer otherEntryId) {
        this.otherEntryId = otherEntryId;
    }

    public LinkToUpdate isSource(Boolean isSource) {
        this.isSource = isSource;
        return this;
    }

    /**
     * Returns whether the entry is the source for the link. The default value is true.
     * @return isSource
     */
    @Schema(description = "Whether the entry is the source for the link. The default value is true.")
    @JsonProperty("isSource")
    public Boolean isSource() {
        return isSource;
    }

    public void setIsSource(Boolean isSource) {
        this.isSource = isSource;
    }

    public LinkToUpdate customProperties(Map<String, String> customProperties) {
        this.customProperties = customProperties;
        return this;
    }

    public LinkToUpdate putCustomPropertiesItem(String key, String customPropertiesItem) {
        if (this.customProperties == null) {
            this.customProperties = new HashMap<String, String>();
        }
        this.customProperties.put(key, customPropertiesItem);
        return this;
    }

    /**
     * Returns custom properties (key, value pairs) to be added to the link.
     * @return customProperties
     */
    @Schema(description = "Custom properties (key, value pairs) to be added to the link.")
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
        LinkToUpdate linkToUpdate = (LinkToUpdate) o;
        return Objects.equals(this.linkDefinitionId, linkToUpdate.linkDefinitionId) && Objects.equals(this.otherEntryId, linkToUpdate.otherEntryId) && Objects.equals(this.isSource, linkToUpdate.isSource) && Objects.equals(this.customProperties, linkToUpdate.customProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkDefinitionId, otherEntryId, isSource, customProperties);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LinkToUpdate {\n");
        sb.append("    linkDefinitionId: ").append(toIndentedString(linkDefinitionId)).append("\n");
        sb.append("    otherEntryId: ").append(toIndentedString(otherEntryId)).append("\n");
        sb.append("    isSource: ").append(toIndentedString(isSource)).append("\n");
        sb.append("    customProperties: ").append(toIndentedString(customProperties)).append("\n");
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
