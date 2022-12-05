package com.laserfiche.repository.api.clients.params;

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

    public ParametersForGetTagDefinitionById setRepoId(String repoId) {
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

    public ParametersForGetTagDefinitionById setTagId(int tagId) {
        this.tagId = tagId;
        return this;
    }

    /**
     * The requested tag definition ID.
     *
     * @return int The return value
     */
    public int getTagId() {
        return this.tagId;
    }

    public ParametersForGetTagDefinitionById setCulture(String culture) {
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

    public ParametersForGetTagDefinitionById setSelect(String select) {
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
