package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-10-17T11:38:41.655-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Folder extends Entry {

    @JsonProperty("isRecordFolder")
    private Boolean isRecordFolder = null;

    @JsonProperty("isUnderRecordSeries")
    private Boolean isUnderRecordSeries = null;

    @JsonProperty("children")
    private List<Entry> children = null;

    public Folder isRecordFolder(Boolean isRecordFolder) {
        this.isRecordFolder = isRecordFolder;
        return this;
    }

    @Schema(description = "A boolean indicating if the folder that this instance represents is known to be a record folder.")
    public Boolean isIsRecordFolder() {
        return isRecordFolder;
    }

    public void setIsRecordFolder(Boolean isRecordFolder) {
        this.isRecordFolder = isRecordFolder;
    }

    public Folder isUnderRecordSeries(Boolean isUnderRecordSeries) {
        this.isUnderRecordSeries = isUnderRecordSeries;
        return this;
    }

    @Schema(description = "A boolean indicating if the folder that this instance represents is known to directly or indirectly under a record series in the repository.")
    public Boolean isIsUnderRecordSeries() {
        return isUnderRecordSeries;
    }

    public void setIsUnderRecordSeries(Boolean isUnderRecordSeries) {
        this.isUnderRecordSeries = isUnderRecordSeries;
    }

    public Folder children(List<Entry> children) {
        this.children = children;
        return this;
    }

    public Folder addChildrenItem(Entry childrenItem) {
        if (this.children == null) {
            this.children = new ArrayList<Entry>();
        }
        this.children.add(childrenItem);
        return this;
    }

    @Schema(description = "The entries in this folder.")
    public List<Entry> getChildren() {
        return children;
    }

    public void setChildren(List<Entry> children) {
        this.children = children;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Folder folder = (Folder) o;
        return Objects.equals(this.isRecordFolder, folder.isRecordFolder) && Objects.equals(this.isUnderRecordSeries,
                folder.isUnderRecordSeries) && Objects.equals(this.children, folder.children) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isRecordFolder, isUnderRecordSeries, children, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Folder {\n");
        sb
                .append("    ")
                .append(toIndentedString(super.toString()))
                .append("\n");
        sb
                .append("    isRecordFolder: ")
                .append(toIndentedString(isRecordFolder))
                .append("\n");
        sb
                .append("    isUnderRecordSeries: ")
                .append(toIndentedString(isUnderRecordSeries))
                .append("\n");
        sb
                .append("    children: ")
                .append(toIndentedString(children))
                .append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o
                .toString()
                .replace("\n", "\n    ");
    }
}
