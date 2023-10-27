// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.StartCopyEntryRequest;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#startCopyEntry(ParametersForStartCopyEntry) startCopyEntry}.
 */
public class ParametersForStartCopyEntry {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * The folder ID that the entry will be created in.
     */
    private Integer entryId;

    private StartCopyEntryRequest requestBody;

    /**
     * An optional query parameter used to indicate the locale that should be used. The value should be a standard language tag.
     */
    private String culture;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForStartCopyEntry} The return value
     */
    public ParametersForStartCopyEntry setRepositoryId(String repositoryId) {
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
     * @param entryId The folder ID that the entry will be created in.
     * @return {@link ParametersForStartCopyEntry} The return value
     */
    public ParametersForStartCopyEntry setEntryId(Integer entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The folder ID that the entry will be created in.
     *
     * @return {@link Integer} The return value
     */
    public Integer getEntryId() {
        return this.entryId;
    }

    public ParametersForStartCopyEntry setRequestBody(StartCopyEntryRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public StartCopyEntryRequest getRequestBody() {
        return this.requestBody;
    }

    /**
     * Sets the value of the culture parameter and returns the current object, to enable chaining further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used. The value should be a standard language tag.
     * @return {@link ParametersForStartCopyEntry} The return value
     */
    public ParametersForStartCopyEntry setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used. The value should be a standard language tag.
     *
     * @return {@link String} The return value
     */
    public String getCulture() {
        return this.culture;
    }
}
