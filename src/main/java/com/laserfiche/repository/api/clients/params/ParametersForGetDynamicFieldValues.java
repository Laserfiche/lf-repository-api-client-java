package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.GetDynamicFieldLogicValueRequest;

/**
 * The encapsulated parameters for {@link
 * com.laserfiche.repository.api.clients.impl.EntriesClientImpl#getDynamicFieldValues(ParametersForGetDynamicFieldValues)
 * getDynamicFieldValues}.
 */
public class ParametersForGetDynamicFieldValues {

  /** The requested repository ID. */
  private String repoId;

  /** The requested entry ID. */
  private Integer entryId;

  private GetDynamicFieldLogicValueRequest requestBody;

  /**
   * Sets the value of the repoId parameter and returns the current object, to enable chaining
   * further setters.
   *
   * @param repoId The requested repository ID.
   * @return {@link ParametersForGetDynamicFieldValues} The return value
   */
  public ParametersForGetDynamicFieldValues setRepoId(String repoId) {
    this.repoId = repoId;
    return this;
  }

  /**
   * The requested repository ID.
   *
   * @return {@link String} The return value
   */
  public String getRepoId() {
    return this.repoId;
  }

  /**
   * Sets the value of the entryId parameter and returns the current object, to enable chaining
   * further setters.
   *
   * @param entryId The requested entry ID.
   * @return {@link ParametersForGetDynamicFieldValues} The return value
   */
  public ParametersForGetDynamicFieldValues setEntryId(Integer entryId) {
    this.entryId = entryId;
    return this;
  }

  /**
   * The requested entry ID.
   *
   * @return {@link Integer} The return value
   */
  public Integer getEntryId() {
    return this.entryId;
  }

  public ParametersForGetDynamicFieldValues setRequestBody(
      GetDynamicFieldLogicValueRequest requestBody) {
    this.requestBody = requestBody;
    return this;
  }

  public GetDynamicFieldLogicValueRequest getRequestBody() {
    return this.requestBody;
  }
}
