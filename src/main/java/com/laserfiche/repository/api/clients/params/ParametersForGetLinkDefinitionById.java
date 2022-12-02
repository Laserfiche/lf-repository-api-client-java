package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForGetLinkDefinitionById {

    /**
     * The requested repository ID.
     */
    String repoId;

    /**
     * The requested link type ID.
     */
    int linkTypeId;

    /**
     * Limits the properties returned in the result.
     */
    String select;

    public ParametersForGetLinkDefinitionById setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetLinkDefinitionById setLinkTypeId(int linkTypeId) {
        this.linkTypeId = linkTypeId;
        return this;
    }

    public int getLinkTypeId() {
        return this.linkTypeId;
    }

    public ParametersForGetLinkDefinitionById setSelect(String select) {
        this.select = select;
        return this;
    }

    public String getSelect() {
        return this.select;
    }
}
