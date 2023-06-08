package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WEntryLinkInfo {

    @JsonProperty("linkId")
    private Integer linkId = null;

    @JsonProperty("sourceId")
    private Integer sourceId = null;

    @JsonProperty("sourceFullPath")
    private String sourceFullPath = null;

    @JsonProperty("sourceLabel")
    private String sourceLabel = null;

    @JsonProperty("targetId")
    private Integer targetId = null;

    @JsonProperty("targetFullPath")
    private String targetFullPath = null;

    @JsonProperty("targetLabel")
    private String targetLabel = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("linkTypeDescription")
    private String linkTypeDescription = null;

    @JsonProperty("linkTypeId")
    private Integer linkTypeId = null;

    @JsonProperty("linkProperties")
    private Map<String, String> linkProperties = null;

    @JsonProperty("sourceLink")
    private String sourceLink = null;

    @JsonProperty("targetLink")
    private String targetLink = null;

    public WEntryLinkInfo linkId(Integer linkId) {
        this.linkId = linkId;
        return this;
    }

    /**
     * Returns the ID of the entry link.
     *
     * @return linkId
     */
    @Schema(description = "The ID of the entry link.")
    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public WEntryLinkInfo sourceId(Integer sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    /**
     * Returns the ID of the source entry of the represented link.
     *
     * @return sourceId
     */
    @Schema(description = "The ID of the source entry of the represented link.")
    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public WEntryLinkInfo sourceFullPath(String sourceFullPath) {
        this.sourceFullPath = sourceFullPath;
        return this;
    }

    /**
     * Returns the full path to the source entry of the represented link.
     *
     * @return sourceFullPath
     */
    @Schema(description = "The full path to the source entry of the represented link.")
    public String getSourceFullPath() {
        return sourceFullPath;
    }

    public void setSourceFullPath(String sourceFullPath) {
        this.sourceFullPath = sourceFullPath;
    }

    public WEntryLinkInfo sourceLabel(String sourceLabel) {
        this.sourceLabel = sourceLabel;
        return this;
    }

    /**
     * Returns the label for the source entry in the link type.
     *
     * @return sourceLabel
     */
    @Schema(description = "The label for the source entry in the link type.")
    public String getSourceLabel() {
        return sourceLabel;
    }

    public void setSourceLabel(String sourceLabel) {
        this.sourceLabel = sourceLabel;
    }

    public WEntryLinkInfo targetId(Integer targetId) {
        this.targetId = targetId;
        return this;
    }

    /**
     * Returns the ID of the target entry of the represented link.
     *
     * @return targetId
     */
    @Schema(description = "The ID of the target entry of the represented link.")
    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public WEntryLinkInfo targetFullPath(String targetFullPath) {
        this.targetFullPath = targetFullPath;
        return this;
    }

    /**
     * Returns the full path to the target entry of the represented link.
     *
     * @return targetFullPath
     */
    @Schema(description = "The full path to the target entry of the represented link.")
    public String getTargetFullPath() {
        return targetFullPath;
    }

    public void setTargetFullPath(String targetFullPath) {
        this.targetFullPath = targetFullPath;
    }

    public WEntryLinkInfo targetLabel(String targetLabel) {
        this.targetLabel = targetLabel;
        return this;
    }

    /**
     * Returns the label for the target entry in the link type.
     *
     * @return targetLabel
     */
    @Schema(description = "The label for the target entry in the link type.")
    public String getTargetLabel() {
        return targetLabel;
    }

    public void setTargetLabel(String targetLabel) {
        this.targetLabel = targetLabel;
    }

    public WEntryLinkInfo description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Returns the descriptive text for the represented entry link.
     *
     * @return description
     */
    @Schema(description = "The descriptive text for the represented entry link.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WEntryLinkInfo linkTypeDescription(String linkTypeDescription) {
        this.linkTypeDescription = linkTypeDescription;
        return this;
    }

    /**
     * Returns the description of the link type.
     *
     * @return linkTypeDescription
     */
    @Schema(description = "The description of the link type.")
    public String getLinkTypeDescription() {
        return linkTypeDescription;
    }

    public void setLinkTypeDescription(String linkTypeDescription) {
        this.linkTypeDescription = linkTypeDescription;
    }

    public WEntryLinkInfo linkTypeId(Integer linkTypeId) {
        this.linkTypeId = linkTypeId;
        return this;
    }

    /**
     * Returns the ID of the entry link type.
     *
     * @return linkTypeId
     */
    @Schema(description = "The ID of the entry link type.")
    public Integer getLinkTypeId() {
        return linkTypeId;
    }

    public void setLinkTypeId(Integer linkTypeId) {
        this.linkTypeId = linkTypeId;
    }

    public WEntryLinkInfo linkProperties(Map<String, String> linkProperties) {
        this.linkProperties = linkProperties;
        return this;
    }

    public WEntryLinkInfo putLinkPropertiesItem(String key, String linkPropertiesItem) {
        if (this.linkProperties == null) {
            this.linkProperties = new HashMap<String, String>();
        }
        this.linkProperties.put(key, linkPropertiesItem);
        return this;
    }

    /**
     * Returns the properties for the entry link.
     *
     * @return linkProperties
     */
    @Schema(description = "The properties for the entry link.")
    public Map<String, String> getLinkProperties() {
        return linkProperties;
    }

    public void setLinkProperties(Map<String, String> linkProperties) {
        this.linkProperties = linkProperties;
    }

    public WEntryLinkInfo sourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
        return this;
    }

    /**
     * Returns the navigation link to the source entry.
     *
     * @return sourceLink
     */
    @Schema(description = "The navigation link to the source entry.")
    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public WEntryLinkInfo targetLink(String targetLink) {
        this.targetLink = targetLink;
        return this;
    }

    /**
     * Returns the navigation link to the target entry.
     *
     * @return targetLink
     */
    @Schema(description = "The navigation link to the target entry.")
    public String getTargetLink() {
        return targetLink;
    }

    public void setTargetLink(String targetLink) {
        this.targetLink = targetLink;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WEntryLinkInfo wentryLinkInfo = (WEntryLinkInfo) o;
        return Objects.equals(this.linkId, wentryLinkInfo.linkId)
                && Objects.equals(this.sourceId, wentryLinkInfo.sourceId)
                && Objects.equals(this.sourceFullPath, wentryLinkInfo.sourceFullPath)
                && Objects.equals(this.sourceLabel, wentryLinkInfo.sourceLabel)
                && Objects.equals(this.targetId, wentryLinkInfo.targetId)
                && Objects.equals(this.targetFullPath, wentryLinkInfo.targetFullPath)
                && Objects.equals(this.targetLabel, wentryLinkInfo.targetLabel)
                && Objects.equals(this.description, wentryLinkInfo.description)
                && Objects.equals(this.linkTypeDescription, wentryLinkInfo.linkTypeDescription)
                && Objects.equals(this.linkTypeId, wentryLinkInfo.linkTypeId)
                && Objects.equals(this.linkProperties, wentryLinkInfo.linkProperties)
                && Objects.equals(this.sourceLink, wentryLinkInfo.sourceLink)
                && Objects.equals(this.targetLink, wentryLinkInfo.targetLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                linkId,
                sourceId,
                sourceFullPath,
                sourceLabel,
                targetId,
                targetFullPath,
                targetLabel,
                description,
                linkTypeDescription,
                linkTypeId,
                linkProperties,
                sourceLink,
                targetLink);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class WEntryLinkInfo {\n");
        sb.append("    linkId: ").append(toIndentedString(linkId)).append("\n");
        sb.append("    sourceId: ").append(toIndentedString(sourceId)).append("\n");
        sb.append("    sourceFullPath: ").append(toIndentedString(sourceFullPath)).append("\n");
        sb.append("    sourceLabel: ").append(toIndentedString(sourceLabel)).append("\n");
        sb.append("    targetId: ").append(toIndentedString(targetId)).append("\n");
        sb.append("    targetFullPath: ").append(toIndentedString(targetFullPath)).append("\n");
        sb.append("    targetLabel: ").append(toIndentedString(targetLabel)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    linkTypeDescription: ")
                .append(toIndentedString(linkTypeDescription))
                .append("\n");
        sb.append("    linkTypeId: ").append(toIndentedString(linkTypeId)).append("\n");
        sb.append("    linkProperties: ").append(toIndentedString(linkProperties)).append("\n");
        sb.append("    sourceLink: ").append(toIndentedString(sourceLink)).append("\n");
        sb.append("    targetLink: ").append(toIndentedString(targetLink)).append("\n");
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
