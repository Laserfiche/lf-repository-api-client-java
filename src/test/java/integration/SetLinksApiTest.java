package integration;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.EntriesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Target;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SetLinksApiTest extends BaseTest {
    RepositoryApiClient client;
    CompletableFuture<Entry> sourceEntry = null;

    CompletableFuture<Entry> targetEntry = null;

    List<Entry> createdEntries;

    @BeforeEach
    void PerTestSetup() {
        client = repositoryApiClient;
        createdEntries = new ArrayList<Entry>();
    }

    @AfterEach
    void deleteEntries() {
        for (Entry createdEntry : createdEntries) {
            if (createdEntry != null) {
                DeleteEntryWithAuditReason body = new DeleteEntryWithAuditReason();
                client.getEntriesClient().deleteEntryInfo(repoId, createdEntry.id, body);
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
        linkRequest.targetId = targetEntry.join().id;
        linkRequest.linkTypeId = 1;
        List<PutLinksRequest> request = new ArrayList<PutLinksRequest>();
        request.add(linkRequest);
        ODataValueOfIListOfWEntryLinkInfo result = client.getEntriesClient().assignEntryLinks(repoId, sourceEntry.join().id, request).join();
        List<WEntryLinkInfo> links = result.value;
        assertNotNull(links);
        assertEquals(request.size(), links.size());
        assertEquals(sourceEntry.join().id, links.get(0).sourceId);
        assertEquals(targetEntry.join().id, links.get(0).targetId);
    }

}
