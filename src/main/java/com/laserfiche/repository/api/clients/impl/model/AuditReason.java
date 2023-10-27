// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * Represents a user-defined audit reason for an audit event.
 */
@Schema(description = "Represents a user-defined audit reason for an audit event.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuditReason {

    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("auditEventType")
    private AuditEventType auditEventType = null;

    public AuditReason id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Returns the audit reason id.
     *
     * @return id
     */
    @Schema(description = "The audit reason id.")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuditReason name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Returns the audit reason text.
     *
     * @return name
     */
    @Schema(description = "The audit reason text.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuditReason auditEventType(AuditEventType auditEventType) {
        this.auditEventType = auditEventType;
        return this;
    }

    /**
     * Returns auditEventType
     *
     * @return auditEventType
     */
    @Schema(description = "")
    public AuditEventType getAuditEventType() {
        return auditEventType;
    }

    public void setAuditEventType(AuditEventType auditEventType) {
        this.auditEventType = auditEventType;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuditReason auditReason = (AuditReason) o;
        return Objects.equals(this.id, auditReason.id) && Objects.equals(this.name, auditReason.name) && Objects.equals(this.auditEventType, auditReason.auditEventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, auditEventType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AuditReason {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    auditEventType: ").append(toIndentedString(auditEventType)).append("\n");
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
