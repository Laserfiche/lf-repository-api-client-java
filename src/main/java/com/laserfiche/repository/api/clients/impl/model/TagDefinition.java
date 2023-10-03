package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

/**
 * Represents an entry tag definition.
 */
@Schema(description = "Represents an entry tag definition.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagDefinition {

    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("displayName")
    private String displayName = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("isSecure")
    private Boolean isSecure = null;

    @JsonProperty("watermark")
    private TagDefinitionWatermark watermark = null;

    public TagDefinition id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Returns the ID of the tag definition.
     * @return id
     */
    @Schema(description = "The ID of the tag definition.")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TagDefinition name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Returns the name of the tag definition.
     * @return name
     */
    @Schema(description = "The name of the tag definition.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TagDefinition displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Returns the localized name of the tag definition.
     * @return displayName
     */
    @Schema(description = "The localized name of the tag definition.")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public TagDefinition description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Returns the description of the tag definition.
     * @return description
     */
    @Schema(description = "The description of the tag definition.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TagDefinition isSecure(Boolean isSecure) {
        this.isSecure = isSecure;
        return this;
    }

    /**
     * Returns a boolean indicating whether or not the tag definition is classified as a security tag (true) or an informational tag (false).
     * @return isSecure
     */
    @Schema(
            description =
                    "A boolean indicating whether or not the tag definition is classified as a security tag (true) or an informational tag (false).")
    @JsonProperty("isSecure")
    public Boolean isSecure() {
        return isSecure;
    }

    public void setIsSecure(Boolean isSecure) {
        this.isSecure = isSecure;
    }

    public TagDefinition watermark(TagDefinitionWatermark watermark) {
        this.watermark = watermark;
        return this;
    }

    /**
     * Returns watermark
     * @return watermark
     */
    @Schema(description = "")
    public TagDefinitionWatermark getWatermark() {
        return watermark;
    }

    public void setWatermark(TagDefinitionWatermark watermark) {
        this.watermark = watermark;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TagDefinition tagDefinition = (TagDefinition) o;
        return Objects.equals(this.id, tagDefinition.id)
                && Objects.equals(this.name, tagDefinition.name)
                && Objects.equals(this.displayName, tagDefinition.displayName)
                && Objects.equals(this.description, tagDefinition.description)
                && Objects.equals(this.isSecure, tagDefinition.isSecure)
                && Objects.equals(this.watermark, tagDefinition.watermark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, displayName, description, isSecure, watermark);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TagDefinition {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    isSecure: ").append(toIndentedString(isSecure)).append("\n");
        sb.append("    watermark: ").append(toIndentedString(watermark)).append("\n");
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
