package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.ServerSessionClientImpl#createServerSession(ParametersForCreateServerSession) createServerSession}.
 */
public class ParametersForCreateServerSession {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForCreateServerSession} The return value
     */
    public ParametersForCreateServerSession setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    /**
     * The requested repository ID.
     *
     * @return {@link String} The return value
     */
    public String getRepoId() {
        return this.repoId;
    }
}