package com.laserfiche.repository.api.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.laserfiche.repository.api.clients.FieldDefinitionsClient;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import com.laserfiche.repository.api.clients.impl.model.FieldDefinition;
import com.laserfiche.repository.api.clients.impl.model.FieldDefinitionCollectionResponse;
import com.laserfiche.repository.api.clients.params.ParametersForGetFieldDefinition;
import com.laserfiche.repository.api.clients.params.ParametersForListFieldDefinitions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FieldDefinitionsApiTest extends BaseTest {
    private FieldDefinitionsClient client;

    @BeforeEach
    void perTestSetup() {
        client = repositoryApiClient.getFieldDefinitionsClient();
    }

    @Test
    void getFieldDefinitionById_ReturnField() {
        FieldDefinition definition = client.getFieldDefinition(new ParametersForGetFieldDefinition()
                .setRepositoryId(repositoryId)
                .setFieldId(1));

        assertNotNull(definition);
        assertEquals(1, definition.getId());
    }

    @Test
    void getFieldDefinitions_ReturnAllFields() {
        FieldDefinitionCollectionResponse definitions =
                client.listFieldDefinitions(new ParametersForListFieldDefinitions()
                        .setRepositoryId(repositoryId));

        assertNotNull(definitions);
        assertFalse(definitions.getValue().isEmpty());
    }

    @Test
    void getFieldDefinitions_NextLink() throws InterruptedException {
        int maxPageSize = 3;
        FieldDefinitionCollectionResponse definitions =
                client.listFieldDefinitions(new ParametersForListFieldDefinitions()
                        .setRepositoryId(repositoryId)
                        .setPrefer(String.format("maxpagesize=%d", maxPageSize)));
        assertNotNull(definitions);
        assertTrue(definitions.getValue().size() <= maxPageSize);

        String nextLink = definitions.getOdataNextLink();
        assertNotNull(nextLink);

        FieldDefinitionCollectionResponse nextLinkResult = client.listFieldDefinitionsNextLink(nextLink, maxPageSize);
        assertNotNull(nextLinkResult);
        assertTrue(nextLinkResult.getValue().size() <= maxPageSize);
    }

    @Test
    void getFieldDefinitions_ForEach() {
        AtomicInteger pageCount = new AtomicInteger();
        int maxPages = 2;
        int maxPageSize = 3;
        Function<FieldDefinitionCollectionResponse, Boolean> callback = fieldInfoList -> {
            if (pageCount.incrementAndGet() <= maxPages) {
                assertNotEquals(0, fieldInfoList.getValue().size());
                assertTrue(fieldInfoList.getValue().size() <= maxPageSize);
                return fieldInfoList.getOdataNextLink() != null;
            } else {
                return false;
            }
        };
        client.listFieldDefinitionsForEach(
                callback, maxPageSize, new ParametersForListFieldDefinitions().setRepositoryId(repositoryId));
        assertTrue(pageCount.get() > 1);
    }
}
