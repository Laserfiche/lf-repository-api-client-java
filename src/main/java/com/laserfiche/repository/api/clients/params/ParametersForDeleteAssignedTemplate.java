package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#deleteAssignedTemplate(ParametersForDeleteAssignedTemplate) deleteAssignedTemplate}.
 */
public class ParametersForDeleteAssignedTemplate {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The ID of the entry that will have its template removed.
     */
    private int entryId;

    /**
     * Builder for setting the repoId parameter.
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
     * Builder for setting the entryId parameter.
     *
     * @param entryId The ID of the entry that will have its template removed.
     * @return {@link ParametersForDeleteAssignedTemplate} The return value
     */
    public ParametersForDeleteAssignedTemplate setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The ID of the entry that will have its template removed.
     *
     * @return {@link int} The return value
     */
    public int getEntryId() {
        return this.entryId;
    }
}
