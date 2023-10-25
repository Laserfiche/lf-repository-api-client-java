// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.StartImportUploadedPartsRequest;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#startImportUploadedParts(ParametersForStartImportUploadedParts) startImportUploadedParts}.
 */
public class ParametersForStartImportUploadedParts {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * The entry ID of the folder that the document will be created in.
     */
    private Integer entryId;

    private StartImportUploadedPartsRequest requestBody;

    /**
     * An optional query parameter used to indicate the locale that should be used. The value should be a standard language tag. This may be used when setting field values with tokens.
     */
    private String culture;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForStartImportUploadedParts} The return value
     */
    public ParametersForStartImportUploadedParts setRepositoryId(String repositoryId) {
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
     * @param entryId The entry ID of the folder that the document will be created in.
     * @return {@link ParametersForStartImportUploadedParts} The return value
     */
    public ParametersForStartImportUploadedParts setEntryId(Integer entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The entry ID of the folder that the document will be created in.
     *
     * @return {@link Integer} The return value
     */
    public Integer getEntryId() {
        return this.entryId;
    }

    public ParametersForStartImportUploadedParts setRequestBody(StartImportUploadedPartsRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public StartImportUploadedPartsRequest getRequestBody() {
        return this.requestBody;
    }

    /**
     * Sets the value of the culture parameter and returns the current object, to enable chaining further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used. The value should be a standard language tag. This may be used when setting field values with tokens.
     * @return {@link ParametersForStartImportUploadedParts} The return value
     */
    public ParametersForStartImportUploadedParts setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used. The value should be a standard language tag. This may be used when setting field values with tokens.
     *
     * @return {@link String} The return value
     */
    public String getCulture() {
        return this.culture;
    }
}
