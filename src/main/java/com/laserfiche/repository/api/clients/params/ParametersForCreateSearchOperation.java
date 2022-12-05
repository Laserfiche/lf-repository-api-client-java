package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.AdvancedSearchRequest;

public class ParametersForCreateSearchOperation {

    /**
     * The requested repository ID.
     */
    private String repoId;

    private AdvancedSearchRequest requestBody;

    public ParametersForCreateSearchOperation setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

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
