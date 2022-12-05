package com.laserfiche.repository.api.clients.params;

public class ParametersForDeletePages {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested document ID.
     */
    private int entryId;

    /**
     * The pages to be deleted.
     */
    private String pageRange;

    public ParametersForDeletePages setRepoId(String repoId) {
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

    public ParametersForDeletePages setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * Returns the value of 'entryId' field, which is the requested document ID.
     *
     * @return int The return value
     */
    public int getEntryId() {
        return this.entryId;
    }

    public ParametersForDeletePages setPageRange(String pageRange) {
        this.pageRange = pageRange;
        return this;
    }

    /**
     * Returns the value of 'pageRange' field, which is the pages to be deleted.
     *
     * @return String The return value
     */
    public String getPageRange() {
        return this.pageRange;
    }
}
