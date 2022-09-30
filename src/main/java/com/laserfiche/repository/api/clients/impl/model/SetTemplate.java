package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(description = "The result of trying to assign a template to the entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SetTemplate {

    @JsonProperty("exceptions")
    private List<APIServerException> exceptions = null;

    @JsonProperty("template")
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
    return Objects.equals(this.exceptions, setTemplate.exceptions) && Objects.equals(this.template, setTemplate.template);
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

    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
