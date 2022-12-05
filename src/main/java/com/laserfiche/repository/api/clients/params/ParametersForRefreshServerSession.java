package com.laserfiche.repository.api.clients.params;

public class ParametersForRefreshServerSession {

    /**
     * The requested repository ID.
     */
    private String repoId;

    public ParametersForRefreshServerSession setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }
}
