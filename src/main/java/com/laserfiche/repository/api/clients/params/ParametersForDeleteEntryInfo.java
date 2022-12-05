package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.DeleteEntryWithAuditReason;

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

    public ParametersForDeleteEntryInfo setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    /**
     * Returns the value of 'repoId' field, which is the requested repository ID.
     *
     * @return String The return value
     */
    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForDeleteEntryInfo setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * Returns the value of 'entryId' field, which is the requested entry ID.
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
