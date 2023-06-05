package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link
 * com.laserfiche.repository.api.clients.impl.TemplateDefinitionsClientImpl#getTemplateDefinitionById(ParametersForGetTemplateDefinitionById)
 * getTemplateDefinitionById}.
 */
public class ParametersForGetTemplateDefinitionById {

    /** The requested repository ID. */
    private String repoId;

    /** The requested template definition ID. */
    private Integer templateId;

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag.
     */
    private String culture;

    /** Limits the properties returned in the result. */
    private String select;

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForGetTemplateDefinitionById} The return value
     */
    public ParametersForGetTemplateDefinitionById setRepoId(String repoId) {
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
     * Sets the value of the templateId parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param templateId The requested template definition ID.
     * @return {@link ParametersForGetTemplateDefinitionById} The return value
     */
    public ParametersForGetTemplateDefinitionById setTemplateId(Integer templateId) {
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
     * Sets the value of the culture parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used
     *     for formatting. The value should be a standard language tag.
     * @return {@link ParametersForGetTemplateDefinitionById} The return value
     */
    public ParametersForGetTemplateDefinitionById setCulture(String culture) {
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
     * Sets the value of the select parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param select Limits the properties returned in the result.
     * @return {@link ParametersForGetTemplateDefinitionById} The return value
     */
    public ParametersForGetTemplateDefinitionById setSelect(String select) {
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
