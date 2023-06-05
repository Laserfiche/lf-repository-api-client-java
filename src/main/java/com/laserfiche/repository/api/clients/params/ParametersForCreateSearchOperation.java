package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.SearchesClientImpl#createSearchOperation(ParametersForCreateSearchOperation) createSearchOperation}.
 */
public class ParametersForCreateSearchOperation {

    /**
     * The requested repository ID.
     */
    private String repoId;

    private AdvancedSearchRequest requestBody;

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForCreateSearchOperation} The return value
     */
    public ParametersForCreateSearchOperation setRepoId(String repoId) {
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

    public ParametersForCreateSearchOperation setRequestBody(AdvancedSearchRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public AdvancedSearchRequest getRequestBody() {
        return this.requestBody;
    }
}
