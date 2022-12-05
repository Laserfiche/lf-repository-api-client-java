package com.laserfiche.repository.api.clients.params;

public class ParametersForGetAuditReasons {

    /**
     * The requested repository ID.
     */
    private String repoId;

    public ParametersForGetAuditReasons setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }
}
