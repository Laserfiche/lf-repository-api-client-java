package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(description = "The result of trying to assign a entry link to the entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SetLinks {

    @JsonProperty("exceptions")
    private List<APIServerException> exceptions = null;

    @JsonProperty("otherEntryIds")
    private List<Integer> otherEntryIds = null;

    public SetLinks exceptions(List<APIServerException> exceptions) {
        this.exceptions = exceptions;
        return this;
    }

    public SetLinks addExceptionsItem(APIServerException exceptionsItem) {
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

    public SetLinks otherEntryIds(List<Integer> otherEntryIds) {
        this.otherEntryIds = otherEntryIds;
        return this;
    }

    public SetLinks addOtherEntryIdsItem(Integer otherEntryIdsItem) {
        if (this.otherEntryIds == null) {
            this.otherEntryIds = new ArrayList<Integer>();
        }
        this.otherEntryIds.add(otherEntryIdsItem);
        return this;
    }

    @Schema(description = "The ids of the other entries linked to the entry")
public List<Integer> getOtherEntryIds() {
    return otherEntryIds;
}

    public void setOtherEntryIds(List<Integer> otherEntryIds) {
        this.otherEntryIds = otherEntryIds;
    }

    @Override
public boolean equals(java.lang.Object o) {
    if (this == o) {
        return true;
    }
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    SetLinks setLinks = (SetLinks) o;
    return Objects.equals(this.exceptions, setLinks.exceptions) && Objects.equals(this.otherEntryIds, setLinks.otherEntryIds);
}

    @Override
public int hashCode() {
    return Objects.hash(exceptions, otherEntryIds);
}

    @Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SetLinks {\n");
    sb.append("    exceptions: ").append(toIndentedString(exceptions)).append("\n");
    sb.append("    otherEntryIds: ").append(toIndentedString(otherEntryIds)).append("\n");
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
