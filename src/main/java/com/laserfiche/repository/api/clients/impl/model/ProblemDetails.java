package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-08-19T17:07:43.799-04:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProblemDetails extends HashMap<String, Object> {

    @JsonProperty("type")
    private String type = null;

    @JsonProperty("title")
    private String title = null;

    @JsonProperty("status")
    private Integer status = null;

    @JsonProperty("detail")
    private String detail = null;

    @JsonProperty("instance")
    private String instance = null;

    @JsonProperty("extensions")
    private Map<String, Object> extensions = null;

    public ProblemDetails type(String type) {
        this.type = type;
        return this;
    }

    @Schema(description = "")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ProblemDetails title(String title) {
        this.title = title;
        return this;
    }

    @Schema(description = "")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ProblemDetails status(Integer status) {
        this.status = status;
        return this;
    }

    @Schema(description = "")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ProblemDetails detail(String detail) {
        this.detail = detail;
        return this;
    }

    @Schema(description = "")
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ProblemDetails instance(String instance) {
        this.instance = instance;
        return this;
    }

    @Schema(description = "")
    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public ProblemDetails extensions(Map<String, Object> extensions) {
        this.extensions = extensions;
        return this;
    }

    public ProblemDetails putExtensionsItem(String key, Object extensionsItem) {
        if (this.extensions == null) {
            this.extensions = new HashMap<String, Object>();
        }
        this.extensions.put(key, extensionsItem);
        return this;
    }

    @Schema(description = "")
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    public void setExtensions(Map<String, Object> extensions) {
        this.extensions = extensions;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProblemDetails problemDetails = (ProblemDetails) o;
        return Objects.equals(this.type, problemDetails.type) && Objects.equals(this.title,
                problemDetails.title) && Objects.equals(this.status, problemDetails.status) && Objects.equals(
                this.detail, problemDetails.detail) && Objects.equals(this.instance,
                problemDetails.instance) && Objects.equals(this.extensions, problemDetails.extensions) && super.equals(
                o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, title, status, detail, instance, extensions, super.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ProblemDetails {\n");
        sb
                .append("    ")
                .append(toIndentedString(super.toString()))
                .append("\n");
        sb
                .append("    type: ")
                .append(toIndentedString(type))
                .append("\n");
        sb
                .append("    title: ")
                .append(toIndentedString(title))
                .append("\n");
        sb
                .append("    status: ")
                .append(toIndentedString(status))
                .append("\n");
        sb
                .append("    detail: ")
                .append(toIndentedString(detail))
                .append("\n");
        sb
                .append("    instance: ")
                .append(toIndentedString(instance))
                .append("\n");
        sb
                .append("    extensions: ")
                .append(toIndentedString(extensions))
                .append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o
                .toString()
                .replace("\n", "\n    ");
    }
}
