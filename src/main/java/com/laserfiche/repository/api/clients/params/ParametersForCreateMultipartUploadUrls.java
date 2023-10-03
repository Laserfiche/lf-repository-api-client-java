package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.CreateMultipartUploadUrlsRequest;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#createMultipartUploadUrls(ParametersForCreateMultipartUploadUrls) createMultipartUploadUrls}.
 */
public class ParametersForCreateMultipartUploadUrls {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    private CreateMultipartUploadUrlsRequest requestBody;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForCreateMultipartUploadUrls} The return value
     */
    public ParametersForCreateMultipartUploadUrls setRepositoryId(String repositoryId) {
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

    public ParametersForCreateMultipartUploadUrls setRequestBody(CreateMultipartUploadUrlsRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public CreateMultipartUploadUrlsRequest getRequestBody() {
        return this.requestBody;
    }
}
