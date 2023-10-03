package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the metadata that will be assigned to the imported entry.
 */
@Schema(description = "Represents the metadata that will be assigned to the imported entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImportEntryRequestMetadata {

    @JsonProperty("templateName")
    private String templateName = null;

    @JsonProperty("fields")
    private List<FieldToUpdate> fields = null;

    @JsonProperty("tags")
    private List<String> tags = null;

    @JsonProperty("links")
    private List<LinkToUpdate> links = null;

    public ImportEntryRequestMetadata templateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    /**
     * Returns the name of the template assigned to the entry.
     * @return templateName
     */
    @Schema(description = "The name of the template assigned to the entry.")
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public ImportEntryRequestMetadata fields(List<FieldToUpdate> fields) {
        this.fields = fields;
        return this;
    }

    public ImportEntryRequestMetadata addFieldsItem(FieldToUpdate fieldsItem) {
        if (this.fields == null) {
            this.fields = new ArrayList<FieldToUpdate>();
        }
        this.fields.add(fieldsItem);
        return this;
    }

    /**
     * Returns the fields that will be assigned to the entry.
     * @return fields
     */
    @Schema(description = "The fields that will be assigned to the entry.")
    public List<FieldToUpdate> getFields() {
        return fields;
    }

    public void setFields(List<FieldToUpdate> fields) {
        this.fields = fields;
    }

    public ImportEntryRequestMetadata tags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public ImportEntryRequestMetadata addTagsItem(String tagsItem) {
        if (this.tags == null) {
            this.tags = new ArrayList<String>();
        }
        this.tags.add(tagsItem);
        return this;
    }

    /**
     * Returns the tags that will be assigned to the entry.
     * @return tags
     */
    @Schema(description = "The tags that will be assigned to the entry.")
    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public ImportEntryRequestMetadata links(List<LinkToUpdate> links) {
        this.links = links;
        return this;
    }

    public ImportEntryRequestMetadata addLinksItem(LinkToUpdate linksItem) {
        if (this.links == null) {
            this.links = new ArrayList<LinkToUpdate>();
        }
        this.links.add(linksItem);
        return this;
    }

    /**
     * Returns the links that will be assigned to the entry.
     * @return links
     */
    @Schema(description = "The links that will be assigned to the entry.")
    public List<LinkToUpdate> getLinks() {
        return links;
    }

    public void setLinks(List<LinkToUpdate> links) {
        this.links = links;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ImportEntryRequestMetadata importEntryRequestMetadata = (ImportEntryRequestMetadata) o;
        return Objects.equals(this.templateName, importEntryRequestMetadata.templateName)
                && Objects.equals(this.fields, importEntryRequestMetadata.fields)
                && Objects.equals(this.tags, importEntryRequestMetadata.tags)
                && Objects.equals(this.links, importEntryRequestMetadata.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(templateName, fields, tags, links);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ImportEntryRequestMetadata {\n");
        sb.append("    templateName: ").append(toIndentedString(templateName)).append("\n");
        sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
        sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
        sb.append("    links: ").append(toIndentedString(links)).append("\n");
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
