// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * Represents an entry link definition.
 */
@Schema(description = "Represents an entry link definition.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LinkDefinition {

    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("sourceLabel")
    private String sourceLabel = null;

    @JsonProperty("targetLabel")
    private String targetLabel = null;

    @JsonProperty("description")
    private String description = null;

    public LinkDefinition id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Returns the ID of the link definition.
     *
     * @return id
     */
    @Schema(description = "The ID of the link definition.")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LinkDefinition sourceLabel(String sourceLabel) {
        this.sourceLabel = sourceLabel;
        return this;
    }

    /**
     * Returns the label for the source entry in the link definition.
     *
     * @return sourceLabel
     */
    @Schema(description = "The label for the source entry in the link definition.")
    public String getSourceLabel() {
        return sourceLabel;
    }

    public void setSourceLabel(String sourceLabel) {
        this.sourceLabel = sourceLabel;
    }

    public LinkDefinition targetLabel(String targetLabel) {
        this.targetLabel = targetLabel;
        return this;
    }

    /**
     * Returns the label for the target entry in the link definition.
     *
     * @return targetLabel
     */
    @Schema(description = "The label for the target entry in the link definition.")
    public String getTargetLabel() {
        return targetLabel;
    }

    public void setTargetLabel(String targetLabel) {
        this.targetLabel = targetLabel;
    }

    public LinkDefinition description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Returns the description of the link definition.
     *
     * @return description
     */
    @Schema(description = "The description of the link definition.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LinkDefinition linkDefinition = (LinkDefinition) o;
        return Objects.equals(this.id, linkDefinition.id) && Objects.equals(this.sourceLabel, linkDefinition.sourceLabel) && Objects.equals(this.targetLabel, linkDefinition.targetLabel) && Objects.equals(this.description, linkDefinition.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sourceLabel, targetLabel, description);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class LinkDefinition {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    sourceLabel: ").append(toIndentedString(sourceLabel)).append("\n");
        sb.append("    targetLabel: ").append(toIndentedString(targetLabel)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
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
