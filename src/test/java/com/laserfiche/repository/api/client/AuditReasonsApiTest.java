package com.laserfiche.repository.api.client;

import com.laserfiche.repository.api.ApiClient;
import com.laserfiche.repository.api.client.model.AuditReasons;
import com.laserfiche.repository.api.client.model.ProblemDetails;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * API tests for AuditReasonsApi
 */
public class AuditReasonsApiTest {

    private AuditReasonsApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(AuditReasonsApi.class);
    }


    /**
     * Get the audit reasons associated with the authenticated user.
     *
     * - Returns the audit reasons associated with the authenticated user. Inherited audit reasons are included. - Only includes audit reasons associated with available API functionalities, like delete entry and export document. - If the authenticated user does not have the appropriate Laserfiche feature right, the audit reasons associated with that feature right will not be included.
     */
    @Test
    public void getAuditReasonsTest() {
        String repoId = null;
        // AuditReasons response = api.getAuditReasons(repoId);

        // TODO: test validations
    }
}
