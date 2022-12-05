package com.laserfiche.repository.api.clients.params;

public class ParametersForCancelOrCloseSearch {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested searchToken.
     */
    private String searchToken;

    public ParametersForCancelOrCloseSearch setRepoId(String repoId) {
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

    public ParametersForCancelOrCloseSearch setSearchToken(String searchToken) {
        this.searchToken = searchToken;
        return this;
    }

    /**
     * The requested searchToken.
     *
     * @return String The return value
     */
    public String getSearchToken() {
        return this.searchToken;
    }
}
