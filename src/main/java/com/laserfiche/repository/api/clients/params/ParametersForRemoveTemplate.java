// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#removeTemplate(ParametersForRemoveTemplate) removeTemplate}.
 */
public class ParametersForRemoveTemplate {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * The ID of the entry that will have its template removed.
     */
    private Integer entryId;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForRemoveTemplate} The return value
     */
    public ParametersForRemoveTemplate setRepositoryId(String repositoryId) {
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
     * @param entryId The ID of the entry that will have its template removed.
     * @return {@link ParametersForRemoveTemplate} The return value
     */
    public ParametersForRemoveTemplate setEntryId(Integer entryId) {
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
