package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;

/**
 * The encapsulated parameters for {@link
 * com.laserfiche.repository.api.clients.impl.EntriesClientImpl#getEntryByPath(ParametersForGetEntryByPath)
 * getEntryByPath}.
 */
public class ParametersForGetEntryByPath {

  /** The requested repository ID. */
  private String repoId;

  /** The requested entry path. */
  private String fullPath;

  /**
   * An optional query parameter used to indicate whether or not the closest ancestor in the path
   * should be returned if the initial entry path is not found. The default value is false.
   */
  private Boolean fallbackToClosestAncestor;

  /**
   * Sets the value of the repoId parameter and returns the current object, to enable chaining
   * further setters.
   *
   * @param repoId The requested repository ID.
   * @return {@link ParametersForGetEntryByPath} The return value
   */
  public ParametersForGetEntryByPath setRepoId(String repoId) {
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
   * Sets the value of the fullPath parameter and returns the current object, to enable chaining
   * further setters.
   *
   * @param fullPath The requested entry path.
   * @return {@link ParametersForGetEntryByPath} The return value
   */
  public ParametersForGetEntryByPath setFullPath(String fullPath) {
    this.fullPath = fullPath;
    return this;
  }

  /**
   * The requested entry path.
   *
   * @return {@link String} The return value
   */
  public String getFullPath() {
    return this.fullPath;
  }

  /**
   * Sets the value of the fallbackToClosestAncestor parameter and returns the current object, to
   * enable chaining further setters.
   *
   * @param fallbackToClosestAncestor An optional query parameter used to indicate whether or not
   *     the closest ancestor in the path should be returned if the initial entry path is not found.
   *     The default value is false.
   * @return {@link ParametersForGetEntryByPath} The return value
   */
  public ParametersForGetEntryByPath setFallbackToClosestAncestor(
      Boolean fallbackToClosestAncestor) {
    this.fallbackToClosestAncestor = fallbackToClosestAncestor;
    return this;
  }

  /**
   * An optional query parameter used to indicate whether or not the closest ancestor in the path
   * should be returned if the initial entry path is not found. The default value is false.
   *
   * @return {@link Boolean} The return value
   */
  public Boolean isFallbackToClosestAncestor() {
    return this.fallbackToClosestAncestor;
  }
}
