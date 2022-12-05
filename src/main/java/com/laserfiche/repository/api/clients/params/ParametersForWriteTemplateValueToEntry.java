package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.PutTemplateRequest;

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

    public ParametersForWriteTemplateValueToEntry setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForWriteTemplateValueToEntry setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

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

    public ParametersForWriteTemplateValueToEntry setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    public String getCulture() {
        return this.culture;
    }
}
