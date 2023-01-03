package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateEntryResult {

    @JsonProperty("operations")
    private CreateEntryOperations operations = null;

    @JsonProperty("documentLink")
    private String documentLink = null;

    public CreateEntryResult operations(CreateEntryOperations operations) {
        this.operations = operations;
        return this;
    }

    /**
     * Returns operations
     *
     * @return operations
     */
    @Schema(description = "")
    public CreateEntryOperations getOperations() {
        return operations;
    }

    public void setOperations(CreateEntryOperations operations) {
        this.operations = operations;
    }

    public CreateEntryResult documentLink(String documentLink) {
        this.documentLink = documentLink;
        return this;
    }

    /**
     * Returns a link to get the created entry.
     *
     * @return documentLink
     */
    @Schema(description = "A link to get the created entry.")
    public String getDocumentLink() {
        return documentLink;
    }

    public void setDocumentLink(String documentLink) {
        this.documentLink = documentLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateEntryResult createEntryResult = (CreateEntryResult) o;
        return Objects.equals(this.operations, createEntryResult.operations) && Objects.equals(this.documentLink,
                createEntryResult.documentLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operations, documentLink);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateEntryResult {\n");
        sb
                .append("    operations: ")
                .append(toIndentedString(operations))
                .append("\n");
        sb
                .append("    documentLink: ")
                .append(toIndentedString(documentLink))
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
