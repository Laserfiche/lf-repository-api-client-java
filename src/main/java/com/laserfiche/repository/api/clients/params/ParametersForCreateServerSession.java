package com.laserfiche.repository.api.clients.params;

public class ParametersForCreateServerSession {

    /**
     * The requested repository ID.
     */
    private String repoId;

    public ParametersForCreateServerSession setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }
}
