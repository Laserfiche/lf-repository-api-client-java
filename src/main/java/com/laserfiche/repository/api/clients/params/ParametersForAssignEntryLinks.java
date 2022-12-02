package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForAssignEntryLinks {

    /**
     * The request repository ID.
     */
    String repoId;

    /**
     * The requested entry ID.
     */
    int entryId;

    List<PutLinksRequest> requestBody;

    public ParametersForAssignEntryLinks setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForAssignEntryLinks setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    public int getEntryId() {
        return this.entryId;
    }

    public ParametersForAssignEntryLinks setRequestBody(List<PutLinksRequest> requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public List<PutLinksRequest> getRequestBody() {
        return this.requestBody;
    }
}
