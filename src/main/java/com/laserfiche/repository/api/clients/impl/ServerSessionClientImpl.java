package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.repository.api.clients.ServerSessionClient;
import com.laserfiche.repository.api.clients.impl.model.ODataValueOfBoolean;
import com.laserfiche.repository.api.clients.impl.model.ODataValueOfDateTime;
import com.laserfiche.repository.api.clients.params.ParametersForCreateServerSession;
import com.laserfiche.repository.api.clients.params.ParametersForInvalidateServerSession;
import com.laserfiche.repository.api.clients.params.ParametersForRefreshServerSession;
import kong.unirest.UnirestInstance;

import java.util.HashMap;
import java.util.Map;

/**
 * The Laserfiche Repository ServerSession API client.
 */
public class ServerSessionClientImpl extends ApiClient implements ServerSessionClient {

    private HttpRequestHandler httpRequestHandler;

    public ServerSessionClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient);
        this.httpRequestHandler = httpRequestHandler;
    }

    @Override
    public ODataValueOfBoolean invalidateServerSession(ParametersForInvalidateServerSession parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"repoId"}, new Object[]{parameters.getRepoId()});
        return sendRequestParseResponse(httpClient, objectMapper, ODataValueOfBoolean.class, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/ServerSession/Invalidate", "POST", null, null, null, null, null,
                pathParameters, new HashMap<String, String>(), false);
    }

    @Override
    public ODataValueOfBoolean createServerSession(ParametersForCreateServerSession parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"repoId"}, new Object[]{parameters.getRepoId()});
        return sendRequestParseResponse(httpClient, objectMapper, ODataValueOfBoolean.class, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/ServerSession/Create", "POST", null, null, null, null, null,
                pathParameters, new HashMap<String, String>(), false);
    }

    @Override
    public ODataValueOfDateTime refreshServerSession(ParametersForRefreshServerSession parameters) {
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"repoId"}, new Object[]{parameters.getRepoId()});
        return sendRequestParseResponse(httpClient, objectMapper, ODataValueOfDateTime.class, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/ServerSession/Refresh", "POST", null, null, null, null, null,
                pathParameters, new HashMap<String, String>(), false);
    }
}
