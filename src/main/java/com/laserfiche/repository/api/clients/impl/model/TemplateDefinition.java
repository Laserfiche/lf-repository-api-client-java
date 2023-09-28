package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.*;

/**
 * Represents a template definition.
 */
@Schema(description = "Represents a template definition.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemplateDefinition {

    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("displayName")
    private String displayName = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("color")
    private LFColor color = null;

    @JsonProperty("fieldCount")
    private Integer fieldCount = null;

    public TemplateDefinition id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Returns the ID of the template definition.
     * @return id
     */
    @Schema(description = "The ID of the template definition.")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TemplateDefinition name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Returns the name of the template definition.
     * @return name
     */
    @Schema(description = "The name of the template definition.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TemplateDefinition displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Returns the localized name of the template definition.
     * @return displayName
     */
    @Schema(description = "The localized name of the template definition.")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public TemplateDefinition description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Returns the description of the template definition.
     * @return description
     */
    @Schema(description = "The description of the template definition.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TemplateDefinition color(LFColor color) {
        this.color = color;
        return this;
    }

    /**
     * Returns color
     * @return color
     */
    @Schema(description = "")
    public LFColor getColor() {
        return color;
    }

    public void setColor(LFColor color) {
        this.color = color;
    }

    public TemplateDefinition fieldCount(Integer fieldCount) {
        this.fieldCount = fieldCount;
        return this;
    }

    /**
     * Returns the number of field definitions assigned to the template definition.
     * @return fieldCount
     */
    @Schema(description = "The number of field definitions assigned to the template definition.")
    public Integer getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(Integer fieldCount) {
        this.fieldCount = fieldCount;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TemplateDefinition templateDefinition = (TemplateDefinition) o;
        return Objects.equals(this.id, templateDefinition.id) && Objects.equals(this.name, templateDefinition.name) && Objects.equals(this.displayName, templateDefinition.displayName) && Objects.equals(this.description, templateDefinition.description) && Objects.equals(this.color, templateDefinition.color) && Objects.equals(this.fieldCount, templateDefinition.fieldCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, displayName, description, color, fieldCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TemplateDefinition {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    color: ").append(toIndentedString(color)).append("\n");
        sb.append("    fieldCount: ").append(toIndentedString(fieldCount)).append("\n");
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
