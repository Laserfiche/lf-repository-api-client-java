package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForGetDynamicFieldValues {

    /**
     * The requested repository ID.
     */
    String repoId;

    /**
     * The requested entry ID.
     */
    int entryId;

    GetDynamicFieldLogicValueRequest requestBody;

    public ParametersForGetDynamicFieldValues setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetDynamicFieldValues setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

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
