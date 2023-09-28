package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#setTemplate(ParametersForSetTemplate) setTemplate}.
 */
public class ParametersForSetTemplate {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * The ID of entry that will have its template updated.
     */
    private Integer entryId;

    private SetTemplateRequest requestBody;

    /**
     * An optional query parameter used to indicate the locale that should be used. The value should be a standard language tag. This may be used when setting field values with tokens.
     */
    private String culture;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForSetTemplate} The return value
     */
    public ParametersForSetTemplate setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
        return this;
    }

    /**
     * The requested repository ID.
     *
     * @return {@link String} The return value
     */
    public String getRepositoryId() {
        return this.repositoryId;
    }

    /**
     * Sets the value of the entryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param entryId The ID of entry that will have its template updated.
     * @return {@link ParametersForSetTemplate} The return value
     */
    public ParametersForSetTemplate setEntryId(Integer entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The ID of entry that will have its template updated.
     *
     * @return {@link Integer} The return value
     */
    public Integer getEntryId() {
        return this.entryId;
    }

    public ParametersForSetTemplate setRequestBody(SetTemplateRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public SetTemplateRequest getRequestBody() {
        return this.requestBody;
    }

    /**
     * Sets the value of the culture parameter and returns the current object, to enable chaining further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used. The value should be a standard language tag. This may be used when setting field values with tokens.
     * @return {@link ParametersForSetTemplate} The return value
     */
    public ParametersForSetTemplate setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used. The value should be a standard language tag. This may be used when setting field values with tokens.
     *
     * @return {@link String} The return value
     */
    public String getCulture() {
        return this.culture;
    }
}
