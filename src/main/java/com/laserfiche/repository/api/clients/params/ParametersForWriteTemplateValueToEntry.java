package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForWriteTemplateValueToEntry {

    /**
     * The requested repository ID.
     */
    String repoId;

    /**
     * The ID of entry that will have its template updated.
     */
    int entryId;

    PutTemplateRequest requestBody;

    /**
     * An optional query parameter used to indicate the locale that should be used.
     *            The value should be a standard language tag. This may be used when setting field values with tokens.
     */
    String culture;

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
