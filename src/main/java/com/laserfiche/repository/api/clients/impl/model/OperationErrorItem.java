package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperationErrorItem {

    @JsonProperty("objectId")
    private Integer objectId = null;

    @JsonProperty("errorMessage")
    private String errorMessage = null;

    public OperationErrorItem objectId(Integer objectId) {
        this.objectId = objectId;
        return this;
    }

    /**
     * Returns the ID of the entry to which the error is related.
     *
     * @return objectId
     */
    @Schema(description = "The ID of the entry to which the error is related. ")
    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public OperationErrorItem errorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    /**
     * Returns the short description of the error.
     *
     * @return errorMessage
     */
    @Schema(description = "The short description of the error.")
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OperationErrorItem operationErrorItem = (OperationErrorItem) o;
        return Objects.equals(this.objectId, operationErrorItem.objectId) && Objects.equals(this.errorMessage,
                operationErrorItem.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectId, errorMessage);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OperationErrorItem {\n");
        sb
                .append("    objectId: ")
                .append(toIndentedString(objectId))
                .append("\n");
        sb
                .append("    errorMessage: ")
                .append(toIndentedString(errorMessage))
                .append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o
                .toString()
                .replace("\n", "\n    ");
    }
}
