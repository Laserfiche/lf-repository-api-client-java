package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForMoveOrRenameEntry {

    /**
     * The requested repository ID.
     */
    String repoId;

    /**
     * The requested entry ID.
     */
    int entryId;

    PatchEntryRequest requestBody;

    /**
     * An optional query parameter used to indicate if the entry should be automatically
     *            renamed if another entry already exists with the same name in the folder. The default value is false.
     */
    boolean autoRename;

    /**
     * An optional query parameter used to indicate the locale that should be used.
     *            The value should be a standard language tag.
     */
    String culture;

    public ParametersForMoveOrRenameEntry setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForMoveOrRenameEntry setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

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

    public boolean isAutoRename() {
        return this.autoRename;
    }

    public ParametersForMoveOrRenameEntry setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    public String getCulture() {
        return this.culture;
    }
}
