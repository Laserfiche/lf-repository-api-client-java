package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#copyEntry(ParametersForCopyEntry) copyEntry}.
 */
public class ParametersForCopyEntry {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * The folder ID that the entry will be created in.
     */
    private Integer entryId;

    private CopyEntryRequest requestBody;

    /**
     * An optional query parameter used to indicate the locale that should be used. The value should be a standard language tag.
     */
    private String culture;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForCopyEntry} The return value
     */
    public ParametersForCopyEntry setRepositoryId(String repositoryId) {
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
     * @return {@link ParametersForCopyEntry} The return value
     */
    public ParametersForCopyEntry setEntryId(Integer entryId) {
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

    public ParametersForCopyEntry setRequestBody(CopyEntryRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public CopyEntryRequest getRequestBody() {
        return this.requestBody;
    }

    /**
     * Sets the value of the culture parameter and returns the current object, to enable chaining further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used. The value should be a standard language tag.
     * @return {@link ParametersForCopyEntry} The return value
     */
    public ParametersForCopyEntry setCulture(String culture) {
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
