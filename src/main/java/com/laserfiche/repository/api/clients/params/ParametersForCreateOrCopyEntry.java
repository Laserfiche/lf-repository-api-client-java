// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#createOrCopyEntry(ParametersForCreateOrCopyEntry) createOrCopyEntry}.
 */
public class ParametersForCreateOrCopyEntry {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The folder ID that the entry will be created in.
     */
    private Integer entryId;

    private PostEntryChildrenRequest requestBody;

    /**
     * An optional query parameter used to indicate if the new entry should be automatically
     *             renamed if an entry already exists with the given name in the folder. The default value is false.
     */
    private Boolean autoRename;

    /**
     * An optional query parameter used to indicate the locale that should be used.
     *             The value should be a standard language tag.
     */
    private String culture;

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForCreateOrCopyEntry} The return value
     */
    public ParametersForCreateOrCopyEntry setRepoId(String repoId) {
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
     * Sets the value of the entryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param entryId The folder ID that the entry will be created in.
     * @return {@link ParametersForCreateOrCopyEntry} The return value
     */
    public ParametersForCreateOrCopyEntry setEntryId(Integer entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The folder ID that the entry will be created in.
     *
     * @return {@link Integer} The return value
     */
    public Integer getEntryId() {
        return this.entryId;
    }

    public ParametersForCreateOrCopyEntry setRequestBody(PostEntryChildrenRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public PostEntryChildrenRequest getRequestBody() {
        return this.requestBody;
    }

    /**
     * Sets the value of the autoRename parameter and returns the current object, to enable chaining further setters.
     *
     * @param autoRename An optional query parameter used to indicate if the new entry should be automatically
     *             renamed if an entry already exists with the given name in the folder. The default value is false.
     * @return {@link ParametersForCreateOrCopyEntry} The return value
     */
    public ParametersForCreateOrCopyEntry setAutoRename(Boolean autoRename) {
        this.autoRename = autoRename;
        return this;
    }

    /**
     * An optional query parameter used to indicate if the new entry should be automatically<br>            renamed if an entry already exists with the given name in the folder. The default value is false.
     *
     * @return {@link Boolean} The return value
     */
    public Boolean isAutoRename() {
        return this.autoRename;
    }

    /**
     * Sets the value of the culture parameter and returns the current object, to enable chaining further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used.
     *             The value should be a standard language tag.
     * @return {@link ParametersForCreateOrCopyEntry} The return value
     */
    public ParametersForCreateOrCopyEntry setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used.<br>            The value should be a standard language tag.
     *
     * @return {@link String} The return value
     */
    public String getCulture() {
        return this.culture;
    }
}
