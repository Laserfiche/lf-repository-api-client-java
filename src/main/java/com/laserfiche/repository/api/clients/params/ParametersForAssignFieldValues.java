package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForAssignFieldValues {

    /**
     * The requested repository ID.
     */
    String repoId;

    /**
     * The entry ID of the entry that will have its fields updated.
     */
    int entryId;

    Map<String, FieldToUpdate> requestBody;

    /**
     * An optional query parameter used to indicate the locale that should be used.
     *            The value should be a standard language tag. This may be used when setting field values with tokens.
     */
    String culture;

    public ParametersForAssignFieldValues setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForAssignFieldValues setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    public int getEntryId() {
        return this.entryId;
    }

    public ParametersForAssignFieldValues setRequestBody(Map<String, FieldToUpdate> requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public Map<String, FieldToUpdate> getRequestBody() {
        return this.requestBody;
    }

    public ParametersForAssignFieldValues setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    public String getCulture() {
        return this.culture;
    }
}
