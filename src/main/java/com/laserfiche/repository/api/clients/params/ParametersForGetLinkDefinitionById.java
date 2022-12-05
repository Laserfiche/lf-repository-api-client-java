package com.laserfiche.repository.api.clients.params;

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

    public ParametersForGetLinkDefinitionById setRepoId(String repoId) {
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

    public ParametersForGetLinkDefinitionById setLinkTypeId(int linkTypeId) {
        this.linkTypeId = linkTypeId;
        return this;
    }

    /**
     * The requested link type ID.
     *
     * @return int The return value
     */
    public int getLinkTypeId() {
        return this.linkTypeId;
    }

    public ParametersForGetLinkDefinitionById setSelect(String select) {
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
