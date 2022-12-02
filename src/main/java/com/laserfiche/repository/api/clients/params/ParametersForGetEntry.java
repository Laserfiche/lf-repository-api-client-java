package com.laserfiche.repository.api.clients.params;

public class ParametersForGetEntry {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested entry ID.
     */
    private int entryId;

    /**
     * Limits the properties returned in the result.
     */
    private String select;

    public ParametersForGetEntry setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetEntry setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    public int getEntryId() {
        return this.entryId;
    }

    public ParametersForGetEntry setSelect(String select) {
        this.select = select;
        return this;
    }

    public String getSelect() {
        return this.select;
    }
}
