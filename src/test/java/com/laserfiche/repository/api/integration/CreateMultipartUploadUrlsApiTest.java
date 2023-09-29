package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.ParametersForCreateMultipartUploadUrls;
import com.laserfiche.repository.api.clients.params.ParametersForImportEntry;
import com.laserfiche.repository.api.clients.params.ParametersForListTemplateDefinitions;
import com.laserfiche.repository.api.clients.params.ParametersForListTemplateFieldDefinitionsByTemplateId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreateMultipartUploadUrlsApiTest extends BaseTest {
    private EntriesClient client;

    @BeforeEach
    public void perTestSetup() {
        client = repositoryApiClient.getEntriesClient();
    }

    @Test
    void createUploadUrlsIsSuccessful() {
        String fileName = "Sample.pdf";
        String mimeType = "application/pdf";

        int parts = 10;
        CreateMultipartUploadUrlsRequest requestBody = new CreateMultipartUploadUrlsRequest();
        requestBody.setFileName(fileName);
        requestBody.setMimeType(mimeType);
        requestBody.setNumberOfParts(parts);

        CreateMultipartUploadUrlsResponse response = client.createMultipartUploadUrls(new ParametersForCreateMultipartUploadUrls()
                .setRepositoryId(repositoryId).setRequestBody(requestBody));

        assertNotNull(response);
        String uploadId = response.getUploadId();
        assertNotNull(uploadId);
        assertEquals(parts, response.getUrls().size());

        // Get a second batch of URLs
        requestBody.setUploadId(uploadId);
        requestBody.setStartingPartNumber(parts + 1);
        requestBody.setFileName(null);
        requestBody.setMimeType(null);
        requestBody.setNumberOfParts(parts);

        response = client.createMultipartUploadUrls(new ParametersForCreateMultipartUploadUrls()
                .setRepositoryId(repositoryId).setRequestBody(requestBody));

        assertNotNull(response);
        String uploadId2 = response.getUploadId();
        assertNotNull(uploadId2);
        assertEquals(uploadId, uploadId2);
        assertEquals(parts, response.getUrls().size());
    }
}
