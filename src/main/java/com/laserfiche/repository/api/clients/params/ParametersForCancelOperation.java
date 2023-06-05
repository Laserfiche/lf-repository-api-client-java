package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;

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
     * Sets the value of the repoId parameter and returns the current object, to enable chaining further setters.
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
     * Sets the value of the operationToken parameter and returns the current object, to enable chaining further setters.
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
