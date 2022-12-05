package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.PostEntryChildrenRequest;

public class ParametersForCreateOrCopyEntry {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The folder ID that the entry will be created in.
     */
    private int entryId;

    private PostEntryChildrenRequest requestBody;

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

    public ParametersForCreateOrCopyEntry setRepoId(String repoId) {
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

    public ParametersForCreateOrCopyEntry setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * Returns the value of 'entryId' field, which is the folder ID that the entry will be created in.
     *
     * @return int The return value
     */
    public int getEntryId() {
        return this.entryId;
    }

    public ParametersForCreateOrCopyEntry setRequestBody(PostEntryChildrenRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public PostEntryChildrenRequest getRequestBody() {
        return this.requestBody;
    }

    public ParametersForCreateOrCopyEntry setAutoRename(boolean autoRename) {
        this.autoRename = autoRename;
        return this;
    }

    /**
     * Returns the value of 'autoRename' field, which is an optional query parameter used to indicate if the new entry should be automatically
     * renamed if an entry already exists with the given name in the folder. The default value is false.
     *
     * @return boolean The return value
     */
    public boolean isAutoRename() {
        return this.autoRename;
    }

    public ParametersForCreateOrCopyEntry setCulture(String culture) {
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
