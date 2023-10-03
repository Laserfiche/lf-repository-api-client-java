package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.SearchesClientImpl#startSearchEntry(ParametersForStartSearchEntry) startSearchEntry}.
 */
public class ParametersForStartSearchEntry {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    private StartSearchEntryRequest requestBody;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForStartSearchEntry} The return value
     */
    public ParametersForStartSearchEntry setRepositoryId(String repositoryId) {
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

    public ParametersForStartSearchEntry setRequestBody(StartSearchEntryRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public StartSearchEntryRequest getRequestBody() {
        return this.requestBody;
    }
}
