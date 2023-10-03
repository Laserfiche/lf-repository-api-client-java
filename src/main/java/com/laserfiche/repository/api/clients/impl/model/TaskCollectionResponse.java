package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Response containing a collection of TaskProgress.
 */
@Schema(description = "Response containing a collection of TaskProgress.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskCollectionResponse {

    @JsonProperty("value")
    private List<TaskProgress> value = null;

    public TaskCollectionResponse value(List<TaskProgress> value) {
        this.value = value;
        return this;
    }

    public TaskCollectionResponse addValueItem(TaskProgress valueItem) {
        if (this.value == null) {
            this.value = new ArrayList<TaskProgress>();
        }
        this.value.add(valueItem);
        return this;
    }

    /**
     * Returns value
     *
     * @return value
     */
    @Schema(description = "")
    public List<TaskProgress> getValue() {
        return value;
    }

    public void setValue(List<TaskProgress> value) {
        this.value = value;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskCollectionResponse taskCollectionResponse = (TaskCollectionResponse) o;
        return Objects.equals(this.value, taskCollectionResponse.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TaskCollectionResponse {\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
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
