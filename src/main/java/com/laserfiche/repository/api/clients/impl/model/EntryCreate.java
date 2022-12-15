package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import com.laserfiche.api.client.model.APIServerException;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;

/**
 * The result of trying to create the entry.
 */
@Schema(description = "The result of trying to create the entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntryCreate {

    @JsonProperty("exceptions")
    private List<APIServerException> exceptions = null;

    @JsonProperty("entryId")
    private Integer entryId = null;

    public EntryCreate exceptions(List<APIServerException> exceptions) {
        this.exceptions = exceptions;
        return this;
    }

    public EntryCreate addExceptionsItem(APIServerException exceptionsItem) {
        if (this.exceptions == null) {
            this.exceptions = new ArrayList<APIServerException>();
        }
        this.exceptions.add(exceptionsItem);
        return this;
    }

    /**
 * Returns the list of exceptions that occured when trying to perform the operation.
 * @return exceptions
*/
    @Schema(description = "The list of exceptions that occured when trying to perform the operation.")
    public List<APIServerException> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<APIServerException> exceptions) {
        this.exceptions = exceptions;
    }

    public EntryCreate entryId(Integer entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
 * Returns the id of the created entry. If the id is 0, then the entry was not created.
 * @return entryId
*/
    @Schema(description = "The id of the created entry. If the id is 0, then the entry was not created.")
    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntryCreate entryCreate = (EntryCreate) o;
        return Objects.equals(this.exceptions, entryCreate.exceptions) && Objects.equals(this.entryId, entryCreate.entryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exceptions, entryId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class EntryCreate {\n");
        sb.append("    exceptions: ").append(toIndentedString(exceptions)).append("\n");
        sb.append("    entryId: ").append(toIndentedString(entryId)).append("\n");
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
