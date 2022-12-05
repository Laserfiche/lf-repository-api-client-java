package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.PutLinksRequest;

import java.util.List;

public class ParametersForAssignEntryLinks {

    /**
     * The request repository ID.
     */
    private String repoId;

    /**
     * The requested entry ID.
     */
    private int entryId;

    private List<PutLinksRequest> requestBody;

    public ParametersForAssignEntryLinks setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    /**
     * Returns the value of 'repoId' field, which is the request repository ID.
     *
     * @return String The return value
     */
    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForAssignEntryLinks setEntryId(int entryId) {
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

    public ParametersForAssignEntryLinks setRequestBody(List<PutLinksRequest> requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public List<PutLinksRequest> getRequestBody() {
        return this.requestBody;
    }
}
