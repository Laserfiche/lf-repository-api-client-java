package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.TemplateDefinitionsClientImpl#getTemplateFieldDefinitionsByTemplateName(ParametersForGetTemplateFieldDefinitionsByTemplateName) getTemplateFieldDefinitionsByTemplateName}.
 */
public class ParametersForGetTemplateFieldDefinitionsByTemplateName {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * A required query parameter for the requested template name.
     */
    private String templateName;

    /**
     * An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     */
    private String prefer;

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
     * Specifies the order in which items are returned. The maximum number of expressions is 5.
     */
    private String orderby;

    /**
     * Limits the number of items returned from a collection.
     */
    private int top;

    /**
     * Excludes the specified number of items of the queried collection from the result.
     */
    private int skip;

    /**
     * Indicates whether the total count of items within a collection are returned in the result.
     */
    private boolean count;

    /**
     * Builder for setting the repoId parameter.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForGetTemplateFieldDefinitionsByTemplateName} The return value
     */
    public ParametersForGetTemplateFieldDefinitionsByTemplateName setRepoId(String repoId) {
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
     * Builder for setting the templateName parameter.
     *
     * @param templateName A required query parameter for the requested template name.
     * @return {@link ParametersForGetTemplateFieldDefinitionsByTemplateName} The return value
     */
    public ParametersForGetTemplateFieldDefinitionsByTemplateName setTemplateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    /**
     * A required query parameter for the requested template name.
     *
     * @return {@link String} The return value
     */
    public String getTemplateName() {
        return this.templateName;
    }

    /**
     * Builder for setting the prefer parameter.
     *
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @return {@link ParametersForGetTemplateFieldDefinitionsByTemplateName} The return value
     */
    public ParametersForGetTemplateFieldDefinitionsByTemplateName setPrefer(String prefer) {
        this.prefer = prefer;
        return this;
    }

    /**
     * An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     *
     * @return {@link String} The return value
     */
    public String getPrefer() {
        return this.prefer;
    }

    /**
     * Builder for setting the culture parameter.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.
     *                The value should be a standard language tag.
     * @return {@link ParametersForGetTemplateFieldDefinitionsByTemplateName} The return value
     */
    public ParametersForGetTemplateFieldDefinitionsByTemplateName setCulture(String culture) {
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
     * @return {@link ParametersForGetTemplateFieldDefinitionsByTemplateName} The return value
     */
    public ParametersForGetTemplateFieldDefinitionsByTemplateName setSelect(String select) {
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

    /**
     * Builder for setting the orderby parameter.
     *
     * @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @return {@link ParametersForGetTemplateFieldDefinitionsByTemplateName} The return value
     */
    public ParametersForGetTemplateFieldDefinitionsByTemplateName setOrderby(String orderby) {
        this.orderby = orderby;
        return this;
    }

    /**
     * Specifies the order in which items are returned. The maximum number of expressions is 5.
     *
     * @return {@link String} The return value
     */
    public String getOrderby() {
        return this.orderby;
    }

    /**
     * Builder for setting the top parameter.
     *
     * @param top Limits the number of items returned from a collection.
     * @return {@link ParametersForGetTemplateFieldDefinitionsByTemplateName} The return value
     */
    public ParametersForGetTemplateFieldDefinitionsByTemplateName setTop(int top) {
        this.top = top;
        return this;
    }

    /**
     * Limits the number of items returned from a collection.
     *
     * @return {@link int} The return value
     */
    public int getTop() {
        return this.top;
    }

    /**
     * Builder for setting the skip parameter.
     *
     * @param skip Excludes the specified number of items of the queried collection from the result.
     * @return {@link ParametersForGetTemplateFieldDefinitionsByTemplateName} The return value
     */
    public ParametersForGetTemplateFieldDefinitionsByTemplateName setSkip(int skip) {
        this.skip = skip;
        return this;
    }

    /**
     * Excludes the specified number of items of the queried collection from the result.
     *
     * @return {@link int} The return value
     */
    public int getSkip() {
        return this.skip;
    }

    /**
     * Builder for setting the count parameter.
     *
     * @param count Indicates whether the total count of items within a collection are returned in the result.
     * @return {@link ParametersForGetTemplateFieldDefinitionsByTemplateName} The return value
     */
    public ParametersForGetTemplateFieldDefinitionsByTemplateName setCount(boolean count) {
        this.count = count;
        return this;
    }

    /**
     * Indicates whether the total count of items within a collection are returned in the result.
     *
     * @return {@link boolean} The return value
     */
    public boolean isCount() {
        return this.count;
    }
}
