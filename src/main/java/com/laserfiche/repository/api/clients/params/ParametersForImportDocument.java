package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForImportDocument {

    /**
     * The requested repository ID.
     */
    String repoId;

    /**
     * The entry ID of the folder that the document will be created in.
     */
    int parentEntryId;

    /**
     * The created document's file name.
     */
    String fileName;

    /**
     * An optional query parameter used to indicate if the new document should be automatically
     *            renamed if an entry already exists with the given name in the folder. The default value is false.
     */
    boolean autoRename;

    /**
     * An optional query parameter used to indicate the locale that should be used.
     *            The value should be a standard language tag. This may be used when setting field values with tokens.
     */
    String culture;

    InputStream inputStream;

    PostEntryWithEdocMetadataRequest requestBody;

    public ParametersForImportDocument setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForImportDocument setParentEntryId(int parentEntryId) {
        this.parentEntryId = parentEntryId;
        return this;
    }

    public int getParentEntryId() {
        return this.parentEntryId;
    }

    public ParametersForImportDocument setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileName() {
        return this.fileName;
    }

    public ParametersForImportDocument setAutoRename(boolean autoRename) {
        this.autoRename = autoRename;
        return this;
    }

    public boolean isAutoRename() {
        return this.autoRename;
    }

    public ParametersForImportDocument setCulture(String culture) {
        this.culture = culture;
        return this;
    }

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
