package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.PostEntryWithEdocMetadataRequest;

import java.io.InputStream;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#importDocument(ParametersForImportDocument) importDocument}.
 */
public class ParametersForImportDocument {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The entry ID of the folder that the document will be created in.
     */
    private int parentEntryId;

    /**
     * The created document's file name.
     */
    private String fileName;

    /**
     * An optional query parameter used to indicate if the new document should be automatically
     * renamed if an entry already exists with the given name in the folder. The default value is false.
     */
    private boolean autoRename;

    /**
     * An optional query parameter used to indicate the locale that should be used.
     * The value should be a standard language tag. This may be used when setting field values with tokens.
     */
    private String culture;

    private InputStream inputStream;

    private PostEntryWithEdocMetadataRequest requestBody;

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForImportDocument} The return value
     */
    public ParametersForImportDocument setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    /**
     * The requested repository ID.
     *
     * @return {@link String} The return value
     */
    public String getRepoId() {
        return this.repoId;
    }

    /**
     * Sets the value of the parentEntryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param parentEntryId The entry ID of the folder that the document will be created in.
     * @return {@link ParametersForImportDocument} The return value
     */
    public ParametersForImportDocument setParentEntryId(int parentEntryId) {
        this.parentEntryId = parentEntryId;
        return this;
    }

    /**
     * The entry ID of the folder that the document will be created in.
     *
     * @return int The return value
     */
    public int getParentEntryId() {
        return this.parentEntryId;
    }

    /**
     * Sets the value of the fileName parameter and returns the current object, to enable chaining further setters.
     *
     * @param fileName The created document's file name.
     * @return {@link ParametersForImportDocument} The return value
     */
    public ParametersForImportDocument setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    /**
     * The created document's file name.
     *
     * @return {@link String} The return value
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Sets the value of the autoRename parameter and returns the current object, to enable chaining further setters.
     *
     * @param autoRename An optional query parameter used to indicate if the new document should be automatically
     *                   renamed if an entry already exists with the given name in the folder. The default value is false.
     * @return {@link ParametersForImportDocument} The return value
     */
    public ParametersForImportDocument setAutoRename(boolean autoRename) {
        this.autoRename = autoRename;
        return this;
    }

    /**
     * An optional query parameter used to indicate if the new document should be automatically
     * renamed if an entry already exists with the given name in the folder. The default value is false.
     *
     * @return boolean The return value
     */
    public boolean isAutoRename() {
        return this.autoRename;
    }

    /**
     * Sets the value of the culture parameter and returns the current object, to enable chaining further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used.
     *                The value should be a standard language tag. This may be used when setting field values with tokens.
     * @return {@link ParametersForImportDocument} The return value
     */
    public ParametersForImportDocument setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used.
     * The value should be a standard language tag. This may be used when setting field values with tokens.
     *
     * @return {@link String} The return value
     */
    public String getCulture() {
        return this.culture;
    }

    public ParametersForImportDocument setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    public ParametersForImportDocument setRequestBody(PostEntryWithEdocMetadataRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public PostEntryWithEdocMetadataRequest getRequestBody() {
        return this.requestBody;
    }
}
