package com.laserfiche.repository.api.integration;

import com.laserfiche.repository.api.clients.AuditReasonsClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.TasksClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.URL;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class StartExportEntryApiTest extends BaseTest {
    private EntriesClient entriesClient;
    private TasksClient tasksClient;
    private static int testEntryId;
    private static long testEntryFileSize;
    private static File exportedFile;

    private static int auditReasonId = -1;
    private static String auditReasonComment;

    @BeforeEach
    public void perTestSetup() {
        entriesClient = repositoryApiClient.getEntriesClient();
        tasksClient = repositoryApiClient.getTasksClient();
        exportedFile = null;
    }

    @AfterEach
    void perTestCleanUp() throws InterruptedException {
        if (exportedFile != null) {
            exportedFile.delete();
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

    private static void prepareDocumentToBeExported() {
        try {
            String fileName = "RepositoryApiClientIntegrationTest Java ExportDocumentApiTest";
            File fileToImport = new File(SMALL_PDF_FILE_PATH);
            testEntryFileSize = fileToImport.length();
            ImportEntryRequest request = new ImportEntryRequest();
            request.setName(fileName);
            request.setAutoRename(true);
            Entry resultEntry = repositoryApiClient
                    .getEntriesClient()
                    .importEntry(new ParametersForImportEntry()
                            .setRepositoryId(repositoryId)
                            .setEntryId(1)
                            .setInputStream(new FileInputStream(fileToImport))
                            .setContentType("application/pdf")
                            .setRequestBody(request));

            testEntryId = resultEntry.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void classCleanUp() throws InterruptedException {
        deleteEntry(testEntryId);
    }

    @Test
    void startExportEntry_Successfully_Export_PDF_File() {
        final String FILE_NAME = "startExportEntry_temp_file.pdf";
        StartExportEntryRequest request = new StartExportEntryRequest();
        request.setPart(ExportEntryRequestPart.EDOC);
        if (auditReasonId != -1) {
            request.setAuditReasonId(auditReasonId);
            request.setAuditReasonComment(auditReasonComment);
        }

        StartTaskResponse startTaskResponse = entriesClient.startExportEntry(new ParametersForStartExportEntry()
                .setRepositoryId(repositoryId)
                .setEntryId(testEntryId)
                .setRequestBody(request));

        assertNotNull(startTaskResponse);
        String taskId = startTaskResponse.getTaskId();
        assertNotNull(taskId);

        waitUntilTaskEnds(taskId);

        TaskCollectionResponse taskCollectionResponse = tasksClient.listTasks(new ParametersForListTasks().setRepositoryId(repositoryId).setTaskIds(taskId));
        assertEquals(1, taskCollectionResponse.getValue().size());

        TaskProgress taskProgress = taskCollectionResponse.getValue().get(0);
        assertEquals(TaskStatus.COMPLETED, taskProgress.getStatus());
        assertTrue(taskProgress.getErrors().isEmpty());
        assertEquals(testEntryId, taskProgress.getResult().getEntryId());
        String uri = taskProgress.getResult().getUri();
        assertNotNull(uri);

        exportedFile = new File(FILE_NAME);
        boolean downloaded = downloadFileFromURI(uri, exportedFile);
        assertTrue(downloaded);
        assertTrue(exportedFile.exists());
        assertEquals(testEntryFileSize, exportedFile.length());
    }
}
