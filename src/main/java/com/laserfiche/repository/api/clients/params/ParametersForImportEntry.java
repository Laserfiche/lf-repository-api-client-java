package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#importEntry(ParametersForImportEntry) importEntry}.
 */
public class ParametersForImportEntry {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * The entry ID of the folder that the document will be created in.
     */
    private Integer entryId;

    /**
     * An optional query parameter used to indicate the locale that should be used. The value should be a standard language tag. This may be used when setting field values with tokens.
     */
    private String culture;

    /**
     * The content type of the file.
     */
    private String contentType;

    /**
     * An InputStream object to read the raw bytes for the file to be uploaded.
     */
    private InputStream inputStream;

    private ImportEntryRequest requestBody;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForImportEntry} The return value
     */
    public ParametersForImportEntry setRepositoryId(String repositoryId) {
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
     * @param entryId The entry ID of the folder that the document will be created in.
     * @return {@link ParametersForImportEntry} The return value
     */
    public ParametersForImportEntry setEntryId(Integer entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The entry ID of the folder that the document will be created in.
     *
     * @return {@link Integer} The return value
     */
    public Integer getEntryId() {
        return this.entryId;
    }

    /**
     * Sets the value of the culture parameter and returns the current object, to enable chaining further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used. The value should be a standard language tag. This may be used when setting field values with tokens.
     * @return {@link ParametersForImportEntry} The return value
     */
    public ParametersForImportEntry setCulture(String culture) {
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

    /**
     * Sets the value of the contentType parameter and returns the current object, to enable chaining further setters.
     *
     * @return {@link ParametersForImportEntry} The return value
     */
    public ParametersForImportEntry setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    /**
     * The content type of the file.
     *
     * @return {@link String} The return value
     */
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Sets the value of the inputStream parameter and returns the current object, to enable chaining further setters.
     *
     * @return {@link ParametersForImportEntry} The return value
     */
    public ParametersForImportEntry setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    /**
     * An InputStream object to read the raw bytes for the file to be uploaded.
     *
     * @return {@link InputStream} The return value
     */
    public InputStream getInputStream() {
        return this.inputStream;
    }

    public ParametersForImportEntry setRequestBody(ImportEntryRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public ImportEntryRequest getRequestBody() {
        return this.requestBody;
    }
}
