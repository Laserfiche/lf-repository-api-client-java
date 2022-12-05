package com.laserfiche.repository.api.clients.params;

public class ParametersForInvalidateServerSession {

    /**
     * The requested repository ID.
     */
    private String repoId;

    public ParametersForInvalidateServerSession setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }
}
