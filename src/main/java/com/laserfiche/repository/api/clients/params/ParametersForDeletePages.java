package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#deletePages(ParametersForDeletePages) deletePages}.
 */
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

    /**
     * Builder for setting the repoId parameter.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForDeletePages} The return value
     */
    public ParametersForDeletePages setRepoId(String repoId) {
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
     * @param entryId The requested document ID.
     * @return {@link ParametersForDeletePages} The return value
     */
    public ParametersForDeletePages setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The requested document ID.
     *
     * @return {@link int} The return value
     */
    public int getEntryId() {
        return this.entryId;
    }

    /**
     * Builder for setting the pageRange parameter.
     *
     * @param pageRange The pages to be deleted.
     * @return {@link ParametersForDeletePages} The return value
     */
    public ParametersForDeletePages setPageRange(String pageRange) {
        this.pageRange = pageRange;
        return this;
    }

    /**
     * The pages to be deleted.
     *
     * @return {@link String} The return value
     */
    public String getPageRange() {
        return this.pageRange;
    }
}
