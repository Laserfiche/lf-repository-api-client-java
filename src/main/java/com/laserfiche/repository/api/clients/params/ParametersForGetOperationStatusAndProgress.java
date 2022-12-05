package com.laserfiche.repository.api.clients.params;

public class ParametersForGetOperationStatusAndProgress {

    /**
     * The requested repository ID
     */
    private String repoId;

    /**
     * The operation token
     */
    private String operationToken;

    public ParametersForGetOperationStatusAndProgress setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetOperationStatusAndProgress setOperationToken(String operationToken) {
        this.operationToken = operationToken;
        return this;
    }

    public String getOperationToken() {
        return this.operationToken;
    }
}
