package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemplateFieldDefinition extends FieldDefinition {

    @JsonProperty("rule")
    private Rule rule = null;

    @JsonProperty("groupId")
    private Integer groupId = null;

    @JsonProperty("groupName")
    private String groupName = null;

    public TemplateFieldDefinition rule(Rule rule) {
        this.rule = rule;
        return this;
    }

    /**
     * Returns rule
     * @return rule
     */
    @Schema(description = "")
    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public TemplateFieldDefinition groupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * Returns the group id of the field in the template.
     * @return groupId
     */
    @Schema(description = "The group id of the field in the template.")
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public TemplateFieldDefinition groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    /**
     * Returns the name of field group.
     * @return groupName
     */
    @Schema(description = "The name of field group.")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TemplateFieldDefinition templateFieldDefinition = (TemplateFieldDefinition) o;
        return Objects.equals(this.rule, templateFieldDefinition.rule)
                && Objects.equals(this.groupId, templateFieldDefinition.groupId)
                && Objects.equals(this.groupName, templateFieldDefinition.groupName)
                && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rule, groupId, groupName, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TemplateFieldDefinition {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    rule: ").append(toIndentedString(rule)).append("\n");
        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
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
