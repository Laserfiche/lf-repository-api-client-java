package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.GetDynamicFieldLogicValueRequest;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#getDynamicFieldValues(ParametersForGetDynamicFieldValues) getDynamicFieldValues}.
 */
public class ParametersForGetDynamicFieldValues {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested entry ID.
     */
    private int entryId;

    private GetDynamicFieldLogicValueRequest requestBody;

    /**
     * Builder for setting the repoId parameter.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForGetDynamicFieldValues} The return value
     */
    public ParametersForGetDynamicFieldValues setRepoId(String repoId) {
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
     * Builder for setting the entryId parameter.
     *
     * @param entryId The requested entry ID.
     * @return {@link ParametersForGetDynamicFieldValues} The return value
     */
    public ParametersForGetDynamicFieldValues setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The requested entry ID.
     *
     * @return {@link int} The return value
     */
    public int getEntryId() {
        return this.entryId;
    }

    public ParametersForGetDynamicFieldValues setRequestBody(GetDynamicFieldLogicValueRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public GetDynamicFieldLogicValueRequest getRequestBody() {
        return this.requestBody;
    }
}
