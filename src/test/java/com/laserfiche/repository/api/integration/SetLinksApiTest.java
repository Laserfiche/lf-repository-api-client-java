package com.laserfiche.repository.api.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.ParametersForAssignEntryLinks;
import java.util.ArrayList;
import java.util.List;
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
    sourceEntry =
        createEntry(client, "RepositoryApiClientIntegrationTest Java SetLinks Source", 1, true);
    createdEntries.add(sourceEntry);
    targetEntry =
        createEntry(client, "RepositoryApiClientIntegrationTest .Net SetLinks Target", 1, true);
    createdEntries.add(targetEntry);
    PutLinksRequest linkRequest = new PutLinksRequest();
    linkRequest.setTargetId(targetEntry.getId());
    linkRequest.setLinkTypeId(1);
    List<PutLinksRequest> request = new ArrayList<PutLinksRequest>();
    request.add(linkRequest);
    ODataValueOfIListOfWEntryLinkInfo result =
        client
            .getEntriesClient()
            .assignEntryLinks(
                new ParametersForAssignEntryLinks()
                    .setRepoId(repositoryId)
                    .setEntryId(sourceEntry.getId())
                    .setRequestBody(request));
    List<WEntryLinkInfo> links = result.getValue();
    assertNotNull(links);
    assertEquals(request.size(), links.size());
    assertEquals(sourceEntry.getId(), links.get(0).getSourceId());
    assertEquals(targetEntry.getId(), links.get(0).getTargetId());
  }
}
