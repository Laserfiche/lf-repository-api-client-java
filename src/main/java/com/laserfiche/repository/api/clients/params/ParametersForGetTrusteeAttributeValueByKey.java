package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.AttributesClientImpl#getTrusteeAttributeValueByKey(ParametersForGetTrusteeAttributeValueByKey) getTrusteeAttributeValueByKey}.
 */
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

    /**
     * Builder for setting the repoId parameter.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForGetTrusteeAttributeValueByKey} The return value
     */
    public ParametersForGetTrusteeAttributeValueByKey setRepoId(String repoId) {
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

    /**
     * Builder for setting the attributeKey parameter.
     *
     * @param attributeKey The requested attribute key.
     * @return {@link ParametersForGetTrusteeAttributeValueByKey} The return value
     */
    public ParametersForGetTrusteeAttributeValueByKey setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
        return this;
    }

    /**
     * The requested attribute key.
     *
     * @return {@link String} The return value
     */
    public String getAttributeKey() {
        return this.attributeKey;
    }

    /**
     * Builder for setting the everyone parameter.
     *
     * @param everyone Boolean value that indicates whether to return attributes associated with everyone or the currently authenticated user.
     * @return {@link ParametersForGetTrusteeAttributeValueByKey} The return value
     */
    public ParametersForGetTrusteeAttributeValueByKey setEveryone(boolean everyone) {
        this.everyone = everyone;
        return this;
    }

    /**
     * Boolean value that indicates whether to return attributes associated with everyone or the currently authenticated user.
     *
     * @return {@link boolean} The return value
     */
    public boolean isEveryone() {
        return this.everyone;
    }
}
