package com.laserfiche.repository.api.clients.params;

public class ParametersForGetSearchStatus {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested searchToken.
     */
    private String searchToken;

    public ParametersForGetSearchStatus setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetSearchStatus setSearchToken(String searchToken) {
        this.searchToken = searchToken;
        return this;
    }

    public String getSearchToken() {
        return this.searchToken;
    }
}
