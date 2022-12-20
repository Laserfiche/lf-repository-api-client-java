package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.TasksClientImpl#cancelOperation(ParametersForCancelOperation) cancelOperation}.
 */
public class ParametersForCancelOperation {

    /**
     * The requested repository ID
     */
    private String repoId;

    /**
     * The operation token
     */
    private String operationToken;

    /**
     * Builder for setting the repoId parameter.
     *
     * @param repoId The requested repository ID
     * @return {@link ParametersForCancelOperation} The return value
     */
    public ParametersForCancelOperation setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    /**
     * The requested repository ID
     *
     * @return {@link String} The return value
     */
    public String getRepoId() {
        return this.repoId;
    }

    /**
     * Builder for setting the operationToken parameter.
     *
     * @param operationToken The operation token
     * @return {@link ParametersForCancelOperation} The return value
     */
    public ParametersForCancelOperation setOperationToken(String operationToken) {
        this.operationToken = operationToken;
        return this;
    }

    /**
     * The operation token
     *
     * @return {@link String} The return value
     */
    public String getOperationToken() {
        return this.operationToken;
    }
}
