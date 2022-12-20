package com.laserfiche.repository.api.clients.params;

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
    private int linkTypeId;

    /**
     * Limits the properties returned in the result.
     */
    private String select;

    /**
     * Builder for setting the repoId parameter.
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
     * Builder for setting the linkTypeId parameter.
     *
     * @param linkTypeId The requested link type ID.
     * @return {@link ParametersForGetLinkDefinitionById} The return value
     */
    public ParametersForGetLinkDefinitionById setLinkTypeId(int linkTypeId) {
        this.linkTypeId = linkTypeId;
        return this;
    }

    /**
     * The requested link type ID.
     *
     * @return {@link int} The return value
     */
    public int getLinkTypeId() {
        return this.linkTypeId;
    }

    /**
     * Builder for setting the select parameter.
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
