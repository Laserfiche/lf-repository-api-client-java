package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;

/**
 * The encapsulated parameters for {@link
 * com.laserfiche.repository.api.clients.impl.EntriesClientImpl#deleteAssignedTemplate(ParametersForDeleteAssignedTemplate)
 * deleteAssignedTemplate}.
 */
public class ParametersForDeleteAssignedTemplate {

    /** The requested repository ID. */
    private String repoId;

    /** The ID of the entry that will have its template removed. */
    private Integer entryId;

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForDeleteAssignedTemplate} The return value
     */
    public ParametersForDeleteAssignedTemplate setRepoId(String repoId) {
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
     * Sets the value of the entryId parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param entryId The ID of the entry that will have its template removed.
     * @return {@link ParametersForDeleteAssignedTemplate} The return value
     */
    public ParametersForDeleteAssignedTemplate setEntryId(Integer entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The ID of the entry that will have its template removed.
     *
     * @return {@link Integer} The return value
     */
    public Integer getEntryId() {
        return this.entryId;
    }
}
