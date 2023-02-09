package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.repository.api.clients.AuditReasonsClient;
import com.laserfiche.repository.api.clients.impl.model.AuditReasons;
import com.laserfiche.repository.api.clients.params.ParametersForGetAuditReasons;
import kong.unirest.UnirestInstance;

import java.util.HashMap;
import java.util.Map;

/**
 * The Laserfiche Repository AuditReasons API client.
 */
public class AuditReasonsClientImpl extends ApiClient implements AuditReasonsClient {

    private HttpRequestHandler httpRequestHandler;

    public AuditReasonsClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient);
        this.httpRequestHandler = httpRequestHandler;
    }

    @Override
    public AuditReasons getAuditReasons(ParametersForGetAuditReasons parameters) {
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"repoId"}, new Object[]{parameters.getRepoId()});
        return sendRequestParseResponse(httpClient, objectMapper, AuditReasons.class, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/AuditReasons", "GET", null, null, null, null, null, pathParameters,
                new HashMap<String, String>(), false);
    }
}
