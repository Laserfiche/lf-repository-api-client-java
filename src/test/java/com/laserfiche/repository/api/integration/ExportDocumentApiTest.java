// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.integration;

import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.ParametersForExportEntry;
import com.laserfiche.repository.api.clients.params.ParametersForImportEntry;
import com.laserfiche.repository.api.clients.params.ParametersForListAuditReasons;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ExportDocumentApiTest extends BaseTest {
    private EntriesClient client;
    private static int testEntryId;
    private static long testEntryFileSize;
    private static File exportedFile;

    private static int auditReasonId = -1;
    private static String auditReasonComment;

    @BeforeEach
    public void perTestSetup() {
        client = repositoryApiClient.getEntriesClient();
        exportedFile = null;
    }

    @AfterEach
    void perTestCleanUp() {
        if (exportedFile != null) {
            boolean deleted = exportedFile.delete();
            if (!deleted) {
                exportedFile.deleteOnExit();
            }
        }
    }

    @BeforeAll
    static void classSetup() {
        // Import a document that will be exported by tests in this class
        prepareDocumentToBeExported();

        // Find an audit reason for Export
        findAuditReasonForExport();
    }

    private static void findAuditReasonForExport() {
        AuditReasonCollectionResponse auditReasons = repositoryApiClient.getAuditReasonsClient()
                .listAuditReasons(
                        new ParametersForListAuditReasons().setRepositoryId(repositoryId));
        Optional<AuditReason> exportAuditReason = auditReasons.getValue().stream().filter(auditReason -> auditReason.getAuditEventType() == AuditEventType.EXPORT_DOCUMENT).findFirst();
        if (exportAuditReason.isPresent()) {
            auditReasonId = exportAuditReason.get().getId();
            auditReasonComment = exportAuditReason.get().getName();
        }
    }

    private static void prepareDocumentToBeExported() {
        FileInputStream fileInputStream = null;
        try {
            String fileName = "RepositoryApiClientIntegrationTest Java ExportDocumentApiTest.pdf";
            File fileToImport = new File(SMALL_PDF_FILE_PATH);
            fileInputStream = new FileInputStream(fileToImport);
            testEntryFileSize = fileToImport.length();
            ImportEntryRequest request = new ImportEntryRequest();
            request.setName(fileName);
            request.setAutoRename(true);
            ImportEntryRequestPdfOptions pdfOptions = new ImportEntryRequestPdfOptions();
            pdfOptions.setGeneratePages(true);
            pdfOptions.setGenerateText(true);
            pdfOptions.setKeepPdfAfterImport(true);
            request.setPdfOptions(pdfOptions);
            Entry resultEntry = repositoryApiClient
                    .getEntriesClient()
                    .importEntry(new ParametersForImportEntry()
                            .setRepositoryId(repositoryId)
                            .setEntryId(1)
                            .setInputStream(fileInputStream)
                            .setContentType("application/pdf")
                            .setRequestBody(request));

            testEntryId = resultEntry.getId();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @AfterAll
    static void classCleanUp() {
        deleteEntry(testEntryId);
    }

    @Test
    void exportDocumentCanExportEDocPart() {
        final String FILE_NAME = "exportDocument_temp_file.pdf";
        ExportEntryRequest request = new ExportEntryRequest();
        request.setPart(ExportEntryRequestPart.EDOC);
        if (auditReasonId != -1) {
            request.setAuditReasonId(auditReasonId);
            request.setAuditReasonComment(auditReasonComment);
        }
        ExportEntryResponse response = client.exportEntry(new ParametersForExportEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testEntryId)
                .setRequestBody(request));
        String uri = response.getValue();
        assertNotNull(uri);
        exportedFile = new File(FILE_NAME);
        boolean downloaded = downloadFileFromURI(uri, exportedFile);
        assertTrue(downloaded);
        assertTrue(exportedFile.exists());
        assertEquals(testEntryFileSize, exportedFile.length());
    }

    @Test
    void exportDocumentCanExportTextPart() {
        ExportEntryRequest request = new ExportEntryRequest();
        request.setPart(ExportEntryRequestPart.TEXT);
        if (auditReasonId != -1) {
            request.setAuditReasonId(auditReasonId);
            request.setAuditReasonComment(auditReasonComment);
        }
        ExportEntryResponse response = client.exportEntry(new ParametersForExportEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testEntryId)
                .setRequestBody(request));
        String uri = response.getValue();
        assertNotNull(uri);
        try {
            URLConnection connection = new URL(uri).openConnection();
            String mimeType = connection.getContentType();
            assertEquals(mimeType, "text/plain");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void exportDocumentThrowsExceptionForInvalidEntryId() {
        Exception thrown = Assertions.assertThrows(ApiException.class, () -> {
            ExportEntryRequest requestBody = new ExportEntryRequest();
            requestBody.setPart(ExportEntryRequestPart.EDOC);
            if (auditReasonId != -1) {
                requestBody.setAuditReasonId(auditReasonId);
                requestBody.setAuditReasonComment(auditReasonComment);
            }
            client.exportEntry(new ParametersForExportEntry()
                    .setRepositoryId(repositoryId)
                    .setRequestBody(requestBody)
                    .setEntryId(-1));
        });
        Assertions.assertEquals(
                "Error: Invalid value for entryId.", thrown.getMessage());
    }

    @Test
    void exportDocumentCanExportPagesAsSinglePageTIFF() {
        ExportEntryRequest request = new ExportEntryRequest();
        request.setPart(ExportEntryRequestPart.IMAGE);
        ExportEntryRequestImageOptions imageOptions = new ExportEntryRequestImageOptions();
        imageOptions.setFormat(ExportEntryRequestImageFormat.SINGLE_PAGE_TIFF);
        imageOptions.setPagePrefix("_PAGE_");
        ExportEntryRequestWatermark watermark = new ExportEntryRequestWatermark();
        watermark.setPosition(WatermarkPosition.BOTTOM_LEFT);
        watermark.setText("WatermarkText");
        watermark.setPageSpanPercentage(80);
        watermark.setRotationAngle(45);
        imageOptions.setWatermark(watermark);
        request.setImageOptions(imageOptions);
        if (auditReasonId != -1) {
            request.setAuditReasonId(auditReasonId);
            request.setAuditReasonComment(auditReasonComment);
        }
        ExportEntryResponse response = client.exportEntry(new ParametersForExportEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testEntryId)
                .setRequestBody(request));
        String uri = response.getValue();
        assertNotNull(uri);
        try {
            URLConnection connection = new URL(uri).openConnection();
            String mimeType = connection.getContentType();
            assertEquals(mimeType, "application/zip");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void exportDocumentCanExportPagesAsMultiPageTIFF() {
        ExportEntryRequest request = new ExportEntryRequest();
        request.setPart(ExportEntryRequestPart.IMAGE);
        ExportEntryRequestImageOptions imageOptions = new ExportEntryRequestImageOptions();
        imageOptions.setFormat(ExportEntryRequestImageFormat.MULTI_PAGE_TIFF);
        imageOptions.setIncludeAnnotations(true);
        imageOptions.setConvertPdfAnnotations(true);
        imageOptions.setIncludeRedactions(true);
        request.setImageOptions(imageOptions);
        if (auditReasonId != -1) {
            request.setAuditReasonId(auditReasonId);
            request.setAuditReasonComment(auditReasonComment);
        }
        ExportEntryResponse response = client.exportEntry(new ParametersForExportEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testEntryId)
                .setRequestBody(request));
        String uri = response.getValue();
        assertNotNull(uri);
        try {
            URLConnection connection = new URL(uri).openConnection();
            String mimeType = connection.getContentType();
            assertEquals(mimeType, "image/tiff");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void exportDocumentCanExportPagesAsJPEG() {
        ExportEntryRequest request = new ExportEntryRequest();
        request.setPart(ExportEntryRequestPart.IMAGE);
        ExportEntryRequestImageOptions imageOptions = new ExportEntryRequestImageOptions();
        imageOptions.setFormat(ExportEntryRequestImageFormat.JPEG);
        imageOptions.setJpegCompressionLevel(90);
        request.setImageOptions(imageOptions);
        if (auditReasonId != -1) {
            request.setAuditReasonId(auditReasonId);
            request.setAuditReasonComment(auditReasonComment);
        }
        ExportEntryResponse response = client.exportEntry(new ParametersForExportEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testEntryId)
                .setRequestBody(request));
        String uri = response.getValue();
        assertNotNull(uri);
        try {
            URLConnection connection = new URL(uri).openConnection();
            String mimeType = connection.getContentType();
            // Since pageRanges includes all the page, the result is a compressed file.
            assertEquals(mimeType, "application/zip");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void exportDocumentCanExportPagesAsJPEGWithPageRange() {
        ExportEntryRequest request = new ExportEntryRequest();
        request.setPart(ExportEntryRequestPart.IMAGE);
        ExportEntryRequestImageOptions imageOptions = new ExportEntryRequestImageOptions();
        imageOptions.setFormat(ExportEntryRequestImageFormat.JPEG);
        request.setImageOptions(imageOptions);
        if (auditReasonId != -1) {
            request.setAuditReasonId(auditReasonId);
            request.setAuditReasonComment(auditReasonComment);
        }
        ExportEntryResponse response = client.exportEntry(new ParametersForExportEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testEntryId)
                .setPageRange("1")
                .setRequestBody(request));
        String uri = response.getValue();
        assertNotNull(uri);
        try {
            URLConnection connection = new URL(uri).openConnection();
            String mimeType = connection.getContentType();
            // Since pageRanges includes only 1 page, the result is a single JPEG file.
            assertEquals(mimeType, "image/jpeg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
