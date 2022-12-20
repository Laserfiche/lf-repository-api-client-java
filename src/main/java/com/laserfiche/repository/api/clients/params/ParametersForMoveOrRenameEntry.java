package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.PatchEntryRequest;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#moveOrRenameEntry(ParametersForMoveOrRenameEntry) moveOrRenameEntry}.
 */
public class ParametersForMoveOrRenameEntry {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested entry ID.
     */
    private int entryId;

    private PatchEntryRequest requestBody;

    /**
     * An optional query parameter used to indicate if the entry should be automatically
     * renamed if another entry already exists with the same name in the folder. The default value is false.
     */
    private boolean autoRename;

    /**
     * An optional query parameter used to indicate the locale that should be used.
     * The value should be a standard language tag.
     */
    private String culture;

    /**
     * Builder for setting the repoId parameter.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForMoveOrRenameEntry} The return value
     */
    public ParametersForMoveOrRenameEntry setRepoId(String repoId) {
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
     * @param entryId The requested entry ID.
     * @return {@link ParametersForMoveOrRenameEntry} The return value
     */
    public ParametersForMoveOrRenameEntry setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The requested entry ID.
     *
     * @return {@link int} The return value
     */
    public int getEntryId() {
        return this.entryId;
    }

    public ParametersForMoveOrRenameEntry setRequestBody(PatchEntryRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public PatchEntryRequest getRequestBody() {
        return this.requestBody;
    }

    /**
     * Builder for setting the autoRename parameter.
     *
     * @param autoRename An optional query parameter used to indicate if the entry should be automatically
     *                   renamed if another entry already exists with the same name in the folder. The default value is false.
     * @return {@link ParametersForMoveOrRenameEntry} The return value
     */
    public ParametersForMoveOrRenameEntry setAutoRename(boolean autoRename) {
        this.autoRename = autoRename;
        return this;
    }

    /**
     * An optional query parameter used to indicate if the entry should be automatically
     * renamed if another entry already exists with the same name in the folder. The default value is false.
     *
     * @return {@link boolean} The return value
     */
    public boolean isAutoRename() {
        return this.autoRename;
    }

    /**
     * Builder for setting the culture parameter.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used.
     *                The value should be a standard language tag.
     * @return {@link ParametersForMoveOrRenameEntry} The return value
     */
    public ParametersForMoveOrRenameEntry setCulture(String culture) {
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
