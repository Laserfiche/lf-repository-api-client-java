package com.laserfiche.repository.api.clients.params;

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

    public ParametersForGetEntryByPath setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    /**
     * The requested repository ID.
     *
     * @return String The return value
     */
    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetEntryByPath setFullPath(String fullPath) {
        this.fullPath = fullPath;
        return this;
    }

    /**
     * The requested entry path.
     *
     * @return String The return value
     */
    public String getFullPath() {
        return this.fullPath;
    }

    public ParametersForGetEntryByPath setFallbackToClosestAncestor(boolean fallbackToClosestAncestor) {
        this.fallbackToClosestAncestor = fallbackToClosestAncestor;
        return this;
    }

    /**
     * An optional query parameter used to indicate whether or not the closest ancestor in the path should be returned if the initial entry path is not found. The default value is false.
     *
     * @return boolean The return value
     */
    public boolean isFallbackToClosestAncestor() {
        return this.fallbackToClosestAncestor;
    }
}
