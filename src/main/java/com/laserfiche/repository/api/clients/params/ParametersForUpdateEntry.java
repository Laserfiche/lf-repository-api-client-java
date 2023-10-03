package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.UpdateEntryRequest;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#updateEntry(ParametersForUpdateEntry) updateEntry}.
 */
public class ParametersForUpdateEntry {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * The requested entry ID.
     */
    private Integer entryId;

    private UpdateEntryRequest requestBody;

    /**
     * An optional query parameter used to indicate the locale that should be used. The value should be a standard language tag.
     */
    private String culture;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForUpdateEntry} The return value
     */
    public ParametersForUpdateEntry setRepositoryId(String repositoryId) {
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
     * @return {@link ParametersForUpdateEntry} The return value
     */
    public ParametersForUpdateEntry setEntryId(Integer entryId) {
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

    public ParametersForUpdateEntry setRequestBody(UpdateEntryRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public UpdateEntryRequest getRequestBody() {
        return this.requestBody;
    }

    /**
     * Sets the value of the culture parameter and returns the current object, to enable chaining further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used. The value should be a standard language tag.
     * @return {@link ParametersForUpdateEntry} The return value
     */
    public ParametersForUpdateEntry setCulture(String culture) {
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
