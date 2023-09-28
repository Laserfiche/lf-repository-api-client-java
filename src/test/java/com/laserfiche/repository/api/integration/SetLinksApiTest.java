package com.laserfiche.repository.api.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import java.util.ArrayList;
import java.util.List;

import com.laserfiche.repository.api.clients.params.ParametersForSetLinks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetLinksApiTest extends BaseTest {
    RepositoryApiClient client;
    Entry sourceEntry = null;

    Entry targetEntry = null;

    List<Entry> createdEntries;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient;
        createdEntries = new ArrayList<Entry>();
    }

    @AfterEach
    void perTestCleanUp() throws InterruptedException {
        deleteEntries(createdEntries);
    }

    @Test
    void setLinks_ReturnLinks() {
        sourceEntry = createEntry(client, "RepositoryApiClientIntegrationTest Java SetLinks Source", 1, true);
        createdEntries.add(sourceEntry);
        targetEntry = createEntry(client, "RepositoryApiClientIntegrationTest .Net SetLinks Target", 1, true);
        createdEntries.add(targetEntry);

        LinkToUpdate link = new LinkToUpdate();
        link.setOtherEntryId(targetEntry.getId());
        link.setLinkDefinitionId(1);
        List<LinkToUpdate> links = new ArrayList<LinkToUpdate>();
        links.add(link);
        SetLinksRequest request = new SetLinksRequest();
        request.setLinks(links);
        LinkCollectionResponse result = client.getEntriesClient()
                .setLinks(new ParametersForSetLinks()
                        .setRepositoryId(repositoryId)
                        .setEntryId(sourceEntry.getId())
                        .setRequestBody(request));
        List<Link> resultLinks = result.getValue();
        assertNotNull(resultLinks);
        assertEquals(links.size(), resultLinks.size());
        assertEquals(sourceEntry.getId(), resultLinks.get(0).getSourceId());
        assertEquals(targetEntry.getId(), resultLinks.get(0).getTargetId());
    }
}
