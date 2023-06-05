package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;

/**
 * The encapsulated parameters for {@link
 * com.laserfiche.repository.api.clients.impl.FieldDefinitionsClientImpl#getFieldDefinitionById(ParametersForGetFieldDefinitionById)
 * getFieldDefinitionById}.
 */
public class ParametersForGetFieldDefinitionById {

  /** The requested repository ID. */
  private String repoId;

  /** The requested field definition ID. */
  private Integer fieldDefinitionId;

  /**
   * An optional query parameter used to indicate the locale that should be used for formatting. The
   * value should be a standard language tag.
   */
  private String culture;

  /** Limits the properties returned in the result. */
  private String select;

  /**
   * Sets the value of the repoId parameter and returns the current object, to enable chaining
   * further setters.
   *
   * @param repoId The requested repository ID.
   * @return {@link ParametersForGetFieldDefinitionById} The return value
   */
  public ParametersForGetFieldDefinitionById setRepoId(String repoId) {
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
   * Sets the value of the fieldDefinitionId parameter and returns the current object, to enable
   * chaining further setters.
   *
   * @param fieldDefinitionId The requested field definition ID.
   * @return {@link ParametersForGetFieldDefinitionById} The return value
   */
  public ParametersForGetFieldDefinitionById setFieldDefinitionId(Integer fieldDefinitionId) {
    this.fieldDefinitionId = fieldDefinitionId;
    return this;
  }

  /**
   * The requested field definition ID.
   *
   * @return {@link Integer} The return value
   */
  public Integer getFieldDefinitionId() {
    return this.fieldDefinitionId;
  }

  /**
   * Sets the value of the culture parameter and returns the current object, to enable chaining
   * further setters.
   *
   * @param culture An optional query parameter used to indicate the locale that should be used for
   *     formatting. The value should be a standard language tag.
   * @return {@link ParametersForGetFieldDefinitionById} The return value
   */
  public ParametersForGetFieldDefinitionById setCulture(String culture) {
    this.culture = culture;
    return this;
  }

  /**
   * An optional query parameter used to indicate the locale that should be used for formatting.<br>
   * The value should be a standard language tag.
   *
   * @return {@link String} The return value
   */
  public String getCulture() {
    return this.culture;
  }

  /**
   * Sets the value of the select parameter and returns the current object, to enable chaining
   * further setters.
   *
   * @param select Limits the properties returned in the result.
   * @return {@link ParametersForGetFieldDefinitionById} The return value
   */
  public ParametersForGetFieldDefinitionById setSelect(String select) {
    this.select = select;
    return this;
  }

  /**
   * Limits the properties returned in the result.
   *
   * @return {@link String} The return value
   */
  public String getSelect() {
    return this.select;
  }
}
