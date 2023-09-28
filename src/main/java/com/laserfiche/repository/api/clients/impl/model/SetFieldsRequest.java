package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;

/**
 * Request body for assigning fields to an entry.
 */
@Schema(description = "Request body for assigning fields to an entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SetFieldsRequest {

    @JsonProperty("fields")
    private List<FieldToUpdate> fields = null;

    public SetFieldsRequest fields(List<FieldToUpdate> fields) {
        this.fields = fields;
        return this;
    }

    public SetFieldsRequest addFieldsItem(FieldToUpdate fieldsItem) {
        if (this.fields == null) {
            this.fields = new ArrayList<FieldToUpdate>();
        }
        this.fields.add(fieldsItem);
        return this;
    }

    /**
     * Returns the fields that will be assigned to the entry.
     * @return fields
     */
    @Schema(description = "The fields that will be assigned to the entry.")
    public List<FieldToUpdate> getFields() {
        return fields;
    }

    public void setFields(List<FieldToUpdate> fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SetFieldsRequest setFieldsRequest = (SetFieldsRequest) o;
        return Objects.equals(this.fields, setFieldsRequest.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fields);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SetFieldsRequest {\n");
        sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
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
