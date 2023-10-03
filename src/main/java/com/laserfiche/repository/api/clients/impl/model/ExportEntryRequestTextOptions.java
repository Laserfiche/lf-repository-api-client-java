package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * Represents the options when exporting the text part of an entry.
 */
@Schema(description = "Represents the options when exporting the text part of an entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExportEntryRequestTextOptions {

    @JsonProperty("includeRedactions")
    private Boolean includeRedactions = true;

    @JsonProperty("redactionCharacter")
    private String redactionCharacter = "X";

    public ExportEntryRequestTextOptions includeRedactions(Boolean includeRedactions) {
        this.includeRedactions = includeRedactions;
        return this;
    }

    /**
     * Returns indicates if redactions are included. The default value is true.
     * @return includeRedactions
     */
    @Schema(description = "Indicates if redactions are included. The default value is true.")
    public Boolean isIncludeRedactions() {
        return includeRedactions;
    }

    public void setIncludeRedactions(Boolean includeRedactions) {
        this.includeRedactions = includeRedactions;
    }

    public ExportEntryRequestTextOptions redactionCharacter(String redactionCharacter) {
        this.redactionCharacter = redactionCharacter;
        return this;
    }

    /**
     * Returns the character that replaces the original character in a redacted text. The value must be a string of length 1 and must not be a whitespace character. The default value is &#x27;X&#x27;.
     * @return redactionCharacter
     */
    @Schema(description = "The character that replaces the original character in a redacted text. The value must be a string of length 1 and must not be a whitespace character. The default value is 'X'.")
    public String getRedactionCharacter() {
        return redactionCharacter;
    }

    public void setRedactionCharacter(String redactionCharacter) {
        this.redactionCharacter = redactionCharacter;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExportEntryRequestTextOptions exportEntryRequestTextOptions = (ExportEntryRequestTextOptions) o;
        return Objects.equals(this.includeRedactions, exportEntryRequestTextOptions.includeRedactions) && Objects.equals(this.redactionCharacter, exportEntryRequestTextOptions.redactionCharacter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(includeRedactions, redactionCharacter);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ExportEntryRequestTextOptions {\n");
        sb.append("    includeRedactions: ").append(toIndentedString(includeRedactions)).append("\n");
        sb.append("    redactionCharacter: ").append(toIndentedString(redactionCharacter)).append("\n");
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
