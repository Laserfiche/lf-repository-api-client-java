package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#deletePages(ParametersForDeletePages) deletePages}.
 */
public class ParametersForDeletePages {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * The requested document ID.
     */
    private Integer entryId;

    /**
     * The pages to be deleted.
     */
    private String pageRange;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForDeletePages} The return value
     */
    public ParametersForDeletePages setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
        return this;
    }

    /**
     * The requested repository ID.
     *
     * @return {@link String} The return value
     */
    public String getRepositoryId() {
        return this.repositoryId;
    }

    /**
     * Sets the value of the entryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param entryId The requested document ID.
     * @return {@link ParametersForDeletePages} The return value
     */
    public ParametersForDeletePages setEntryId(Integer entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The requested document ID.
     *
     * @return {@link Integer} The return value
     */
    public Integer getEntryId() {
        return this.entryId;
    }

    /**
     * Sets the value of the pageRange parameter and returns the current object, to enable chaining further setters.
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
