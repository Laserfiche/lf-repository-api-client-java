package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link
 * com.laserfiche.repository.api.clients.impl.ServerSessionClientImpl#invalidateServerSession(ParametersForInvalidateServerSession)
 * invalidateServerSession}.
 */
public class ParametersForInvalidateServerSession {

    /** The requested repository ID. */
    private String repoId;

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForInvalidateServerSession} The return value
     */
    public ParametersForInvalidateServerSession setRepoId(String repoId) {
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
