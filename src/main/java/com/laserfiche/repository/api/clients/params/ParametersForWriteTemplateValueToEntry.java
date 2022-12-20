package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.PutTemplateRequest;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#writeTemplateValueToEntry(ParametersForWriteTemplateValueToEntry) writeTemplateValueToEntry}.
 */
public class ParametersForWriteTemplateValueToEntry {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The ID of entry that will have its template updated.
     */
    private int entryId;

    private PutTemplateRequest requestBody;

    /**
     * An optional query parameter used to indicate the locale that should be used.
     * The value should be a standard language tag. This may be used when setting field values with tokens.
     */
    private String culture;

    /**
     * Builder for setting the repoId parameter.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForWriteTemplateValueToEntry} The return value
     */
    public ParametersForWriteTemplateValueToEntry setRepoId(String repoId) {
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
     * @param entryId The ID of entry that will have its template updated.
     * @return {@link ParametersForWriteTemplateValueToEntry} The return value
     */
    public ParametersForWriteTemplateValueToEntry setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The ID of entry that will have its template updated.
     *
     * @return {@link int} The return value
     */
    public int getEntryId() {
        return this.entryId;
    }

    public ParametersForWriteTemplateValueToEntry setRequestBody(PutTemplateRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public PutTemplateRequest getRequestBody() {
        return this.requestBody;
    }

    /**
     * Builder for setting the culture parameter.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used.
     *                The value should be a standard language tag. This may be used when setting field values with tokens.
     * @return {@link ParametersForWriteTemplateValueToEntry} The return value
     */
    public ParametersForWriteTemplateValueToEntry setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used.
     * The value should be a standard language tag. This may be used when setting field values with tokens.
     *
     * @return {@link String} The return value
     */
    public String getCulture() {
        return this.culture;
    }
}
