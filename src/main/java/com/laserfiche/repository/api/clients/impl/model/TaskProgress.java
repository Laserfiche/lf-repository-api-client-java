package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.laserfiche.api.client.model.ProblemDetails;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.OffsetDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the progress of a long operation task.
 */
@Schema(description = "Represents the progress of a long operation task.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskProgress {

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("taskType")
    private TaskType taskType = null;

    @JsonProperty("percentComplete")
    private Integer percentComplete = null;

    @JsonProperty("status")
    private TaskStatus status = null;

    @JsonProperty("errors")
    private List<ProblemDetails> errors = null;

    @JsonProperty("result")
    private TaskResult result = null;

    @JsonProperty("startTime")
    private OffsetDateTime startTime = null;

    @JsonProperty("lastUpdateTime")
    private OffsetDateTime lastUpdateTime = null;

    public TaskProgress id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Returns the task ID of the task associated with this TaskProgress.
     *
     * @return id
     */
    @Schema(description = "The task ID of the task associated with this TaskProgress.")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TaskProgress taskType(TaskType taskType) {
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

    public TaskProgress percentComplete(Integer percentComplete) {
        this.percentComplete = percentComplete;
        return this;
    }

    /**
     * Returns determines what percentage of the execution of the associated task is completed.
     *
     * @return percentComplete
     */
    @Schema(description = "Determines what percentage of the execution of the associated task is completed.")
    public Integer getPercentComplete() {
        return percentComplete;
    }

    public void setPercentComplete(Integer percentComplete) {
        this.percentComplete = percentComplete;
    }

    public TaskProgress status(TaskStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Returns status
     *
     * @return status
     */
    @Schema(description = "")
    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskProgress errors(List<ProblemDetails> errors) {
        this.errors = errors;
        return this;
    }

    public TaskProgress addErrorsItem(ProblemDetails errorsItem) {
        if (this.errors == null) {
            this.errors = new ArrayList<ProblemDetails>();
        }
        this.errors.add(errorsItem);
        return this;
    }

    /**
     * Returns the list of errors occurred during the execution of the associated task.
     *
     * @return errors
     */
    @Schema(description = "The list of errors occurred during the execution of the associated task.")
    public List<ProblemDetails> getErrors() {
        return errors;
    }

    public void setErrors(List<ProblemDetails> errors) {
        this.errors = errors;
    }

    public TaskProgress result(TaskResult result) {
        this.result = result;
        return this;
    }

    /**
     * Returns result
     *
     * @return result
     */
    @Schema(description = "")
    public TaskResult getResult() {
        return result;
    }

    public void setResult(TaskResult result) {
        this.result = result;
    }

    public TaskProgress startTime(OffsetDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Returns the time representing when the associated task&#x27;s execution started.
     *
     * @return startTime
     */
    @Schema(description = "The time representing when the associated task's execution started.")
    public OffsetDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(OffsetDateTime startTime) {
        this.startTime = startTime;
    }

    public TaskProgress lastUpdateTime(OffsetDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return this;
    }

    /**
     * Returns the time representing when the associated task&#x27;s status last changed.
     *
     * @return lastUpdateTime
     */
    @Schema(description = "The time representing when the associated task's status last changed.")
    public OffsetDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(OffsetDateTime lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskProgress taskProgress = (TaskProgress) o;
        return Objects.equals(this.id, taskProgress.id) && Objects.equals(this.taskType, taskProgress.taskType) && Objects.equals(this.percentComplete, taskProgress.percentComplete) && Objects.equals(this.status, taskProgress.status) && Objects.equals(this.errors, taskProgress.errors) && Objects.equals(this.result, taskProgress.result) && Objects.equals(this.startTime, taskProgress.startTime) && Objects.equals(this.lastUpdateTime, taskProgress.lastUpdateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskType, percentComplete, status, errors, result, startTime, lastUpdateTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TaskProgress {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    taskType: ").append(toIndentedString(taskType)).append("\n");
        sb.append("    percentComplete: ").append(toIndentedString(percentComplete)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
        sb.append("    result: ").append(toIndentedString(result)).append("\n");
        sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
        sb.append("    lastUpdateTime: ").append(toIndentedString(lastUpdateTime)).append("\n");
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
