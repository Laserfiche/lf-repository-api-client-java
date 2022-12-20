package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.TasksClientImpl#getOperationStatusAndProgress(ParametersForGetOperationStatusAndProgress) getOperationStatusAndProgress}.
 */
public class ParametersForGetOperationStatusAndProgress {

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
     * @return {@link ParametersForGetOperationStatusAndProgress} The return value
     */
    public ParametersForGetOperationStatusAndProgress setRepoId(String repoId) {
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
     * @return {@link ParametersForGetOperationStatusAndProgress} The return value
     */
    public ParametersForGetOperationStatusAndProgress setOperationToken(String operationToken) {
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
