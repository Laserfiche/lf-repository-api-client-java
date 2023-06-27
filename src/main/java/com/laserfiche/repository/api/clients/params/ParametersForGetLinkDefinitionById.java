package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.LinkDefinitionsClientImpl#getLinkDefinitionById(ParametersForGetLinkDefinitionById) getLinkDefinitionById}.
 */
public class ParametersForGetLinkDefinitionById {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested link type ID.
     */
    private Integer linkTypeId;

    /**
     * Limits the properties returned in the result.
     */
    private String select;

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForGetLinkDefinitionById} The return value
     */
    public ParametersForGetLinkDefinitionById setRepoId(String repoId) {
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
     * Sets the value of the linkTypeId parameter and returns the current object, to enable chaining further setters.
     *
     * @param linkTypeId The requested link type ID.
     * @return {@link ParametersForGetLinkDefinitionById} The return value
     */
    public ParametersForGetLinkDefinitionById setLinkTypeId(Integer linkTypeId) {
        this.linkTypeId = linkTypeId;
        return this;
    }

    /**
     * The requested link type ID.
     *
     * @return {@link Integer} The return value
     */
    public Integer getLinkTypeId() {
        return this.linkTypeId;
    }

    /**
     * Sets the value of the select parameter and returns the current object, to enable chaining further setters.
     *
     * @param select Limits the properties returned in the result.
     * @return {@link ParametersForGetLinkDefinitionById} The return value
     */
    public ParametersForGetLinkDefinitionById setSelect(String select) {
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
