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

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetFieldDefinitionById setFieldDefinitionId(int fieldDefinitionId) {
        this.fieldDefinitionId = fieldDefinitionId;
        return this;
    }

    public int getFieldDefinitionId() {
        return this.fieldDefinitionId;
    }

    public ParametersForGetFieldDefinitionById setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    public String getCulture() {
        return this.culture;
    }

    public ParametersForGetFieldDefinitionById setSelect(String select) {
        this.select = select;
        return this;
    }

    public String getSelect() {
        return this.select;
    }
}
