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

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForCreateOrCopyEntry setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

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

    public boolean isAutoRename() {
        return this.autoRename;
    }

    public ParametersForCreateOrCopyEntry setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    public String getCulture() {
        return this.culture;
    }
}
