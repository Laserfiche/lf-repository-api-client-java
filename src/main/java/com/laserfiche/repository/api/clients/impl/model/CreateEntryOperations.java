package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

/**
 * The results of each operation needed in order to create the electronic document with optional
 * template and fields.
 */
@Schema(
        description =
                "The results of each operation needed in order to create the electronic document with optional template and fields.")
@javax.annotation.Generated(
        value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen",
        date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateEntryOperations {

    @JsonProperty("entryCreate")
    private EntryCreate entryCreate = null;

    @JsonProperty("setEdoc")
    private SetEdoc setEdoc = null;

    @JsonProperty("setTemplate")
    private SetTemplate setTemplate = null;

    @JsonProperty("setFields")
    private SetFields setFields = null;

    @JsonProperty("setTags")
    private SetTags setTags = null;

    @JsonProperty("setLinks")
    private SetLinks setLinks = null;

    public CreateEntryOperations entryCreate(EntryCreate entryCreate) {
        this.entryCreate = entryCreate;
        return this;
    }

    /**
     * Returns entryCreate
     *
     * @return entryCreate
     */
    @Schema(description = "")
    public EntryCreate getEntryCreate() {
        return entryCreate;
    }

    public void setEntryCreate(EntryCreate entryCreate) {
        this.entryCreate = entryCreate;
    }

    public CreateEntryOperations setEdoc(SetEdoc setEdoc) {
        this.setEdoc = setEdoc;
        return this;
    }

    /**
     * Returns setEdoc
     *
     * @return setEdoc
     */
    @Schema(description = "")
    public SetEdoc getSetEdoc() {
        return setEdoc;
    }

    public void setSetEdoc(SetEdoc setEdoc) {
        this.setEdoc = setEdoc;
    }

    public CreateEntryOperations setTemplate(SetTemplate setTemplate) {
        this.setTemplate = setTemplate;
        return this;
    }

    /**
     * Returns setTemplate
     *
     * @return setTemplate
     */
    @Schema(description = "")
    public SetTemplate getSetTemplate() {
        return setTemplate;
    }

    public void setSetTemplate(SetTemplate setTemplate) {
        this.setTemplate = setTemplate;
    }

    public CreateEntryOperations setFields(SetFields setFields) {
        this.setFields = setFields;
        return this;
    }

    /**
     * Returns setFields
     *
     * @return setFields
     */
    @Schema(description = "")
    public SetFields getSetFields() {
        return setFields;
    }

    public void setSetFields(SetFields setFields) {
        this.setFields = setFields;
    }

    public CreateEntryOperations setTags(SetTags setTags) {
        this.setTags = setTags;
        return this;
    }

    /**
     * Returns setTags
     *
     * @return setTags
     */
    @Schema(description = "")
    public SetTags getSetTags() {
        return setTags;
    }

    public void setSetTags(SetTags setTags) {
        this.setTags = setTags;
    }

    public CreateEntryOperations setLinks(SetLinks setLinks) {
        this.setLinks = setLinks;
        return this;
    }

    /**
     * Returns setLinks
     *
     * @return setLinks
     */
    @Schema(description = "")
    public SetLinks getSetLinks() {
        return setLinks;
    }

    public void setSetLinks(SetLinks setLinks) {
        this.setLinks = setLinks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateEntryOperations createEntryOperations = (CreateEntryOperations) o;
        return Objects.equals(this.entryCreate, createEntryOperations.entryCreate)
                && Objects.equals(this.setEdoc, createEntryOperations.setEdoc)
                && Objects.equals(this.setTemplate, createEntryOperations.setTemplate)
                && Objects.equals(this.setFields, createEntryOperations.setFields)
                && Objects.equals(this.setTags, createEntryOperations.setTags)
                && Objects.equals(this.setLinks, createEntryOperations.setLinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryCreate, setEdoc, setTemplate, setFields, setTags, setLinks);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateEntryOperations {\n");
        sb.append("    entryCreate: ").append(toIndentedString(entryCreate)).append("\n");
        sb.append("    setEdoc: ").append(toIndentedString(setEdoc)).append("\n");
        sb.append("    setTemplate: ").append(toIndentedString(setTemplate)).append("\n");
        sb.append("    setFields: ").append(toIndentedString(setFields)).append("\n");
        sb.append("    setTags: ").append(toIndentedString(setTags)).append("\n");
        sb.append("    setLinks: ").append(toIndentedString(setLinks)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
