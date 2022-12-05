package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.FieldToUpdate;

import java.util.Map;

public class ParametersForAssignFieldValues {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The entry ID of the entry that will have its fields updated.
     */
    private int entryId;

    private Map<String, FieldToUpdate> requestBody;

    /**
     * An optional query parameter used to indicate the locale that should be used.
     * The value should be a standard language tag. This may be used when setting field values with tokens.
     */
    private String culture;

    public ParametersForAssignFieldValues setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    /**
     * The requested repository ID.
     *
     * @return String The return value
     */
    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForAssignFieldValues setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The entry ID of the entry that will have its fields updated.
     *
     * @return int The return value
     */
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

    /**
     * An optional query parameter used to indicate the locale that should be used.
     * The value should be a standard language tag. This may be used when setting field values with tokens.
     *
     * @return String The return value
     */
    public String getCulture() {
        return this.culture;
    }
}
