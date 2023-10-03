package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * Request body for creating an entry.
 */
@Schema(description = "Request body for creating an entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateEntryRequest {

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("autoRename")
    private Boolean autoRename = false;

    @JsonProperty("entryType")
    private CreateEntryRequestEntryType entryType = null;

    @JsonProperty("targetId")
    private Integer targetId = null;

    @JsonProperty("volumeName")
    private String volumeName = null;

    public CreateEntryRequest name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Returns the name of the entry.
     *
     * @return name
     */
    @Schema(required = true, description = "The name of the entry.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreateEntryRequest autoRename(Boolean autoRename) {
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

    public CreateEntryRequest entryType(CreateEntryRequestEntryType entryType) {
        this.entryType = entryType;
        return this;
    }

    /**
     * Returns entryType
     *
     * @return entryType
     */
    @Schema(required = true, description = "")
    public CreateEntryRequestEntryType getEntryType() {
        return entryType;
    }

    public void setEntryType(CreateEntryRequestEntryType entryType) {
        this.entryType = entryType;
    }

    public CreateEntryRequest targetId(Integer targetId) {
        this.targetId = targetId;
        return this;
    }

    /**
     * Returns the TargetId is only needed for creating a shortcut. This will be the entry ID of the shortcut target.
     *
     * @return targetId
     */
    @Schema(description = "The TargetId is only needed for creating a shortcut. This will be the entry ID of the shortcut target.")
    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public CreateEntryRequest volumeName(String volumeName) {
        this.volumeName = volumeName;
        return this;
    }

    /**
     * Returns the name of the volume to use. Will use the default parent entry volume if not specified. This is ignored in Laserfiche Cloud.
     *
     * @return volumeName
     */
    @Schema(description = "The name of the volume to use. Will use the default parent entry volume if not specified. This is ignored in Laserfiche Cloud.")
    public String getVolumeName() {
        return volumeName;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateEntryRequest createEntryRequest = (CreateEntryRequest) o;
        return Objects.equals(this.name, createEntryRequest.name) && Objects.equals(this.autoRename, createEntryRequest.autoRename) && Objects.equals(this.entryType, createEntryRequest.entryType) && Objects.equals(this.targetId, createEntryRequest.targetId) && Objects.equals(this.volumeName, createEntryRequest.volumeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, autoRename, entryType, targetId, volumeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateEntryRequest {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    autoRename: ").append(toIndentedString(autoRename)).append("\n");
        sb.append("    entryType: ").append(toIndentedString(entryType)).append("\n");
        sb.append("    targetId: ").append(toIndentedString(targetId)).append("\n");
        sb.append("    volumeName: ").append(toIndentedString(volumeName)).append("\n");
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
