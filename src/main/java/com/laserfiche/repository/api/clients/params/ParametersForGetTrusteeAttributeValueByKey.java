package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;

/**
 * The encapsulated parameters for {@link
 * com.laserfiche.repository.api.clients.impl.AttributesClientImpl#getTrusteeAttributeValueByKey(ParametersForGetTrusteeAttributeValueByKey)
 * getTrusteeAttributeValueByKey}.
 */
public class ParametersForGetTrusteeAttributeValueByKey {

  /** The requested repository ID. */
  private String repoId;

  /** The requested attribute key. */
  private String attributeKey;

  /**
   * Boolean value that indicates whether to return attributes associated with everyone or the
   * currently authenticated user.
   */
  private Boolean everyone;

  /**
   * Sets the value of the repoId parameter and returns the current object, to enable chaining
   * further setters.
   *
   * @param repoId The requested repository ID.
   * @return {@link ParametersForGetTrusteeAttributeValueByKey} The return value
   */
  public ParametersForGetTrusteeAttributeValueByKey setRepoId(String repoId) {
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
   * Sets the value of the attributeKey parameter and returns the current object, to enable chaining
   * further setters.
   *
   * @param attributeKey The requested attribute key.
   * @return {@link ParametersForGetTrusteeAttributeValueByKey} The return value
   */
  public ParametersForGetTrusteeAttributeValueByKey setAttributeKey(String attributeKey) {
    this.attributeKey = attributeKey;
    return this;
  }

  /**
   * The requested attribute key.
   *
   * @return {@link String} The return value
   */
  public String getAttributeKey() {
    return this.attributeKey;
  }

  /**
   * Sets the value of the everyone parameter and returns the current object, to enable chaining
   * further setters.
   *
   * @param everyone Boolean value that indicates whether to return attributes associated with
   *     everyone or the currently authenticated user.
   * @return {@link ParametersForGetTrusteeAttributeValueByKey} The return value
   */
  public ParametersForGetTrusteeAttributeValueByKey setEveryone(Boolean everyone) {
    this.everyone = everyone;
    return this;
  }

  /**
   * Boolean value that indicates whether to return attributes associated with everyone or the
   * currently authenticated user.
   *
   * @return {@link Boolean} The return value
   */
  public Boolean isEveryone() {
    return this.everyone;
  }
}
