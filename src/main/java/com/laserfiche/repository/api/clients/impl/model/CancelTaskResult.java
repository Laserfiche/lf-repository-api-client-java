package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * Represents the result of cancelling a long operation task.
 */
@Schema(description = "Represents the result of cancelling a long operation task.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CancelTaskResult {

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("taskType")
    private TaskType taskType = null;

    @JsonProperty("result")
    private Boolean result = null;

    public CancelTaskResult id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Returns the ID of the task which has been subject to cancellation.
     *
     * @return id
     */
    @Schema(description = "The ID of the task which has been subject to cancellation.")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CancelTaskResult taskType(TaskType taskType) {
        this.taskType = taskType;
        return this;
    }

    /**
     * Returns taskType
     *
     * @return taskType
     */
    @Schema(description = "")
    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public CancelTaskResult result(Boolean result) {
        this.result = result;
        return this;
    }

    /**
     * Returns indicates if the request to cancel the task has been received.
     *
     * @return result
     */
    @Schema(description = "Indicates if the request to cancel the task has been received.")
    public Boolean isResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CancelTaskResult cancelTaskResult = (CancelTaskResult) o;
        return Objects.equals(this.id, cancelTaskResult.id) && Objects.equals(this.taskType, cancelTaskResult.taskType) && Objects.equals(this.result, cancelTaskResult.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskType, result);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CancelTaskResult {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    taskType: ").append(toIndentedString(taskType)).append("\n");
        sb.append("    result: ").append(toIndentedString(result)).append("\n");
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
