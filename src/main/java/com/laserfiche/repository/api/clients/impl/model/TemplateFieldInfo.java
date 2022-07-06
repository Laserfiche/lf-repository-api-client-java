/*
 * Laserfiche Repository API
 * Welcome to the Laserfiche API Swagger Playground. You can try out any of our API calls against your live Laserfiche Cloud account. Visit the developer center for more details: <a href=\"https://developer.laserfiche.com\">https://developer.laserfiche.com</a><p><strong>Build# : </strong>eb72c77d640efc5f986310ccc43114fc25742dab_.20220610.1</p>
 *
 * OpenAPI spec version: 2-alpha
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * TemplateFieldInfo
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-06-21T09:31:30.649462900-04:00[America/New_York]")
public class TemplateFieldInfo extends WFieldInfo {
  @SerializedName("rule")
  private Object rule = null;

  @SerializedName("groupId")
  private Integer groupId = null;

  @SerializedName("groupName")
  private String groupName = null;

  public TemplateFieldInfo rule(Object rule) {
    this.rule = rule;
    return this;
  }

   /**
   * A form logic rule associated with a Laserfiche template and field definition.
   * @return rule
  **/
  @Schema(description = "A form logic rule associated with a Laserfiche template and field definition.")
  public Object getRule() {
    return rule;
  }

  public void setRule(Object rule) {
    this.rule = rule;
  }

  public TemplateFieldInfo groupId(Integer groupId) {
    this.groupId = groupId;
    return this;
  }

   /**
   * The group id of the field in the template.
   * @return groupId
  **/
  @Schema(description = "The group id of the field in the template.")
  public Integer getGroupId() {
    return groupId;
  }

  public void setGroupId(Integer groupId) {
    this.groupId = groupId;
  }

  public TemplateFieldInfo groupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

   /**
   * The name of field group.
   * @return groupName
  **/
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
    TemplateFieldInfo templateFieldInfo = (TemplateFieldInfo) o;
    return Objects.equals(this.rule, templateFieldInfo.rule) &&
        Objects.equals(this.groupId, templateFieldInfo.groupId) &&
        Objects.equals(this.groupName, templateFieldInfo.groupName) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rule, groupId, groupName, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TemplateFieldInfo {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    rule: ").append(toIndentedString(rule)).append("\n");
    sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
    sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}