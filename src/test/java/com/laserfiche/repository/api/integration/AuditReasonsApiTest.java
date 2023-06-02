package com.laserfiche.repository.api.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.laserfiche.repository.api.clients.AuditReasonsClient;
import com.laserfiche.repository.api.clients.impl.model.AuditReasons;
import com.laserfiche.repository.api.clients.params.ParametersForGetAuditReasons;
import org.junit.jupiter.api.Test;

class AuditReasonsApiTest extends BaseTest {
  @Test
  void getAuditReasons_ReturnAuditReasons() {
    AuditReasonsClient client = repositoryApiClient.getAuditReasonsClient();
    AuditReasons reasons =
        client.getAuditReasons(new ParametersForGetAuditReasons().setRepoId(repositoryId));

    assertNotNull(reasons);
  }
}
