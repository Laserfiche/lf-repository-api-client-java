package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForGetSearchStatus {

    /**
     * The requested repository ID.
     */
    String repoId;

    /**
     * The requested searchToken.
     */
    String searchToken;

    public ParametersForGetSearchStatus setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetSearchStatus setSearchToken(String searchToken) {
        this.searchToken = searchToken;
        return this;
    }

    public String getSearchToken() {
        return this.searchToken;
    }
}
