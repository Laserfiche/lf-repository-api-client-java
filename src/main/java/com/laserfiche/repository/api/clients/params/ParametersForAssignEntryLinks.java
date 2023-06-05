package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.PutLinksRequest;
import java.util.List;

/**
 * The encapsulated parameters for {@link
 * com.laserfiche.repository.api.clients.impl.EntriesClientImpl#assignEntryLinks(ParametersForAssignEntryLinks)
 * assignEntryLinks}.
 */
public class ParametersForAssignEntryLinks {

    /** The request repository ID. */
    private String repoId;

    /** The requested entry ID. */
    private Integer entryId;

    private List<PutLinksRequest> requestBody;

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param repoId The request repository ID.
     * @return {@link ParametersForAssignEntryLinks} The return value
     */
    public ParametersForAssignEntryLinks setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    /**
     * The request repository ID.
     *
     * @return {@link String} The return value
     */
    public String getRepoId() {
        return this.repoId;
    }

    /**
     * Sets the value of the entryId parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param entryId The requested entry ID.
     * @return {@link ParametersForAssignEntryLinks} The return value
     */
    public ParametersForAssignEntryLinks setEntryId(Integer entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The requested entry ID.
     *
     * @return {@link Integer} The return value
     */
    public Integer getEntryId() {
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
