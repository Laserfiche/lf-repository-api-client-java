package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.*;

/**
 * Response containing an entry or ancestor entry found by entry path.
 */
@Schema(description = "Response containing an entry or ancestor entry found by entry path.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetEntryByPathResponse {

    @JsonProperty("entry")
    private Entry entry = null;

    @JsonProperty("ancestorEntry")
    private Entry ancestorEntry = null;

    public GetEntryByPathResponse entry(Entry entry) {
        this.entry = entry;
        return this;
    }

    /**
     * Returns entry
     * @return entry
     */
    @Schema(description = "")
    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public GetEntryByPathResponse ancestorEntry(Entry ancestorEntry) {
        this.ancestorEntry = ancestorEntry;
        return this;
    }

    /**
     * Returns ancestorEntry
     * @return ancestorEntry
     */
    @Schema(description = "")
    public Entry getAncestorEntry() {
        return ancestorEntry;
    }

    public void setAncestorEntry(Entry ancestorEntry) {
        this.ancestorEntry = ancestorEntry;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetEntryByPathResponse getEntryByPathResponse = (GetEntryByPathResponse) o;
        return Objects.equals(this.entry, getEntryByPathResponse.entry) && Objects.equals(this.ancestorEntry, getEntryByPathResponse.ancestorEntry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entry, ancestorEntry);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetEntryByPathResponse {\n");
        sb.append("    entry: ").append(toIndentedString(entry)).append("\n");
        sb.append("    ancestorEntry: ").append(toIndentedString(ancestorEntry)).append("\n");
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
