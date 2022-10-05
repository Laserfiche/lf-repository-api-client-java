package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-10-05T16:07:18.617065500-04:00[America/New_York]")
public class FindEntryResult {
    @JsonProperty("entry")
    private Entry entry = null;

    @JsonProperty("ancestorEntry")
    private Entry ancestorEntry = null;

    public FindEntryResult entry(Entry entry) {
        this.entry = entry;
        return this;
    }

    @Schema(description = "")
    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public FindEntryResult ancestorEntry(Entry ancestorEntry) {
        this.ancestorEntry = ancestorEntry;
        return this;
    }

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
        FindEntryResult findEntryResult = (FindEntryResult) o;
        return Objects.equals(this.entry, findEntryResult.entry) &&
                Objects.equals(this.ancestorEntry, findEntryResult.ancestorEntry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entry, ancestorEntry);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class FindEntryResult {\n");

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
