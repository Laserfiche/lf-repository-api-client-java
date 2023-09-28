package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#setLinks(ParametersForSetLinks) setLinks}.
 */
public class ParametersForSetLinks {

    /**
     * The request repository ID.
     */
    private String repositoryId;

    /**
     * The requested entry ID.
     */
    private Integer entryId;

    private SetLinksRequest requestBody;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The request repository ID.
     * @return {@link ParametersForSetLinks} The return value
     */
    public ParametersForSetLinks setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
        return this;
    }

    /**
     * The request repository ID.
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
     * @return {@link ParametersForSetLinks} The return value
     */
    public ParametersForSetLinks setEntryId(Integer entryId) {
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

    public ParametersForSetLinks setRequestBody(SetLinksRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public SetLinksRequest getRequestBody() {
        return this.requestBody;
    }
}
