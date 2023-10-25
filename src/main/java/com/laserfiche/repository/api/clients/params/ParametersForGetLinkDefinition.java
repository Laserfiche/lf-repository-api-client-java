// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.LinkDefinitionsClientImpl#getLinkDefinition(ParametersForGetLinkDefinition) getLinkDefinition}.
 */
public class ParametersForGetLinkDefinition {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * The requested link definition ID.
     */
    private Integer linkDefinitionId;

    /**
     * Limits the properties returned in the result.
     */
    private String select;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForGetLinkDefinition} The return value
     */
    public ParametersForGetLinkDefinition setRepositoryId(String repositoryId) {
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
     * Sets the value of the linkDefinitionId parameter and returns the current object, to enable chaining further setters.
     *
     * @param linkDefinitionId The requested link definition ID.
     * @return {@link ParametersForGetLinkDefinition} The return value
     */
    public ParametersForGetLinkDefinition setLinkDefinitionId(Integer linkDefinitionId) {
        this.linkDefinitionId = linkDefinitionId;
        return this;
    }

    /**
     * The requested link definition ID.
     *
     * @return {@link Integer} The return value
     */
    public Integer getLinkDefinitionId() {
        return this.linkDefinitionId;
    }

    /**
     * Sets the value of the select parameter and returns the current object, to enable chaining further setters.
     *
     * @param select Limits the properties returned in the result.
     * @return {@link ParametersForGetLinkDefinition} The return value
     */
    public ParametersForGetLinkDefinition setSelect(String select) {
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
