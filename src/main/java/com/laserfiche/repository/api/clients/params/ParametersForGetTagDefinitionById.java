package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.TagDefinitionsClientImpl#getTagDefinitionById(ParametersForGetTagDefinitionById) getTagDefinitionById}.
 */
public class ParametersForGetTagDefinitionById {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested tag definition ID.
     */
    private int tagId;

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
     * @return {@link ParametersForGetTagDefinitionById} The return value
     */
    public ParametersForGetTagDefinitionById setRepoId(String repoId) {
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
     * Builder for setting the tagId parameter.
     *
     * @param tagId The requested tag definition ID.
     * @return {@link ParametersForGetTagDefinitionById} The return value
     */
    public ParametersForGetTagDefinitionById setTagId(int tagId) {
        this.tagId = tagId;
        return this;
    }

    /**
     * The requested tag definition ID.
     *
     * @return {@link int} The return value
     */
    public int getTagId() {
        return this.tagId;
    }

    /**
     * Builder for setting the culture parameter.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.
     *                The value should be a standard language tag.
     * @return {@link ParametersForGetTagDefinitionById} The return value
     */
    public ParametersForGetTagDefinitionById setCulture(String culture) {
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
     * @return {@link ParametersForGetTagDefinitionById} The return value
     */
    public ParametersForGetTagDefinitionById setSelect(String select) {
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
