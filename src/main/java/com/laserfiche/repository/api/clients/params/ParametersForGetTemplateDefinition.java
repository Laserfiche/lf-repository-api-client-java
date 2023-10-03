package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.TemplateDefinitionsClientImpl#getTemplateDefinition(ParametersForGetTemplateDefinition) getTemplateDefinition}.
 */
public class ParametersForGetTemplateDefinition {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * The requested template definition ID.
     */
    private Integer templateId;

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
     * @return {@link ParametersForGetTemplateDefinition} The return value
     */
    public ParametersForGetTemplateDefinition setRepositoryId(String repositoryId) {
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
     * Sets the value of the templateId parameter and returns the current object, to enable chaining further setters.
     *
     * @param templateId The requested template definition ID.
     * @return {@link ParametersForGetTemplateDefinition} The return value
     */
    public ParametersForGetTemplateDefinition setTemplateId(Integer templateId) {
        this.templateId = templateId;
        return this;
    }

    /**
     * The requested template definition ID.
     *
     * @return {@link Integer} The return value
     */
    public Integer getTemplateId() {
        return this.templateId;
    }

    /**
     * Sets the value of the culture parameter and returns the current object, to enable chaining further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting. The value should be a standard language tag.
     * @return {@link ParametersForGetTemplateDefinition} The return value
     */
    public ParametersForGetTemplateDefinition setCulture(String culture) {
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
     * @return {@link ParametersForGetTemplateDefinition} The return value
     */
    public ParametersForGetTemplateDefinition setSelect(String select) {
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
