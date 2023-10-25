// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.TasksClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ImportUploadedPartsApiTest extends BaseTest {
    private static Entry testClassParentFolder;
    private EntriesClient client;
    private TasksClient tasksClient;
    private static int auditReasonId = -1;
    private static String auditReasonComment;

    @BeforeEach
    public void perTestSetup() {
        client = repositoryApiClient.getEntriesClient();
        tasksClient = repositoryApiClient.getTasksClient();
    }

    @BeforeAll
    static void classSetup() {
        String name = "RepositoryApiClientIntegrationTest Java TestClassParentFolder";
        testClassParentFolder = createEntry(repositoryApiClient, name, 1, true);
        findAuditReasonForExport();
    }

    private static void findAuditReasonForExport() {
        AuditReasonCollectionResponse auditReasons = repositoryApiClient
                .getAuditReasonsClient()
                .listAuditReasons(
                        new ParametersForListAuditReasons().setRepositoryId(repositoryId));
        Optional<AuditReason> exportAuditReason = auditReasons.getValue().stream().filter(auditReason -> auditReason.getAuditEventType() == AuditEventType.EXPORT_DOCUMENT).findFirst();
        if (exportAuditReason.isPresent()) {
            auditReasonId = exportAuditReason.get().getId();
            auditReasonComment = exportAuditReason.get().getName();
        }
    }

    @AfterAll
    static void classCleanUp() {
        deleteEntry(testClassParentFolder.getId());
    }

    @Test
    void createMultipartUploadUrlsCanBeCalledForSecondBatchOfURLs() {
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

    @Test
    void startImportUploadedPartsCanImportLargeFileAndGeneratePages() {
        String fileName = "Sample.pdf";
        String mimeType = "application/pdf";

        // Step 1: Get upload URLs
        int parts = 2;
        int partSizeInMB = 5;
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

        // Step 2: Write file part into upload URLs
        List<String> eTags = writeFile(LARGE_PDF_FILE_PATH, response.getUrls(), partSizeInMB);
        assertEquals(parts, eTags.size());

        // Step 3: Call ImportUploadedParts API
        StartImportUploadedPartsRequest requestBody2 = new StartImportUploadedPartsRequest();
        requestBody2.setUploadId(uploadId);
        requestBody2.setAutoRename(true);
        requestBody2.setPartETags(eTags);
        requestBody2.setName(fileName);
        ImportEntryRequestPdfOptions pdfOptions = new ImportEntryRequestPdfOptions();
        pdfOptions.setGeneratePages(true);
        pdfOptions.setKeepPdfAfterImport(true);
        requestBody2.setPdfOptions(pdfOptions);
        StartTaskResponse response2 = client.startImportUploadedParts(new ParametersForStartImportUploadedParts()
                .setRepositoryId(repositoryId)
                .setEntryId(testClassParentFolder.getId())
                .setRequestBody(requestBody2));

        assertNotNull(response2);
        String taskId = response2.getTaskId();
        assertNotNull(taskId);

        TaskCollectionResponse tasks = tasksClient.listTasks(new ParametersForListTasks().setRepositoryId(repositoryId).setTaskIds(taskId));
        assertNotNull(tasks);
        assertEquals(1, tasks.getValue().size());
        TaskProgress taskProgress = tasks.getValue().get(0);
        if (taskProgress.getStatus() == TaskStatus.FAILED) {
            printProblemDetails(taskProgress.getErrors().get(0));
        }
        assertEquals(TaskStatus.COMPLETED, taskProgress.getStatus());
        assertTrue(taskProgress.getErrors().isEmpty());
        assertTrue(taskProgress.getResult().getEntryId() > 1);
        String uri = taskProgress.getResult().getUri();
        assertNotNull(uri);

        // Export the imported entry to check the imported file size
        int createdEntryId = taskProgress.getResult().getEntryId();
        StartExportEntryRequest exportRequestBody = new StartExportEntryRequest();
        exportRequestBody.setPart(ExportEntryRequestPart.EDOC);
        if (auditReasonId != -1) {
            exportRequestBody.setAuditReasonId(auditReasonId);
            exportRequestBody.setAuditReasonComment(auditReasonComment);
        }
        StartTaskResponse exportResponse = client.startExportEntry(new ParametersForStartExportEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(createdEntryId)
                .setRequestBody(exportRequestBody));

        assertNotNull(exportResponse);
        taskId = exportResponse.getTaskId();
        assertNotNull(taskId);

        tasks = tasksClient.listTasks(new ParametersForListTasks().setRepositoryId(repositoryId).setTaskIds(taskId));
        assertNotNull(tasks);
        assertEquals(1, tasks.getValue().size());
        taskProgress = tasks.getValue().get(0);
        if (taskProgress.getStatus() == TaskStatus.FAILED) {
            printProblemDetails(taskProgress.getErrors().get(0));
        }
        assertEquals(TaskStatus.COMPLETED, taskProgress.getStatus());
        assertTrue(taskProgress.getErrors().isEmpty());
        uri = taskProgress.getResult().getUri();
        assertNotNull(uri);

        File exportedFile = download(uri, fileName);
        assertNotNull(exportedFile);
        assertTrue(exportedFile.isFile());
        assertEquals(new File(LARGE_PDF_FILE_PATH).length(), exportedFile.length());

        exportedFile.deleteOnExit();

        // Call GetEntry api to check the imported entry
        Entry entry = client.getEntry(new ParametersForGetEntry().setRepositoryId(repositoryId).setEntryId(createdEntryId));
        assertEquals(EntryType.DOCUMENT, entry.getEntryType());
        assertTrue(entry instanceof Document);
        Document document = (Document) entry;
        assertEquals("pdf", document.getExtension());
        assertEquals(56, document.getPageCount());
        assertEquals(new File(LARGE_PDF_FILE_PATH).length(), document.getElectronicDocumentSize());
        assertTrue(document.isElectronicDocument());
        assertEquals(mimeType, document.getMimeType());
    }

    private File download(String uri, String fileName) {
        File file = new File(fileName);
        FileOutputStream outputStream = null;
        ReadableByteChannel readableByteChannel = null;
        try {
            readableByteChannel = Channels.newChannel(new URL(uri).openStream());
            outputStream = new FileOutputStream(file);
            outputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (readableByteChannel != null) {
                try {
                    readableByteChannel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    /*
    NOTE: the following code is only for the purpose of the specific files used in the integration test. It doesn't work for any file of any scenario.
     */
    private List<String> writeFile(String filePath, List<String> urls, int partSizeInMB) {
        File file = new File(filePath);
        List<String> eTags = new ArrayList<>(urls.size());
        try (FileInputStream inputStream = new FileInputStream(file)) {
            for (String uploadUrl : urls) {
                byte[] buffer = new byte[partSizeInMB * 1024 * 1024];
                int numberOfBytesRead = inputStream.read(buffer);

                URL url = new URL(uploadUrl);
                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                httpCon.setDoOutput(true);
                httpCon.setRequestMethod("PUT");
                httpCon.setRequestProperty("Content-Type", "application/octet-stream");

                DataOutputStream outputStream = new DataOutputStream(
                        httpCon.getOutputStream());
                outputStream.write(buffer, 0, numberOfBytesRead);
                outputStream.flush();
                outputStream.close();
                if (httpCon.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    String eTag = httpCon.getHeaderField("ETag");
                    eTags.add(eTag);
                }
                httpCon.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eTags;
    }
}
