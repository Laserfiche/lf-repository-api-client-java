package com.laserfiche.repository.api.clients.params;

public class ParametersForDeleteAssignedTemplate {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The ID of the entry that will have its template removed.
     */
    private int entryId;

    public ParametersForDeleteAssignedTemplate setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    /**
     * The requested repository ID.
     *
     * @return String The return value
     */
    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForDeleteAssignedTemplate setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The ID of the entry that will have its template removed.
     *
     * @return int The return value
     */
    public int getEntryId() {
        return this.entryId;
    }
}
