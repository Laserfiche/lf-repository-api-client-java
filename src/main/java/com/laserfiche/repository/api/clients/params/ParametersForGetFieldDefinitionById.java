package com.laserfiche.repository.api.clients.params;

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

    public ParametersForGetFieldDefinitionById setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }


    /**
     * Returns the value of 'repoId' field, which is the requested repository ID.
     *
     * @return String The return value
     */
    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetFieldDefinitionById setFieldDefinitionId(int fieldDefinitionId) {
        this.fieldDefinitionId = fieldDefinitionId;
        return this;
    }


    /**
     * Returns the value of 'fieldDefinitionId' field, which is the requested field definition ID.
     *
     * @return int The return value
     */
    public int getFieldDefinitionId() {
        return this.fieldDefinitionId;
    }

    public ParametersForGetFieldDefinitionById setCulture(String culture) {
        this.culture = culture;
        return this;
    }


    /**
     * Returns the value of 'culture' field, which is an optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag.
     *
     * @return String The return value
     */
    public String getCulture() {
        return this.culture;
    }

    public ParametersForGetFieldDefinitionById setSelect(String select) {
        this.select = select;
        return this;
    }


    /**
     * Returns the value of 'select' field, which is limits the properties returned in the result.
     *
     * @return String The return value
     */
    public String getSelect() {
        return this.select;
    }
}
