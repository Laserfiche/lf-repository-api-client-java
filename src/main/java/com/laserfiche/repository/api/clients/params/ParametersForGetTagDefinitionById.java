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

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetTagDefinitionById setTagId(int tagId) {
        this.tagId = tagId;
        return this;
    }

    public int getTagId() {
        return this.tagId;
    }

    public ParametersForGetTagDefinitionById setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    public String getCulture() {
        return this.culture;
    }

    public ParametersForGetTagDefinitionById setSelect(String select) {
        this.select = select;
        return this;
    }

    public String getSelect() {
        return this.select;
    }
}
