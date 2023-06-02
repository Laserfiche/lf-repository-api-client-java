package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.Objects;

/**
 * A machine-readable format for specifying errors in HTTP API responses based on
 * https://tools.ietf.org/html/rfc7807.
 */
@Schema(
    description =
        "A machine-readable format for specifying errors in HTTP API responses based on https://tools.ietf.org/html/rfc7807.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
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

  @JsonProperty("operationId")
  private String operationId = null;

  @JsonProperty("errorSource")
  private String errorSource = null;

  @JsonProperty("errorCode")
  private Integer errorCode = null;

  @JsonProperty("traceId")
  private String traceId = null;

  public ProblemDetails type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Returns he problem type.
   *
   * @return type
   */
  @Schema(description = "The problem type.")
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

  /**
   * Returns short, human-readable summary of the problem type.
   *
   * @return title
   */
  @Schema(description = "A short, human-readable summary of the problem type.")
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

  /**
   * Returns he HTTP status code.
   *
   * @return status
   */
  @Schema(required = true, description = "The HTTP status code.")
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

  /**
   * Returns human-readable explanation specific to this occurrence of the problem.
   *
   * @return detail
   */
  @Schema(description = "A human-readable explanation specific to this occurrence of the problem.")
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

  /**
   * Returns URI reference that identifies the specific occurrence of the problem.
   *
   * @return instance
   */
  @Schema(description = "A URI reference that identifies the specific occurrence of the problem.")
  public String getInstance() {
    return instance;
  }

  public void setInstance(String instance) {
    this.instance = instance;
  }

  public ProblemDetails operationId(String operationId) {
    this.operationId = operationId;
    return this;
  }

  /**
   * Returns he operation id.
   *
   * @return operationId
   */
  @Schema(description = "The operation id.")
  public String getOperationId() {
    return operationId;
  }

  public void setOperationId(String operationId) {
    this.operationId = operationId;
  }

  public ProblemDetails errorSource(String errorSource) {
    this.errorSource = errorSource;
    return this;
  }

  /**
   * Returns he error source.
   *
   * @return errorSource
   */
  @Schema(description = "The error source.")
  public String getErrorSource() {
    return errorSource;
  }

  public void setErrorSource(String errorSource) {
    this.errorSource = errorSource;
  }

  public ProblemDetails errorCode(Integer errorCode) {
    this.errorCode = errorCode;
    return this;
  }

  /**
   * Returns he error code.
   *
   * @return errorCode
   */
  @Schema(description = "The error code.")
  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  public ProblemDetails traceId(String traceId) {
    this.traceId = traceId;
    return this;
  }

  /**
   * Returns he trace id.
   *
   * @return traceId
   */
  @Schema(description = "The trace id.")
  public String getTraceId() {
    return traceId;
  }

  public void setTraceId(String traceId) {
    this.traceId = traceId;
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
    return Objects.equals(this.type, problemDetails.type)
        && Objects.equals(this.title, problemDetails.title)
        && Objects.equals(this.status, problemDetails.status)
        && Objects.equals(this.detail, problemDetails.detail)
        && Objects.equals(this.instance, problemDetails.instance)
        && Objects.equals(this.operationId, problemDetails.operationId)
        && Objects.equals(this.errorSource, problemDetails.errorSource)
        && Objects.equals(this.errorCode, problemDetails.errorCode)
        && Objects.equals(this.traceId, problemDetails.traceId)
        && super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        type,
        title,
        status,
        detail,
        instance,
        operationId,
        errorSource,
        errorCode,
        traceId,
        super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProblemDetails {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
    sb.append("    instance: ").append(toIndentedString(instance)).append("\n");
    sb.append("    operationId: ").append(toIndentedString(operationId)).append("\n");
    sb.append("    errorSource: ").append(toIndentedString(errorSource)).append("\n");
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    traceId: ").append(toIndentedString(traceId)).append("\n");
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
