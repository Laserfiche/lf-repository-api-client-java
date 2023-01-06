package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.SearchesClientImpl#cancelOrCloseSearch(ParametersForCancelOrCloseSearch) cancelOrCloseSearch}.
 */
public class ParametersForCancelOrCloseSearch {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested searchToken.
     */
    private String searchToken;

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForCancelOrCloseSearch} The return value
     */
    public ParametersForCancelOrCloseSearch setRepoId(String repoId) {
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
     * Sets the value of the searchToken parameter and returns the current object, to enable chaining further setters.
     *
     * @param searchToken The requested searchToken.
     * @return {@link ParametersForCancelOrCloseSearch} The return value
     */
    public ParametersForCancelOrCloseSearch setSearchToken(String searchToken) {
        this.searchToken = searchToken;
        return this;
    }

    /**
     * The requested searchToken.
     *
     * @return {@link String} The return value
     */
    public String getSearchToken() {
        return this.searchToken;
    }
}
