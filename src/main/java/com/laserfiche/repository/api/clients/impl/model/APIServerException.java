package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;
/**
 * APIServerException
 */

public class APIServerException {
  @SerializedName("operationId")
  private String operationId = null;

  @SerializedName("message")
  private String message = null;

  @SerializedName("errorCode")
  private Integer errorCode = null;

  @SerializedName("errorClass")
  private String errorClass = null;

  @SerializedName("statusCode")
  private Integer statusCode = null;

  @SerializedName("errorSource")
  private String errorSource = null;

  public APIServerException operationId(String operationId) {
    this.operationId = operationId;
    return this;
  }

   /**
   * The id of the operation that threw the exception.
   * @return operationId
  **/
  @Schema(description = "The id of the operation that threw the exception.")
  public String getOperationId() {
    return operationId;
  }

  public void setOperationId(String operationId) {
    this.operationId = operationId;
  }

  public APIServerException message(String message) {
    this.message = message;
    return this;
  }

   /**
   * The explaination of the exception that occurred.
   * @return message
  **/
  @Schema(description = "The explaination of the exception that occurred.")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public APIServerException errorCode(Integer errorCode) {
    this.errorCode = errorCode;
    return this;
  }

   /**
   * The code associated with the exception.
   * @return errorCode
  **/
  @Schema(description = "The code associated with the exception.")
  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  public APIServerException errorClass(String errorClass) {
    this.errorClass = errorClass;
    return this;
  }

   /**
   * The class of exceptions this belongs to.
   * @return errorClass
  **/
  @Schema(description = "The class of exceptions this belongs to.")
  public String getErrorClass() {
    return errorClass;
  }

  public void setErrorClass(String errorClass) {
    this.errorClass = errorClass;
  }

  public APIServerException statusCode(Integer statusCode) {
    this.statusCode = statusCode;
    return this;
  }

   /**
   * The HTTP status code returned.
   * @return statusCode
  **/
  @Schema(description = "The HTTP status code returned.")
  public Integer getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public APIServerException errorSource(String errorSource) {
    this.errorSource = errorSource;
    return this;
  }

   /**
   * The source of where the exception occurred.
   * @return errorSource
  **/
  @Schema(description = "The source of where the exception occurred.")
  public String getErrorSource() {
    return errorSource;
  }

  public void setErrorSource(String errorSource) {
    this.errorSource = errorSource;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    APIServerException apIServerException = (APIServerException) o;
    return Objects.equals(this.operationId, apIServerException.operationId) &&
        Objects.equals(this.message, apIServerException.message) &&
        Objects.equals(this.errorCode, apIServerException.errorCode) &&
        Objects.equals(this.errorClass, apIServerException.errorClass) &&
        Objects.equals(this.statusCode, apIServerException.statusCode) &&
        Objects.equals(this.errorSource, apIServerException.errorSource);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operationId, message, errorCode, errorClass, statusCode, errorSource);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class APIServerException {\n");
    
    sb.append("    operationId: ").append(toIndentedString(operationId)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    errorClass: ").append(toIndentedString(errorClass)).append("\n");
    sb.append("    statusCode: ").append(toIndentedString(statusCode)).append("\n");
    sb.append("    errorSource: ").append(toIndentedString(errorSource)).append("\n");
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
