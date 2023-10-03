package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#deleteElectronicDocument(ParametersForDeleteElectronicDocument) deleteElectronicDocument}.
 */
public class ParametersForDeleteElectronicDocument {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * The requested document ID.
     */
    private Integer entryId;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForDeleteElectronicDocument} The return value
     */
    public ParametersForDeleteElectronicDocument setRepositoryId(String repositoryId) {
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
     * Sets the value of the entryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param entryId The requested document ID.
     * @return {@link ParametersForDeleteElectronicDocument} The return value
     */
    public ParametersForDeleteElectronicDocument setEntryId(Integer entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The requested document ID.
     *
     * @return {@link Integer} The return value
     */
    public Integer getEntryId() {
        return this.entryId;
    }
}
