// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ODataValueOfIListOfEntryLinkTypeInfo {

    @JsonProperty("value")
    private List<EntryLinkTypeInfo> value = null;

    public ODataValueOfIListOfEntryLinkTypeInfo value(List<EntryLinkTypeInfo> value) {
        this.value = value;
        return this;
    }

    public ODataValueOfIListOfEntryLinkTypeInfo addValueItem(EntryLinkTypeInfo valueItem) {
        if (this.value == null) {
            this.value = new ArrayList<EntryLinkTypeInfo>();
        }
        this.value.add(valueItem);
        return this;
    }

    /**
     * Returns value
     * @return value
     */
    @Schema(description = "")
    public List<EntryLinkTypeInfo> getValue() {
        return value;
    }

    public void setValue(List<EntryLinkTypeInfo> value) {
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
        ODataValueOfIListOfEntryLinkTypeInfo odataValueOfIListOfEntryLinkTypeInfo =
                (ODataValueOfIListOfEntryLinkTypeInfo) o;
        return Objects.equals(this.value, odataValueOfIListOfEntryLinkTypeInfo.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ODataValueOfIListOfEntryLinkTypeInfo {\n");
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
