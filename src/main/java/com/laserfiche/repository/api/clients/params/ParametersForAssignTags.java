package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.PutTagRequest;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#assignTags(ParametersForAssignTags) assignTags}.
 */
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

    /**
     * Builder for setting the repoId parameter.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForAssignTags} The return value
     */
    public ParametersForAssignTags setRepoId(String repoId) {
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
     * Builder for setting the entryId parameter.
     *
     * @param entryId The requested entry ID.
     * @return {@link ParametersForAssignTags} The return value
     */
    public ParametersForAssignTags setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The requested entry ID.
     *
     * @return {@link int} The return value
     */
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
