package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForCreateSearchOperation {

    /**
     * The requested repository ID.
     */
    String repoId;

    AdvancedSearchRequest requestBody;

    public ParametersForCreateSearchOperation setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForCreateSearchOperation setRequestBody(AdvancedSearchRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public AdvancedSearchRequest getRequestBody() {
        return this.requestBody;
    }
}
