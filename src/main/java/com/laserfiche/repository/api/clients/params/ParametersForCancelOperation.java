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
     * Returns the value of 'repoId' field, which is the requested repository ID
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
     * Returns the value of 'operationToken' field, which is the operation token
     *
     * @return String The return value
     */
    public String getOperationToken() {
        return this.operationToken;
    }
}
