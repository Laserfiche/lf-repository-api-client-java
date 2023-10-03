package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.FieldDefinitionsClientImpl#getFieldDefinition(ParametersForGetFieldDefinition) getFieldDefinition}.
 */
public class ParametersForGetFieldDefinition {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * The requested field definition ID.
     */
    private Integer fieldId;

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting. The value should be a standard language tag.
     */
    private String culture;

    /**
     * Limits the properties returned in the result.
     */
    private String select;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForGetFieldDefinition} The return value
     */
    public ParametersForGetFieldDefinition setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
        return this;
    }

    /**
     * The requested repository ID.
     *
     * @return {@link String} The return value
     */
    public String getRepositoryId() {
        return this.repositoryId;
    }

    /**
     * Sets the value of the fieldId parameter and returns the current object, to enable chaining further setters.
     *
     * @param fieldId The requested field definition ID.
     * @return {@link ParametersForGetFieldDefinition} The return value
     */
    public ParametersForGetFieldDefinition setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
        return this;
    }

    /**
     * The requested field definition ID.
     *
     * @return {@link Integer} The return value
     */
    public Integer getFieldId() {
        return this.fieldId;
    }

    /**
     * Sets the value of the culture parameter and returns the current object, to enable chaining further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting. The value should be a standard language tag.
     * @return {@link ParametersForGetFieldDefinition} The return value
     */
    public ParametersForGetFieldDefinition setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting. The value should be a standard language tag.
     *
     * @return {@link String} The return value
     */
    public String getCulture() {
        return this.culture;
    }

    /**
     * Sets the value of the select parameter and returns the current object, to enable chaining further setters.
     *
     * @param select Limits the properties returned in the result.
     * @return {@link ParametersForGetFieldDefinition} The return value
     */
    public ParametersForGetFieldDefinition setSelect(String select) {
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
