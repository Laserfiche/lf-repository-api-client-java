package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-10-17T11:38:41.655-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatchEntryRequest {

    @JsonProperty("parentId")
    private Integer parentId = null;

    @JsonProperty("name")
    private String name = null;

    public PatchEntryRequest parentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    @Schema(description = "The ID of the parent entry that the entry will be moved to.")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public PatchEntryRequest name(String name) {
        this.name = name;
        return this;
    }

    @Schema(description = "The name that will be assigned to the entry.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PatchEntryRequest patchEntryRequest = (PatchEntryRequest) o;
        return Objects.equals(this.parentId, patchEntryRequest.parentId) && Objects.equals(this.name,
                patchEntryRequest.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId, name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PatchEntryRequest {\n");
        sb
                .append("    parentId: ")
                .append(toIndentedString(parentId))
                .append("\n");
        sb
                .append("    name: ")
                .append(toIndentedString(name))
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
