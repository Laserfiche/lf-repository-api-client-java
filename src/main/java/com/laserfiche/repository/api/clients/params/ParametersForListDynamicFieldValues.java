package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.ListDynamicFieldValuesRequest;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#listDynamicFieldValues(ParametersForListDynamicFieldValues) listDynamicFieldValues}.
 */
public class ParametersForListDynamicFieldValues {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * The requested entry ID.
     */
    private Integer entryId;

    private ListDynamicFieldValuesRequest requestBody;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForListDynamicFieldValues} The return value
     */
    public ParametersForListDynamicFieldValues setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
        return this;
    }

    /**
     * The requested repository ID.
     *
     * @return {@link String} The return value
     */
    public String getRepositoryId() {
        return this.repositoryId;
    }

    /**
     * Sets the value of the entryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param entryId The requested entry ID.
     * @return {@link ParametersForListDynamicFieldValues} The return value
     */
    public ParametersForListDynamicFieldValues setEntryId(Integer entryId) {
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

    public ParametersForListDynamicFieldValues setRequestBody(ListDynamicFieldValuesRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public ListDynamicFieldValuesRequest getRequestBody() {
        return this.requestBody;
    }
}
