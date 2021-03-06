package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.OffsetDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * OperationProgress
 */

public class OperationProgress {
  @SerializedName("operationToken")
  private String operationToken = null;

  @SerializedName("operationType")
  private String operationType = null;

  @SerializedName("percentComplete")
  private Integer percentComplete = null;

  @SerializedName("status")
  private OperationStatus status = null;

  @SerializedName("errors")
  private List<OperationErrorItem> errors = null;

  @SerializedName("redirectUri")
  private String redirectUri = null;

  @SerializedName("startTimestamp")
  private OffsetDateTime startTimestamp = null;

  @SerializedName("statusTimestamp")
  private OffsetDateTime statusTimestamp = null;

  public OperationProgress operationToken(String operationToken) {
    this.operationToken = operationToken;
    return this;
  }

   /**
   * The operation token of the operation associated with this OperationProgress.
   * @return operationToken
  **/
  @Schema(description = "The operation token of the operation associated with this OperationProgress.")
  public String getOperationToken() {
    return operationToken;
  }

  public void setOperationToken(String operationToken) {
    this.operationToken = operationToken;
  }

  public OperationProgress operationType(String operationType) {
    this.operationType = operationType;
    return this;
  }

   /**
   * The type of the operation associated with this OperationProgress.
   * @return operationType
  **/
  @Schema(description = "The type of the operation associated with this OperationProgress.")
  public String getOperationType() {
    return operationType;
  }

  public void setOperationType(String operationType) {
    this.operationType = operationType;
  }

  public OperationProgress percentComplete(Integer percentComplete) {
    this.percentComplete = percentComplete;
    return this;
  }

   /**
   * Determines what percentage of the execution of the associated operation is completed.
   * @return percentComplete
  **/
  @Schema(description = "Determines what percentage of the execution of the associated operation is completed.")
  public Integer getPercentComplete() {
    return percentComplete;
  }

  public void setPercentComplete(Integer percentComplete) {
    this.percentComplete = percentComplete;
  }

  public OperationProgress status(OperationStatus status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @Schema(description = "")
  public OperationStatus getStatus() {
    return status;
  }

  public void setStatus(OperationStatus status) {
    this.status = status;
  }

  public OperationProgress errors(List<OperationErrorItem> errors) {
    this.errors = errors;
    return this;
  }

  public OperationProgress addErrorsItem(OperationErrorItem errorsItem) {
    if (this.errors == null) {
      this.errors = new ArrayList<OperationErrorItem>();
    }
    this.errors.add(errorsItem);
    return this;
  }

   /**
   * The list of errors occurred during the execution of the associated operation.
   * @return errors
  **/
  @Schema(description = "The list of errors occurred during the execution of the associated operation.")
  public List<OperationErrorItem> getErrors() {
    return errors;
  }

  public void setErrors(List<OperationErrorItem> errors) {
    this.errors = errors;
  }

  public OperationProgress redirectUri(String redirectUri) {
    this.redirectUri = redirectUri;
    return this;
  }

   /**
   * The URI which can be used (via api call) to access the result(s) of the associated operation.
   * @return redirectUri
  **/
  @Schema(description = "The URI which can be used (via api call) to access the result(s) of the associated operation.")
  public String getRedirectUri() {
    return redirectUri;
  }

  public void setRedirectUri(String redirectUri) {
    this.redirectUri = redirectUri;
  }

  public OperationProgress startTimestamp(OffsetDateTime startTimestamp) {
    this.startTimestamp = startTimestamp;
    return this;
  }

   /**
   * The timestamp representing when the associated operation&#x27;s execution is started.
   * @return startTimestamp
  **/
  @Schema(description = "The timestamp representing when the associated operation's execution is started.")
  public OffsetDateTime getStartTimestamp() {
    return startTimestamp;
  }

  public void setStartTimestamp(OffsetDateTime startTimestamp) {
    this.startTimestamp = startTimestamp;
  }

  public OperationProgress statusTimestamp(OffsetDateTime statusTimestamp) {
    this.statusTimestamp = statusTimestamp;
    return this;
  }

   /**
   * The timestamp representing the last time when the associated task&#x27;s status has changed.
   * @return statusTimestamp
  **/
  @Schema(description = "The timestamp representing the last time when the associated task's status has changed.")
  public OffsetDateTime getStatusTimestamp() {
    return statusTimestamp;
  }

  public void setStatusTimestamp(OffsetDateTime statusTimestamp) {
    this.statusTimestamp = statusTimestamp;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OperationProgress operationProgress = (OperationProgress) o;
    return Objects.equals(this.operationToken, operationProgress.operationToken) &&
        Objects.equals(this.operationType, operationProgress.operationType) &&
        Objects.equals(this.percentComplete, operationProgress.percentComplete) &&
        Objects.equals(this.status, operationProgress.status) &&
        Objects.equals(this.errors, operationProgress.errors) &&
        Objects.equals(this.redirectUri, operationProgress.redirectUri) &&
        Objects.equals(this.startTimestamp, operationProgress.startTimestamp) &&
        Objects.equals(this.statusTimestamp, operationProgress.statusTimestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operationToken, operationType, percentComplete, status, errors, redirectUri, startTimestamp, statusTimestamp);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OperationProgress {\n");
    
    sb.append("    operationToken: ").append(toIndentedString(operationToken)).append("\n");
    sb.append("    operationType: ").append(toIndentedString(operationType)).append("\n");
    sb.append("    percentComplete: ").append(toIndentedString(percentComplete)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
    sb.append("    redirectUri: ").append(toIndentedString(redirectUri)).append("\n");
    sb.append("    startTimestamp: ").append(toIndentedString(startTimestamp)).append("\n");
    sb.append("    statusTimestamp: ").append(toIndentedString(statusTimestamp)).append("\n");
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
