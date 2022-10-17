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
public class SetFields {

    @JsonProperty("exceptions")
    private List<APIServerException> exceptions = null;

    @JsonProperty("fieldCount")
    private Integer fieldCount = null;

    public SetFields exceptions(List<APIServerException> exceptions) {
        this.exceptions = exceptions;
        return this;
    }

    public SetFields addExceptionsItem(APIServerException exceptionsItem) {
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

    public SetFields fieldCount(Integer fieldCount) {
        this.fieldCount = fieldCount;
        return this;
    }

    @Schema(description = "The number of fields assigned to the entry.")
    public Integer getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(Integer fieldCount) {
        this.fieldCount = fieldCount;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SetFields setFields = (SetFields) o;
        return Objects.equals(this.exceptions, setFields.exceptions) && Objects.equals(this.fieldCount,
                setFields.fieldCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exceptions, fieldCount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SetFields {\n");
        sb
                .append("    exceptions: ")
                .append(toIndentedString(exceptions))
                .append("\n");
        sb
                .append("    fieldCount: ")
                .append(toIndentedString(fieldCount))
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
