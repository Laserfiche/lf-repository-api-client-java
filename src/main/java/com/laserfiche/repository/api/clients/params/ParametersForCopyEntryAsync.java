package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.CopyAsyncRequest;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#copyEntryAsync(ParametersForCopyEntryAsync) copyEntryAsync}.
 */
public class ParametersForCopyEntryAsync {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The folder ID that the entry will be created in.
     */
    private int entryId;

    private CopyAsyncRequest requestBody;

    /**
     * An optional query parameter used to indicate if the new entry should be automatically
     * renamed if an entry already exists with the given name in the folder. The default value is false.
     */
    private boolean autoRename;

    /**
     * An optional query parameter used to indicate the locale that should be used.
     * The value should be a standard language tag.
     */
    private String culture;

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForCopyEntryAsync} The return value
     */
    public ParametersForCopyEntryAsync setRepoId(String repoId) {
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
     * Sets the value of the entryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param entryId The folder ID that the entry will be created in.
     * @return {@link ParametersForCopyEntryAsync} The return value
     */
    public ParametersForCopyEntryAsync setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The folder ID that the entry will be created in.
     *
     * @return int The return value
     */
    public int getEntryId() {
        return this.entryId;
    }

    public ParametersForCopyEntryAsync setRequestBody(CopyAsyncRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public CopyAsyncRequest getRequestBody() {
        return this.requestBody;
    }

    /**
     * Sets the value of the autoRename parameter and returns the current object, to enable chaining further setters.
     *
     * @param autoRename An optional query parameter used to indicate if the new entry should be automatically
     *                   renamed if an entry already exists with the given name in the folder. The default value is false.
     * @return {@link ParametersForCopyEntryAsync} The return value
     */
    public ParametersForCopyEntryAsync setAutoRename(boolean autoRename) {
        this.autoRename = autoRename;
        return this;
    }

    /**
     * An optional query parameter used to indicate if the new entry should be automatically
     * renamed if an entry already exists with the given name in the folder. The default value is false.
     *
     * @return boolean The return value
     */
    public boolean isAutoRename() {
        return this.autoRename;
    }

    /**
     * Sets the value of the culture parameter and returns the current object, to enable chaining further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used.
     *                The value should be a standard language tag.
     * @return {@link ParametersForCopyEntryAsync} The return value
     */
    public ParametersForCopyEntryAsync setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used.
     * The value should be a standard language tag.
     *
     * @return {@link String} The return value
     */
    public String getCulture() {
        return this.culture;
    }
}
