package com.laserfiche.repository.api.clients.params;

public class ParametersForGetTrusteeAttributeValueByKey {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested attribute key.
     */
    private String attributeKey;

    /**
     * Boolean value that indicates whether to return attributes associated with everyone or the currently authenticated user.
     */
    private boolean everyone;

    public ParametersForGetTrusteeAttributeValueByKey setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    /**
     * The requested repository ID.
     *
     * @return String The return value
     */
    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetTrusteeAttributeValueByKey setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
        return this;
    }

    /**
     * The requested attribute key.
     *
     * @return String The return value
     */
    public String getAttributeKey() {
        return this.attributeKey;
    }

    public ParametersForGetTrusteeAttributeValueByKey setEveryone(boolean everyone) {
        this.everyone = everyone;
        return this;
    }

    /**
     * Boolean value that indicates whether to return attributes associated with everyone or the currently authenticated user.
     *
     * @return boolean The return value
     */
    public boolean isEveryone() {
        return this.everyone;
    }
}
