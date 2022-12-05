package com.laserfiche.repository.api.clients.params;

public class ParametersForGetTemplateDefinitionById {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested template definition ID.
     */
    private int templateId;

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag.
     */
    private String culture;

    /**
     * Limits the properties returned in the result.
     */
    private String select;

    public ParametersForGetTemplateDefinitionById setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    /**
     * The requested repository ID.
     *
     * @return String The return value
     */
    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetTemplateDefinitionById setTemplateId(int templateId) {
        this.templateId = templateId;
        return this;
    }

    /**
     * The requested template definition ID.
     *
     * @return int The return value
     */
    public int getTemplateId() {
        return this.templateId;
    }

    public ParametersForGetTemplateDefinitionById setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag.
     *
     * @return String The return value
     */
    public String getCulture() {
        return this.culture;
    }

    public ParametersForGetTemplateDefinitionById setSelect(String select) {
        this.select = select;
        return this;
    }

    /**
     * Limits the properties returned in the result.
     *
     * @return String The return value
     */
    public String getSelect() {
        return this.select;
    }
}
