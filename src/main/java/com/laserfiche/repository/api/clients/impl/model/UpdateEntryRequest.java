package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * Request body for updating an entry.
 */
@Schema(description = "Request body for updating an entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateEntryRequest {

    @JsonProperty("parentId")
    private Integer parentId = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("autoRename")
    private Boolean autoRename = false;

    public UpdateEntryRequest parentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    /**
     * Returns the ID of the parent entry that the entry will be moved to.
     *
     * @return parentId
     */
    @Schema(description = "The ID of the parent entry that the entry will be moved to.")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public UpdateEntryRequest name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Returns the name that will be assigned to the entry.
     *
     * @return name
     */
    @Schema(description = "The name that will be assigned to the entry.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UpdateEntryRequest autoRename(Boolean autoRename) {
        this.autoRename = autoRename;
        return this;
    }

    /**
     * Returns indicates if the entry should be automatically renamed if an entry already exists with the given name in the folder. The default value is false.
     *
     * @return autoRename
     */
    @Schema(description = "Indicates if the entry should be automatically renamed if an entry already exists with the given name in the folder. The default value is false.")
    public Boolean isAutoRename() {
        return autoRename;
    }

    public void setAutoRename(Boolean autoRename) {
        this.autoRename = autoRename;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdateEntryRequest updateEntryRequest = (UpdateEntryRequest) o;
        return Objects.equals(this.parentId, updateEntryRequest.parentId) && Objects.equals(this.name, updateEntryRequest.name) && Objects.equals(this.autoRename, updateEntryRequest.autoRename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentId, name, autoRename);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UpdateEntryRequest {\n");
        sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    autoRename: ").append(toIndentedString(autoRename)).append("\n");
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
