package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(description = "The result of trying to assign fields to the entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-10-17T11:38:41.655-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SetTags {

    @JsonProperty("exceptions")
    private List<APIServerException> exceptions = null;

    @JsonProperty("assignedTags")
    private List<String> assignedTags = null;

    public SetTags exceptions(List<APIServerException> exceptions) {
        this.exceptions = exceptions;
        return this;
    }

    public SetTags addExceptionsItem(APIServerException exceptionsItem) {
        if (this.exceptions == null) {
            this.exceptions = new ArrayList<APIServerException>();
        }
        this.exceptions.add(exceptionsItem);
        return this;
    }

    @Schema(description = "The list of exceptions that occured when trying to perform the operation.")
    public List<APIServerException> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<APIServerException> exceptions) {
        this.exceptions = exceptions;
    }

    public SetTags assignedTags(List<String> assignedTags) {
        this.assignedTags = assignedTags;
        return this;
    }

    public SetTags addAssignedTagsItem(String assignedTagsItem) {
        if (this.assignedTags == null) {
            this.assignedTags = new ArrayList<String>();
        }
        this.assignedTags.add(assignedTagsItem);
        return this;
    }

    @Schema(description = "The tags that were assigned to the entry")
    public List<String> getAssignedTags() {
        return assignedTags;
    }

    public void setAssignedTags(List<String> assignedTags) {
        this.assignedTags = assignedTags;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SetTags setTags = (SetTags) o;
        return Objects.equals(this.exceptions, setTags.exceptions) && Objects.equals(this.assignedTags,
                setTags.assignedTags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exceptions, assignedTags);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SetTags {\n");
        sb
                .append("    exceptions: ")
                .append(toIndentedString(exceptions))
                .append("\n");
        sb
                .append("    assignedTags: ")
                .append(toIndentedString(assignedTags))
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
