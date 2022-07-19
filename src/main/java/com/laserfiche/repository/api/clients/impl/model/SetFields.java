package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * The result of trying to assign fields to the entry.
 */
@Schema(description = "The result of trying to assign fields to the entry.")
public class SetFields {
  @SerializedName("exceptions")
  private List<APIServerException> exceptions = null;

  @SerializedName("fieldCount")
  private Integer fieldCount = null;

  public SetFields exceptions(List<APIServerException> exceptions) {
    this.exceptions = exceptions;
    return this;
  }

  public SetFields addExceptionsItem(APIServerException exceptionsItem) {
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

  public SetFields fieldCount(Integer fieldCount) {
    this.fieldCount = fieldCount;
    return this;
  }

   /**
   * The number of fields assigned to the entry.
   * @return fieldCount
  **/
  @Schema(description = "The number of fields assigned to the entry.")
  public Integer getFieldCount() {
    return fieldCount;
  }

  public void setFieldCount(Integer fieldCount) {
    this.fieldCount = fieldCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SetFields setFields = (SetFields) o;
    return Objects.equals(this.exceptions, setFields.exceptions) &&
        Objects.equals(this.fieldCount, setFields.fieldCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(exceptions, fieldCount);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SetFields {\n");
    
    sb.append("    exceptions: ").append(toIndentedString(exceptions)).append("\n");
    sb.append("    fieldCount: ").append(toIndentedString(fieldCount)).append("\n");
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
