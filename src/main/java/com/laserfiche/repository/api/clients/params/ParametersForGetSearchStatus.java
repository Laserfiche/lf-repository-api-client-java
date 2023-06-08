package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;

/**
 * The encapsulated parameters for {@link
 * com.laserfiche.repository.api.clients.impl.SearchesClientImpl#getSearchStatus(ParametersForGetSearchStatus)
 * getSearchStatus}.
 */
public class ParametersForGetSearchStatus {

    /** The requested repository ID. */
    private String repoId;

    /** The requested searchToken. */
    private String searchToken;

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForGetSearchStatus} The return value
     */
    public ParametersForGetSearchStatus setRepoId(String repoId) {
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
     * Sets the value of the searchToken parameter and returns the current object, to enable
     * chaining further setters.
     *
     * @param searchToken The requested searchToken.
     * @return {@link ParametersForGetSearchStatus} The return value
     */
    public ParametersForGetSearchStatus setSearchToken(String searchToken) {
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
