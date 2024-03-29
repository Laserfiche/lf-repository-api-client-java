// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.SetTagsRequest;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#setTags(ParametersForSetTags) setTags}.
 */
public class ParametersForSetTags {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * The requested entry ID.
     */
    private Integer entryId;

    private SetTagsRequest requestBody;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForSetTags} The return value
     */
    public ParametersForSetTags setRepositoryId(String repositoryId) {
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
     * @param entryId The requested entry ID.
     * @return {@link ParametersForSetTags} The return value
     */
    public ParametersForSetTags setEntryId(Integer entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The requested entry ID.
     *
     * @return {@link Integer} The return value
     */
    public Integer getEntryId() {
        return this.entryId;
    }

    public ParametersForSetTags setRequestBody(SetTagsRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public SetTagsRequest getRequestBody() {
        return this.requestBody;
    }
}
