// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
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
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OperationErrorItem operationErrorItem = (OperationErrorItem) o;
        return Objects.equals(this.objectId, operationErrorItem.objectId)
                && Objects.equals(this.errorMessage, operationErrorItem.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectId, errorMessage);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OperationErrorItem {\n");
        sb.append("    objectId: ").append(toIndentedString(objectId)).append("\n");
        sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
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
