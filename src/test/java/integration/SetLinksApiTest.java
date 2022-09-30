package integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SetLinksApiTest extends BaseTest {
    RepositoryApiClient client;
    CompletableFuture<Entry> sourceEntry = null;

    CompletableFuture<Entry> targetEntry = null;

    List<Entry> createdEntries;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient;
        createdEntries = new ArrayList<Entry>();
    }

    @AfterEach
    void deleteEntries() {
        for (Entry createdEntry : createdEntries) {
            if (createdEntry != null) {
                DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
                client.getEntriesClient().deleteEntryInfo(repoId, createdEntry.getId(), body);
            }
        }
    }

    @Test
    void setLinks_ReturnLinks() {
        sourceEntry = createEntry(client, "RepositoryApiClientIntegrationTest Java SetLinks Source", 1, true);
        createdEntries.add(sourceEntry.join());
        targetEntry = createEntry(client, "RepositoryApiClientIntegrationTest .Net SetLinks Target", 1, true);
        createdEntries.add(targetEntry.join());
        PutLinksRequest linkRequest = new PutLinksRequest();
        linkRequest.setTargetId(targetEntry.join().getId());
        linkRequest.setLinkTypeId(1);
        List<PutLinksRequest> request = new ArrayList<PutLinksRequest>();
        request.add(linkRequest);
        ODataValueOfIListOfWEntryLinkInfo result = client.getEntriesClient().assignEntryLinks(repoId, sourceEntry.join().getId(), request).join();
        List<WEntryLinkInfo> links = result.getValue();
        assertNotNull(links);
        assertEquals(request.size(), links.size());
        assertEquals(sourceEntry.join().getId(), links.get(0).getSourceId());
        assertEquals(targetEntry.join().getId(), links.get(0).getTargetId());
    }

}