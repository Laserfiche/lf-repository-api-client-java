package com.laserfiche.repository.api.clients.params;

public class ParametersForGetDocumentContentType {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested document ID.
     */
    private int entryId;

    public ParametersForGetDocumentContentType setRepoId(String repoId) {
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

    public ParametersForGetDocumentContentType setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The requested document ID.
     *
     * @return int The return value
     */
    public int getEntryId() {
        return this.entryId;
    }
}
