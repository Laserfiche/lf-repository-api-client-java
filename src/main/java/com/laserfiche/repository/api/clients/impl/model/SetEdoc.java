package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * The result of trying to create the electronic document.
 */
@Schema(description = "The result of trying to create the electronic document.")
public class SetEdoc {
  @SerializedName("exceptions")
  private List<APIServerException> exceptions = null;

  public SetEdoc exceptions(List<APIServerException> exceptions) {
    this.exceptions = exceptions;
    return this;
  }

  public SetEdoc addExceptionsItem(APIServerException exceptionsItem) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SetEdoc setEdoc = (SetEdoc) o;
    return Objects.equals(this.exceptions, setEdoc.exceptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(exceptions);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SetEdoc {\n");
    
    sb.append("    exceptions: ").append(toIndentedString(exceptions)).append("\n");
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
