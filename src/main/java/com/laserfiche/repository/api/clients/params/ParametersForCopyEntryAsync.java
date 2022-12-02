package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForCopyEntryAsync {

    /**
     * The requested repository ID.
     */
    String repoId;

    /**
     * The folder ID that the entry will be created in.
     */
    int entryId;

    CopyAsyncRequest requestBody;

    /**
     * An optional query parameter used to indicate if the new entry should be automatically
     *            renamed if an entry already exists with the given name in the folder. The default value is false.
     */
    boolean autoRename;

    /**
     * An optional query parameter used to indicate the locale that should be used.
     *            The value should be a standard language tag.
     */
    String culture;

    public ParametersForCopyEntryAsync setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForCopyEntryAsync setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

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

    public ParametersForCopyEntryAsync setAutoRename(boolean autoRename) {
        this.autoRename = autoRename;
        return this;
    }

    public boolean isAutoRename() {
        return this.autoRename;
    }

    public ParametersForCopyEntryAsync setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    public String getCulture() {
        return this.culture;
    }
}
