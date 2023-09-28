package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;

/**
 * Response containing a collection of CancelTaskResult.
 */
@Schema(description = "Response containing a collection of CancelTaskResult.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CancelTasksResponse {

    @JsonProperty("value")
    private List<CancelTaskResult> value = null;

    public CancelTasksResponse value(List<CancelTaskResult> value) {
        this.value = value;
        return this;
    }

    public CancelTasksResponse addValueItem(CancelTaskResult valueItem) {
        if (this.value == null) {
            this.value = new ArrayList<CancelTaskResult>();
        }
        this.value.add(valueItem);
        return this;
    }

    /**
     * Returns value
     * @return value
     */
    @Schema(description = "")
    public List<CancelTaskResult> getValue() {
        return value;
    }

    public void setValue(List<CancelTaskResult> value) {
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
        CancelTasksResponse cancelTasksResponse = (CancelTasksResponse) o;
        return Objects.equals(this.value, cancelTasksResponse.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CancelTasksResponse {\n");
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