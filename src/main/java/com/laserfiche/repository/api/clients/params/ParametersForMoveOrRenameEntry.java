package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.PatchEntryRequest;

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

    public ParametersForMoveOrRenameEntry setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }


    /**
     * Returns the value of 'repoId' field, which is the requested repository ID.
     *
     * @return String The return value
     */
    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForMoveOrRenameEntry setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }


    /**
     * Returns the value of 'entryId' field, which is the requested entry ID.
     *
     * @return int The return value
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

    public ParametersForMoveOrRenameEntry setAutoRename(boolean autoRename) {
        this.autoRename = autoRename;
        return this;
    }


    /**
     * Returns the value of 'autoRename' field, which is an optional query parameter used to indicate if the entry should be automatically
     * renamed if another entry already exists with the same name in the folder. The default value is false.
     *
     * @return boolean The return value
     */
    public boolean isAutoRename() {
        return this.autoRename;
    }

    public ParametersForMoveOrRenameEntry setCulture(String culture) {
        this.culture = culture;
        return this;
    }


    /**
     * Returns the value of 'culture' field, which is an optional query parameter used to indicate the locale that should be used.
     * The value should be a standard language tag.
     *
     * @return String The return value
     */
    public String getCulture() {
        return this.culture;
    }
}
