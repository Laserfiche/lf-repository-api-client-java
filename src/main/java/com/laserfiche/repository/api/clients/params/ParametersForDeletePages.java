package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;

/**
 * The encapsulated parameters for {@link
 * com.laserfiche.repository.api.clients.impl.EntriesClientImpl#deletePages(ParametersForDeletePages)
 * deletePages}.
 */
public class ParametersForDeletePages {

  /** The requested repository ID. */
  private String repoId;

  /** The requested document ID. */
  private Integer entryId;

  /** The pages to be deleted. */
  private String pageRange;

  /**
   * Sets the value of the repoId parameter and returns the current object, to enable chaining
   * further setters.
   *
   * @param repoId The requested repository ID.
   * @return {@link ParametersForDeletePages} The return value
   */
  public ParametersForDeletePages setRepoId(String repoId) {
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
   * @return {@link ParametersForDeletePages} The return value
   */
  public ParametersForDeletePages setEntryId(Integer entryId) {
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

  /**
   * Sets the value of the pageRange parameter and returns the current object, to enable chaining
   * further setters.
   *
   * @param pageRange The pages to be deleted.
   * @return {@link ParametersForDeletePages} The return value
   */
  public ParametersForDeletePages setPageRange(String pageRange) {
    this.pageRange = pageRange;
    return this;
  }

  /**
   * The pages to be deleted.
   *
   * @return {@link String} The return value
   */
  public String getPageRange() {
    return this.pageRange;
  }
}
