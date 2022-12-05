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

    /**
     * The requested repository ID.
     *
     * @return String The return value
     */
    public String getRepoId() {
        return this.repoId;
    }
}
