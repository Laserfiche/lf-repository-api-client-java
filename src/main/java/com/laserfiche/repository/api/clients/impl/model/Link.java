package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.*;

/**
 * Represents a link between a source entry and a target entry.
 */
@Schema(description = "Represents a link between a source entry and a target entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Link {

    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("sourceId")
    private Integer sourceId = null;

    @JsonProperty("sourceFullPath")
    private String sourceFullPath = null;

    @JsonProperty("sourceLabel")
    private String sourceLabel = null;

    @JsonProperty("sourceLink")
    private String sourceLink = null;

    @JsonProperty("targetId")
    private Integer targetId = null;

    @JsonProperty("targetFullPath")
    private String targetFullPath = null;

    @JsonProperty("targetLabel")
    private String targetLabel = null;

    @JsonProperty("targetLink")
    private String targetLink = null;

    @JsonProperty("linkDefinitionId")
    private Integer linkDefinitionId = null;

    @JsonProperty("linkDefinitionDescription")
    private String linkDefinitionDescription = null;

    @JsonProperty("customProperties")
    private Map<String, String> customProperties = null;

    public Link id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Returns the ID of the represented link.
     * @return id
     */
    @Schema(description = "The ID of the represented link.")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Link description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Returns the description for the represented link.
     * @return description
     */
    @Schema(description = "The description for the represented link.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Link sourceId(Integer sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    /**
     * Returns the ID of the source entry of the represented link.
     * @return sourceId
     */
    @Schema(description = "The ID of the source entry of the represented link.")
    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Link sourceFullPath(String sourceFullPath) {
        this.sourceFullPath = sourceFullPath;
        return this;
    }

    /**
     * Returns the full path to the source entry of the represented link.
     * @return sourceFullPath
     */
    @Schema(description = "The full path to the source entry of the represented link.")
    public String getSourceFullPath() {
        return sourceFullPath;
    }

    public void setSourceFullPath(String sourceFullPath) {
        this.sourceFullPath = sourceFullPath;
    }

    public Link sourceLabel(String sourceLabel) {
        this.sourceLabel = sourceLabel;
        return this;
    }

    /**
     * Returns the label for the source entry in the link definition.
     * @return sourceLabel
     */
    @Schema(description = "The label for the source entry in the link definition.")
    public String getSourceLabel() {
        return sourceLabel;
    }

    public void setSourceLabel(String sourceLabel) {
        this.sourceLabel = sourceLabel;
    }

    public Link sourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
        return this;
    }

    /**
     * Returns the navigation link to the source entry.
     * @return sourceLink
     */
    @Schema(description = "The navigation link to the source entry.")
    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public Link targetId(Integer targetId) {
        this.targetId = targetId;
        return this;
    }

    /**
     * Returns the ID of the target entry of the represented link.
     * @return targetId
     */
    @Schema(description = "The ID of the target entry of the represented link.")
    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Link targetFullPath(String targetFullPath) {
        this.targetFullPath = targetFullPath;
        return this;
    }

    /**
     * Returns the full path to the target entry of the represented link.
     * @return targetFullPath
     */
    @Schema(description = "The full path to the target entry of the represented link.")
    public String getTargetFullPath() {
        return targetFullPath;
    }

    public void setTargetFullPath(String targetFullPath) {
        this.targetFullPath = targetFullPath;
    }

    public Link targetLabel(String targetLabel) {
        this.targetLabel = targetLabel;
        return this;
    }

    /**
     * Returns the label for the target entry in the link definition.
     * @return targetLabel
     */
    @Schema(description = "The label for the target entry in the link definition.")
    public String getTargetLabel() {
        return targetLabel;
    }

    public void setTargetLabel(String targetLabel) {
        this.targetLabel = targetLabel;
    }

    public Link targetLink(String targetLink) {
        this.targetLink = targetLink;
        return this;
    }

    /**
     * Returns the navigation link to the target entry.
     * @return targetLink
     */
    @Schema(description = "The navigation link to the target entry.")
    public String getTargetLink() {
        return targetLink;
    }

    public void setTargetLink(String targetLink) {
        this.targetLink = targetLink;
    }

    public Link linkDefinitionId(Integer linkDefinitionId) {
        this.linkDefinitionId = linkDefinitionId;
        return this;
    }

    /**
     * Returns the ID of the link definition.
     * @return linkDefinitionId
     */
    @Schema(description = "The ID of the link definition.")
    public Integer getLinkDefinitionId() {
        return linkDefinitionId;
    }

    public void setLinkDefinitionId(Integer linkDefinitionId) {
        this.linkDefinitionId = linkDefinitionId;
    }

    public Link linkDefinitionDescription(String linkDefinitionDescription) {
        this.linkDefinitionDescription = linkDefinitionDescription;
        return this;
    }

    /**
     * Returns the description of the link definition.
     * @return linkDefinitionDescription
     */
    @Schema(description = "The description of the link definition.")
    public String getLinkDefinitionDescription() {
        return linkDefinitionDescription;
    }

    public void setLinkDefinitionDescription(String linkDefinitionDescription) {
        this.linkDefinitionDescription = linkDefinitionDescription;
    }

    public Link customProperties(Map<String, String> customProperties) {
        this.customProperties = customProperties;
        return this;
    }

    public Link putCustomPropertiesItem(String key, String customPropertiesItem) {
        if (this.customProperties == null) {
            this.customProperties = new HashMap<String, String>();
        }
        this.customProperties.put(key, customPropertiesItem);
        return this;
    }

    /**
     * Returns the custom properties for the represented link.
     * @return customProperties
     */
    @Schema(description = "The custom properties for the represented link.")
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
        Link link = (Link) o;
        return Objects.equals(this.id, link.id) && Objects.equals(this.description, link.description) && Objects.equals(this.sourceId, link.sourceId) && Objects.equals(this.sourceFullPath, link.sourceFullPath) && Objects.equals(this.sourceLabel, link.sourceLabel) && Objects.equals(this.sourceLink, link.sourceLink) && Objects.equals(this.targetId, link.targetId) && Objects.equals(this.targetFullPath, link.targetFullPath) && Objects.equals(this.targetLabel, link.targetLabel) && Objects.equals(this.targetLink, link.targetLink) && Objects.equals(this.linkDefinitionId, link.linkDefinitionId) && Objects.equals(this.linkDefinitionDescription, link.linkDefinitionDescription) && Objects.equals(this.customProperties, link.customProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, sourceId, sourceFullPath, sourceLabel, sourceLink, targetId, targetFullPath, targetLabel, targetLink, linkDefinitionId, linkDefinitionDescription, customProperties);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Link {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    sourceId: ").append(toIndentedString(sourceId)).append("\n");
        sb.append("    sourceFullPath: ").append(toIndentedString(sourceFullPath)).append("\n");
        sb.append("    sourceLabel: ").append(toIndentedString(sourceLabel)).append("\n");
        sb.append("    sourceLink: ").append(toIndentedString(sourceLink)).append("\n");
        sb.append("    targetId: ").append(toIndentedString(targetId)).append("\n");
        sb.append("    targetFullPath: ").append(toIndentedString(targetFullPath)).append("\n");
        sb.append("    targetLabel: ").append(toIndentedString(targetLabel)).append("\n");
        sb.append("    targetLink: ").append(toIndentedString(targetLink)).append("\n");
        sb.append("    linkDefinitionId: ").append(toIndentedString(linkDefinitionId)).append("\n");
        sb.append("    linkDefinitionDescription: ").append(toIndentedString(linkDefinitionDescription)).append("\n");
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
