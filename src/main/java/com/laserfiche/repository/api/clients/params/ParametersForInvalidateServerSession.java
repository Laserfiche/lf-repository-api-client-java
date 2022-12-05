package com.laserfiche.repository.api.clients.params;

public class ParametersForInvalidateServerSession {

    /**
     * The requested repository ID.
     */
    private String repoId;

    public ParametersForInvalidateServerSession setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }


    /**
     * Returns the value of 'repoId' field, which is the requested repository ID.
     *
     * @return String The return value
     */
    public String getRepoId() {
        return this.repoId;
    }
}
