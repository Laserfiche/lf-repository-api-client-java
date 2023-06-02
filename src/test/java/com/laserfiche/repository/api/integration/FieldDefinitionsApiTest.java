package com.laserfiche.repository.api.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.laserfiche.repository.api.clients.FieldDefinitionsClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfIListOfWFieldInfo;
import com.laserfiche.repository.api.clients.impl.model.WFieldInfo;
import com.laserfiche.repository.api.clients.params.ParametersForGetFieldDefinitionById;
import com.laserfiche.repository.api.clients.params.ParametersForGetFieldDefinitions;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FieldDefinitionsApiTest extends BaseTest {
  FieldDefinitionsClient client;

  @BeforeEach
  void perTestSetup() {
    client = repositoryApiClient.getFieldDefinitionsClient();
  }

  @Test
  void getFieldDefinitionById_ReturnField() {
    WFieldInfo fieldInfo =
        client.getFieldDefinitionById(
            new ParametersForGetFieldDefinitionById()
                .setRepoId(repositoryId)
                .setFieldDefinitionId(1));

    assertNotNull(fieldInfo);
  }

  @Test
  void getFieldDefinitions_ReturnAllFields() {
    ODataValueContextOfIListOfWFieldInfo fieldInfoList =
        client.getFieldDefinitions(new ParametersForGetFieldDefinitions().setRepoId(repositoryId));

    assertNotNull(fieldInfoList);
  }

  @Test
  void getFieldDefinitions_NextLink() throws InterruptedException {
    int maxPageSize = 3;
    ODataValueContextOfIListOfWFieldInfo fieldInfoList =
        client.getFieldDefinitions(
            new ParametersForGetFieldDefinitions()
                .setRepoId(repositoryId)
                .setPrefer(String.format("maxpagesize=%d", maxPageSize)));
    assertNotNull(fieldInfoList);

    String nextLink = fieldInfoList.getOdataNextLink();
    assertNotNull(nextLink);

    assertTrue(fieldInfoList.getValue().size() <= maxPageSize);

    ODataValueContextOfIListOfWFieldInfo nextLinkResult =
        client.getFieldDefinitionsNextLink(nextLink, maxPageSize);

    assertNotNull(nextLinkResult);
    assertTrue(nextLinkResult.getValue().size() <= maxPageSize);
  }

  @Test
  void getFieldDefinitions_ForEach() {
    AtomicInteger pageCount = new AtomicInteger();
    int maxPages = 2;
    int maxPageSize = 3;
    Function<ODataValueContextOfIListOfWFieldInfo, Boolean> callback =
        fieldInfoList -> {
          if (pageCount.incrementAndGet() <= maxPages && fieldInfoList.getOdataNextLink() != null) {
            assertNotEquals(0, fieldInfoList.getValue().size());
            assertTrue(fieldInfoList.getValue().size() <= maxPageSize);
            return true;
          } else {
            return false;
          }
        };
    client.getFieldDefinitionsForEach(
        callback, maxPageSize, new ParametersForGetFieldDefinitions().setRepoId(repositoryId));
    assertTrue(pageCount.get() > 1);
  }
}
