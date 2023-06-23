package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.util.Map;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#assignFieldValues(ParametersForAssignFieldValues) assignFieldValues}.
 */
public class ParametersForAssignFieldValues {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The entry ID of the entry that will have its fields updated.
     */
    private Integer entryId;

    private Map<String, FieldToUpdate> requestBody;

    /**
     * An optional query parameter used to indicate the locale that should be used.
     *             The value should be a standard language tag. This may be used when setting field values with tokens.
     */
    private String culture;

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForAssignFieldValues} The return value
     */
    public ParametersForAssignFieldValues setRepoId(String repoId) {
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
     * @param entryId The entry ID of the entry that will have its fields updated.
     * @return {@link ParametersForAssignFieldValues} The return value
     */
    public ParametersForAssignFieldValues setEntryId(Integer entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The entry ID of the entry that will have its fields updated.
     *
     * @return {@link Integer} The return value
     */
    public Integer getEntryId() {
        return this.entryId;
    }

    public ParametersForAssignFieldValues setRequestBody(Map<String, FieldToUpdate> requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public Map<String, FieldToUpdate> getRequestBody() {
        return this.requestBody;
    }

    /**
     * Sets the value of the culture parameter and returns the current object, to enable chaining further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used.
     *             The value should be a standard language tag. This may be used when setting field values with tokens.
     * @return {@link ParametersForAssignFieldValues} The return value
     */
    public ParametersForAssignFieldValues setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used.<br>            The value should be a standard language tag. This may be used when setting field values with tokens.
     *
     * @return {@link String} The return value
     */
    public String getCulture() {
        return this.culture;
    }
}
