package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostEntryChildrenRequest {

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("entryType")
    private PostEntryChildrenEntryType entryType = null;

    @JsonProperty("targetId")
    private Integer targetId = null;

    @JsonProperty("sourceId")
    private Integer sourceId = null;

    @JsonProperty("volumeName")
    private String volumeName = null;

    public PostEntryChildrenRequest name(String name) {
        this.name = name;
        return this;
    }

    /**
 * Returns the name of the entry.
 * @return name
*/
    @Schema(description = "The name of the entry.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PostEntryChildrenRequest entryType(PostEntryChildrenEntryType entryType) {
        this.entryType = entryType;
        return this;
    }

    /**
 * Returns entryType
 * @return entryType
 
*/
    @Schema(description = "")
    public PostEntryChildrenEntryType getEntryType() {
        return entryType;
    }

    public void setEntryType(PostEntryChildrenEntryType entryType) {
        this.entryType = entryType;
    }

    public PostEntryChildrenRequest targetId(Integer targetId) {
        this.targetId = targetId;
        return this;
    }

    /**
 * Returns the TargetId is only needed for creating a shortcut. This will be the entry ID of the shortcut target.
 * @return targetId
*/
    @Schema(description = "The TargetId is only needed for creating a shortcut. This will be the entry ID of the shortcut target.")
    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public PostEntryChildrenRequest sourceId(Integer sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    /**
 * Returns the SourceId is needed for some operations that require a source/destination. One example is the Copy operation.
 * @return sourceId
*/
    @Schema(description = "The SourceId is needed for some operations that require a source/destination. One example is the Copy operation.")
    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public PostEntryChildrenRequest volumeName(String volumeName) {
        this.volumeName = volumeName;
        return this;
    }

    /**
 * Returns the name of the volume to use. Will use the default parent entry volume if not specified. This is ignored in Laserfiche Cloud.
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PostEntryChildrenRequest postEntryChildrenRequest = (PostEntryChildrenRequest) o;
        return Objects.equals(this.name, postEntryChildrenRequest.name) && Objects.equals(this.entryType, postEntryChildrenRequest.entryType) && Objects.equals(this.targetId, postEntryChildrenRequest.targetId) && Objects.equals(this.sourceId, postEntryChildrenRequest.sourceId) && Objects.equals(this.volumeName, postEntryChildrenRequest.volumeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, entryType, targetId, sourceId, volumeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PostEntryChildrenRequest {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    entryType: ").append(toIndentedString(entryType)).append("\n");
        sb.append("    targetId: ").append(toIndentedString(targetId)).append("\n");
        sb.append("    sourceId: ").append(toIndentedString(sourceId)).append("\n");
        sb.append("    volumeName: ").append(toIndentedString(volumeName)).append("\n");
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
