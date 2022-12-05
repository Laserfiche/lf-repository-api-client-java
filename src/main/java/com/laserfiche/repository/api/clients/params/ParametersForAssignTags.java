package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.PutTagRequest;

public class ParametersForAssignTags {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested entry ID.
     */
    private int entryId;

    private PutTagRequest requestBody;

    public ParametersForAssignTags setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForAssignTags setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    public int getEntryId() {
        return this.entryId;
    }

    public ParametersForAssignTags setRequestBody(PutTagRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public PutTagRequest getRequestBody() {
        return this.requestBody;
    }
}
