package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.GetDynamicFieldLogicValueRequest;

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

    public ParametersForGetDynamicFieldValues setRepoId(String repoId) {
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

    public ParametersForGetDynamicFieldValues setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The requested entry ID.
     *
     * @return int The return value
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
