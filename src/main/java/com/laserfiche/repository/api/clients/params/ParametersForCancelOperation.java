package com.laserfiche.repository.api.clients.params;

public class ParametersForCancelOperation {

    /**
     * The requested repository ID
     */
    private String repoId;

    /**
     * The operation token
     */
    private String operationToken;

    public ParametersForCancelOperation setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    /**
     * The requested repository ID
     *
     * @return String The return value
     */
    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForCancelOperation setOperationToken(String operationToken) {
        this.operationToken = operationToken;
        return this;
    }

    /**
     * The operation token
     *
     * @return String The return value
     */
    public String getOperationToken() {
        return this.operationToken;
    }
}
