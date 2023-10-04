package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * Request body for copying an entry.
 */
@Schema(description = "Request body for copying an entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CopyEntryRequest {

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("autoRename")
    private Boolean autoRename = false;

    @JsonProperty("sourceId")
    private Integer sourceId = null;

    @JsonProperty("volumeName")
    private String volumeName = null;

    public CopyEntryRequest name(String name) {
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

    public CopyEntryRequest autoRename(Boolean autoRename) {
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

    public CopyEntryRequest sourceId(Integer sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    /**
     * Returns the source entry Id to copy.
     *
     * @return sourceId
     */
    @Schema(required = true, description = "The source entry Id to copy.")
    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public CopyEntryRequest volumeName(String volumeName) {
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
        CopyEntryRequest copyEntryRequest = (CopyEntryRequest) o;
        return Objects.equals(this.name, copyEntryRequest.name) && Objects.equals(this.autoRename, copyEntryRequest.autoRename) && Objects.equals(this.sourceId, copyEntryRequest.sourceId) && Objects.equals(this.volumeName, copyEntryRequest.volumeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, autoRename, sourceId, volumeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CopyEntryRequest {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    autoRename: ").append(toIndentedString(autoRename)).append("\n");
        sb.append("    sourceId: ").append(toIndentedString(sourceId)).append("\n");
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
