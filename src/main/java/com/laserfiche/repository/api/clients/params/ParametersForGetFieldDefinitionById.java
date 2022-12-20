package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.FieldDefinitionsClientImpl#getFieldDefinitionById(ParametersForGetFieldDefinitionById) getFieldDefinitionById}.
 */
public class ParametersForGetFieldDefinitionById {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested field definition ID.
     */
    private int fieldDefinitionId;

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag.
     */
    private String culture;

    /**
     * Limits the properties returned in the result.
     */
    private String select;

    /**
     * Builder for setting the repoId parameter.
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
     * Builder for setting the fieldDefinitionId parameter.
     *
     * @param fieldDefinitionId The requested field definition ID.
     * @return {@link ParametersForGetFieldDefinitionById} The return value
     */
    public ParametersForGetFieldDefinitionById setFieldDefinitionId(int fieldDefinitionId) {
        this.fieldDefinitionId = fieldDefinitionId;
        return this;
    }

    /**
     * The requested field definition ID.
     *
     * @return {@link int} The return value
     */
    public int getFieldDefinitionId() {
        return this.fieldDefinitionId;
    }

    /**
     * Builder for setting the culture parameter.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.
     *                The value should be a standard language tag.
     * @return {@link ParametersForGetFieldDefinitionById} The return value
     */
    public ParametersForGetFieldDefinitionById setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag.
     *
     * @return {@link String} The return value
     */
    public String getCulture() {
        return this.culture;
    }

    /**
     * Builder for setting the select parameter.
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
