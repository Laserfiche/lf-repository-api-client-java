// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.AttributesClientImpl#getAttribute(ParametersForGetAttribute) getAttribute}.
 */
public class ParametersForGetAttribute {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * The requested attribute key.
     */
    private String attributeKey;

    /**
     * Indicates if attributes associated with the &quot;Everyone&quot; group or the currently authenticated user is returned. The default value is false.
     */
    private Boolean everyone;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForGetAttribute} The return value
     */
    public ParametersForGetAttribute setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
        return this;
    }

    /**
     * The requested repository ID.
     *
     * @return {@link String} The return value
     */
    public String getRepositoryId() {
        return this.repositoryId;
    }

    /**
     * Sets the value of the attributeKey parameter and returns the current object, to enable chaining further setters.
     *
     * @param attributeKey The requested attribute key.
     * @return {@link ParametersForGetAttribute} The return value
     */
    public ParametersForGetAttribute setAttributeKey(String attributeKey) {
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
     * Sets the value of the everyone parameter and returns the current object, to enable chaining further setters.
     *
     * @param everyone Indicates if attributes associated with the &quot;Everyone&quot; group or the currently authenticated user is returned. The default value is false.
     * @return {@link ParametersForGetAttribute} The return value
     */
    public ParametersForGetAttribute setEveryone(Boolean everyone) {
        this.everyone = everyone;
        return this;
    }

    /**
     * Indicates if attributes associated with the &amp;quot;Everyone&amp;quot; group or the currently authenticated user is returned. The default value is false.
     *
     * @return {@link Boolean} The return value
     */
    public Boolean isEveryone() {
        return this.everyone;
    }
}
