package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#getEntryByPath(ParametersForGetEntryByPath) getEntryByPath}.
 */
public class ParametersForGetEntryByPath {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested entry path.
     */
    private String fullPath;

    /**
     * An optional query parameter used to indicate whether or not the closest ancestor in the path should be returned if the initial entry path is not found. The default value is false.
     */
    private boolean fallbackToClosestAncestor;

    /**
     * Builder for setting the repoId parameter.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForGetEntryByPath} The return value
     */
    public ParametersForGetEntryByPath setRepoId(String repoId) {
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
     * Builder for setting the fullPath parameter.
     *
     * @param fullPath The requested entry path.
     * @return {@link ParametersForGetEntryByPath} The return value
     */
    public ParametersForGetEntryByPath setFullPath(String fullPath) {
        this.fullPath = fullPath;
        return this;
    }

    /**
     * The requested entry path.
     *
     * @return {@link String} The return value
     */
    public String getFullPath() {
        return this.fullPath;
    }

    /**
     * Builder for setting the fallbackToClosestAncestor parameter.
     *
     * @param fallbackToClosestAncestor An optional query parameter used to indicate whether or not the closest ancestor in the path should be returned if the initial entry path is not found. The default value is false.
     * @return {@link ParametersForGetEntryByPath} The return value
     */
    public ParametersForGetEntryByPath setFallbackToClosestAncestor(boolean fallbackToClosestAncestor) {
        this.fallbackToClosestAncestor = fallbackToClosestAncestor;
        return this;
    }

    /**
     * An optional query parameter used to indicate whether or not the closest ancestor in the path should be returned if the initial entry path is not found. The default value is false.
     *
     * @return {@link boolean} The return value
     */
    public boolean isFallbackToClosestAncestor() {
        return this.fallbackToClosestAncestor;
    }
}
