package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * The result of trying to assign a template to the entry.
 */
@Schema(description = "The result of trying to assign a template to the entry.")
public class SetTemplate {
  @SerializedName("exceptions")
  private List<APIServerException> exceptions = null;

  @SerializedName("template")
  private String template = null;

  public SetTemplate exceptions(List<APIServerException> exceptions) {
    this.exceptions = exceptions;
    return this;
  }

  public SetTemplate addExceptionsItem(APIServerException exceptionsItem) {
    if (this.exceptions == null) {
      this.exceptions = new ArrayList<APIServerException>();
    }
    this.exceptions.add(exceptionsItem);
    return this;
  }

   /**
   * The list of exceptions that occured when trying to perform the operation.
   * @return exceptions
  **/
  @Schema(description = "The list of exceptions that occured when trying to perform the operation.")
  public List<APIServerException> getExceptions() {
    return exceptions;
  }

  public void setExceptions(List<APIServerException> exceptions) {
    this.exceptions = exceptions;
  }

  public SetTemplate template(String template) {
    this.template = template;
    return this;
  }

   /**
   * The name of the template assigned to the entry. If this is null, then no template was assigned.
   * @return template
  **/
  @Schema(description = "The name of the template assigned to the entry. If this is null, then no template was assigned.")
  public String getTemplate() {
    return template;
  }

  public void setTemplate(String template) {
    this.template = template;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SetTemplate setTemplate = (SetTemplate) o;
    return Objects.equals(this.exceptions, setTemplate.exceptions) &&
        Objects.equals(this.template, setTemplate.template);
  }

  @Override
  public int hashCode() {
    return Objects.hash(exceptions, template);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SetTemplate {\n");
    
    sb.append("    exceptions: ").append(toIndentedString(exceptions)).append("\n");
    sb.append("    template: ").append(toIndentedString(template)).append("\n");
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
