package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForDeleteEntryInfo {

    /**
     * The requested repository ID.
     */
    String repoId;

    /**
     * The requested entry ID.
     */
    int entryId;

    DeleteEntryWithAuditReason requestBody;

    public ParametersForDeleteEntryInfo setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForDeleteEntryInfo setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    public int getEntryId() {
        return this.entryId;
    }

    public ParametersForDeleteEntryInfo setRequestBody(DeleteEntryWithAuditReason requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public DeleteEntryWithAuditReason getRequestBody() {
        return this.requestBody;
    }
}
