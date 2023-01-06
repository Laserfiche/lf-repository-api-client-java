package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.DeleteEntryWithAuditReason;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#deleteEntryInfo(ParametersForDeleteEntryInfo) deleteEntryInfo}.
 */
public class ParametersForDeleteEntryInfo {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested entry ID.
     */
    private int entryId;

    private DeleteEntryWithAuditReason requestBody;

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForDeleteEntryInfo} The return value
     */
    public ParametersForDeleteEntryInfo setRepoId(String repoId) {
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
     * Sets the value of the entryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param entryId The requested entry ID.
     * @return {@link ParametersForDeleteEntryInfo} The return value
     */
    public ParametersForDeleteEntryInfo setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The requested entry ID.
     *
     * @return int The return value
     */
    public int getEntryId() {
        return this.entryId;
    }

    public ParametersForDeleteEntryInfo setRequestBody(DeleteEntryWithAuditReason requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public DeleteEntryWithAuditReason getRequestBody() {
        return this.requestBody;
    }
}
