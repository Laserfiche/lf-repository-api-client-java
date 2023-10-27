// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.TagDefinitionsClientImpl#getTagDefinition(ParametersForGetTagDefinition) getTagDefinition}.
 */
public class ParametersForGetTagDefinition {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * The requested tag definition ID.
     */
    private Integer tagId;

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
     * @return {@link ParametersForGetTagDefinition} The return value
     */
    public ParametersForGetTagDefinition setRepositoryId(String repositoryId) {
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
     * Sets the value of the tagId parameter and returns the current object, to enable chaining further setters.
     *
     * @param tagId The requested tag definition ID.
     * @return {@link ParametersForGetTagDefinition} The return value
     */
    public ParametersForGetTagDefinition setTagId(Integer tagId) {
        this.tagId = tagId;
        return this;
    }

    /**
     * The requested tag definition ID.
     *
     * @return {@link Integer} The return value
     */
    public Integer getTagId() {
        return this.tagId;
    }

    /**
     * Sets the value of the culture parameter and returns the current object, to enable chaining further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting. The value should be a standard language tag.
     * @return {@link ParametersForGetTagDefinition} The return value
     */
    public ParametersForGetTagDefinition setCulture(String culture) {
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
     * @return {@link ParametersForGetTagDefinition} The return value
     */
    public ParametersForGetTagDefinition setSelect(String select) {
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
