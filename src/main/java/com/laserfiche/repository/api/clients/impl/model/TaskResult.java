package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.*;

/**
 * Represents the result of a long operation task.
 */
@Schema(description = "Represents the result of a long operation task.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResult {

    @JsonProperty("entryId")
    private Integer entryId = null;

    @JsonProperty("uri")
    private String uri = null;

    public TaskResult entryId(Integer entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * Returns the ID of the entry which is affected (e.g. created or modified) by the execution of the associated task.
     * @return entryId
     */
    @Schema(description = "The ID of the entry which is affected (e.g. created or modified) by the execution of the associated task.")
    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public TaskResult uri(String uri) {
        this.uri = uri;
        return this;
    }

    /**
     * Returns the URI which can be used (via api call) to access the result(s) of the associated task.
     * @return uri
     */
    @Schema(description = "The URI which can be used (via api call) to access the result(s) of the associated task.")
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskResult taskResult = (TaskResult) o;
        return Objects.equals(this.entryId, taskResult.entryId) && Objects.equals(this.uri, taskResult.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryId, uri);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TaskResult {\n");
        sb.append("    entryId: ").append(toIndentedString(entryId)).append("\n");
        sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
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
