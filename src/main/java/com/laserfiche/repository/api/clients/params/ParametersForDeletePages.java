package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForDeletePages {

    /**
     * The requested repository ID.
     */
    String repoId;

    /**
     * The requested document ID.
     */
    int entryId;

    /**
     * The pages to be deleted.
     */
    String pageRange;

    public ParametersForDeletePages setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForDeletePages setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    public int getEntryId() {
        return this.entryId;
    }

    public ParametersForDeletePages setPageRange(String pageRange) {
        this.pageRange = pageRange;
        return this;
    }

    public String getPageRange() {
        return this.pageRange;
    }
}
