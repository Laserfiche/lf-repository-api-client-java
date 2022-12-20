package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.AuditReasonsClientImpl#getAuditReasons(ParametersForGetAuditReasons) getAuditReasons}.
 */
public class ParametersForGetAuditReasons {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * Builder for setting the repoId parameter.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForGetAuditReasons} The return value
     */
    public ParametersForGetAuditReasons setRepoId(String repoId) {
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
