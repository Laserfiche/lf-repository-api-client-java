// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.StartExportEntryRequest;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#startExportEntry(ParametersForStartExportEntry) startExportEntry}.
 */
public class ParametersForStartExportEntry {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * The ID of entry to export.
     */
    private Integer entryId;

    /**
     * A comma-separated range of pages to include. Ex: 1,3,4 or 1-3,5-7,9. This value is ignored when part=Edoc.
     */
    private String pageRange;

    private StartExportEntryRequest requestBody;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForStartExportEntry} The return value
     */
    public ParametersForStartExportEntry setRepositoryId(String repositoryId) {
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
     * @param entryId The ID of entry to export.
     * @return {@link ParametersForStartExportEntry} The return value
     */
    public ParametersForStartExportEntry setEntryId(Integer entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The ID of entry to export.
     *
     * @return {@link Integer} The return value
     */
    public Integer getEntryId() {
        return this.entryId;
    }

    /**
     * Sets the value of the pageRange parameter and returns the current object, to enable chaining further setters.
     *
     * @param pageRange A comma-separated range of pages to include. Ex: 1,3,4 or 1-3,5-7,9. This value is ignored when part=Edoc.
     * @return {@link ParametersForStartExportEntry} The return value
     */
    public ParametersForStartExportEntry setPageRange(String pageRange) {
        this.pageRange = pageRange;
        return this;
    }

    /**
     * A comma-separated range of pages to include. Ex: 1,3,4 or 1-3,5-7,9. This value is ignored when part=Edoc.
     *
     * @return {@link String} The return value
     */
    public String getPageRange() {
        return this.pageRange;
    }

    public ParametersForStartExportEntry setRequestBody(StartExportEntryRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public StartExportEntryRequest getRequestBody() {
        return this.requestBody;
    }
}
