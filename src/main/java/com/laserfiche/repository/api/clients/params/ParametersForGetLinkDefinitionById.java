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

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetLinkDefinitionById setLinkTypeId(int linkTypeId) {
        this.linkTypeId = linkTypeId;
        return this;
    }

    public int getLinkTypeId() {
        return this.linkTypeId;
    }

    public ParametersForGetLinkDefinitionById setSelect(String select) {
        this.select = select;
        return this;
    }

    public String getSelect() {
        return this.select;
    }
}
