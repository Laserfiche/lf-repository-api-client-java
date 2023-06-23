package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;

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
    private Integer entryId;

    private PatchEntryRequest requestBody;

    /**
     * An optional query parameter used to indicate if the entry should be automatically
     *             renamed if another entry already exists with the same name in the folder. The default value is false.
     */
    private Boolean autoRename;

    /**
     * An optional query parameter used to indicate the locale that should be used.
     *             The value should be a standard language tag.
     */
    private String culture;

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining further setters.
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
     * Sets the value of the entryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param entryId The requested entry ID.
     * @return {@link ParametersForMoveOrRenameEntry} The return value
     */
    public ParametersForMoveOrRenameEntry setEntryId(Integer entryId) {
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

    public ParametersForMoveOrRenameEntry setRequestBody(PatchEntryRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public PatchEntryRequest getRequestBody() {
        return this.requestBody;
    }

    /**
     * Sets the value of the autoRename parameter and returns the current object, to enable chaining further setters.
     *
     * @param autoRename An optional query parameter used to indicate if the entry should be automatically
     *             renamed if another entry already exists with the same name in the folder. The default value is false.
     * @return {@link ParametersForMoveOrRenameEntry} The return value
     */
    public ParametersForMoveOrRenameEntry setAutoRename(Boolean autoRename) {
        this.autoRename = autoRename;
        return this;
    }

    /**
     * An optional query parameter used to indicate if the entry should be automatically<br>            renamed if another entry already exists with the same name in the folder. The default value is false.
     *
     * @return {@link Boolean} The return value
     */
    public Boolean isAutoRename() {
        return this.autoRename;
    }

    /**
     * Sets the value of the culture parameter and returns the current object, to enable chaining further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used.
     *             The value should be a standard language tag.
     * @return {@link ParametersForMoveOrRenameEntry} The return value
     */
    public ParametersForMoveOrRenameEntry setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used.<br>            The value should be a standard language tag.
     *
     * @return {@link String} The return value
     */
    public String getCulture() {
        return this.culture;
    }
}
