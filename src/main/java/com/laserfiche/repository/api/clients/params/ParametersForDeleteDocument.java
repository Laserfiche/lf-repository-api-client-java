package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link
 * com.laserfiche.repository.api.clients.impl.EntriesClientImpl#deleteDocument(ParametersForDeleteDocument)
 * deleteDocument}.
 */
public class ParametersForDeleteDocument {

  /** The requested repository ID. */
  private String repoId;

  /** The requested document ID. */
  private Integer entryId;

  /**
   * Sets the value of the repoId parameter and returns the current object, to enable chaining
   * further setters.
   *
   * @param repoId The requested repository ID.
   * @return {@link ParametersForDeleteDocument} The return value
   */
  public ParametersForDeleteDocument setRepoId(String repoId) {
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
   * @param entryId The requested document ID.
   * @return {@link ParametersForDeleteDocument} The return value
   */
  public ParametersForDeleteDocument setEntryId(Integer entryId) {
    this.entryId = entryId;
    return this;
  }

  /**
   * The requested document ID.
   *
   * @return {@link Integer} The return value
   */
  public Integer getEntryId() {
    return this.entryId;
  }
}
