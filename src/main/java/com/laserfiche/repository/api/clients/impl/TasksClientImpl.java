package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.repository.api.clients.TasksClient;
import com.laserfiche.repository.api.clients.impl.model.OperationProgress;
import com.laserfiche.repository.api.clients.params.ParametersForCancelOperation;
import com.laserfiche.repository.api.clients.params.ParametersForGetOperationStatusAndProgress;
import kong.unirest.UnirestInstance;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * The Laserfiche Repository Tasks API client.
 */
public class TasksClientImpl extends ApiClient implements TasksClient {

    private HttpRequestHandler httpRequestHandler;

    public TasksClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient);
        this.httpRequestHandler = httpRequestHandler;
    }

    @Override
    public OperationProgress getOperationStatusAndProgress(ParametersForGetOperationStatusAndProgress parameters) {
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "String"},
                new String[]{"repoId", "operationToken"},
                new Object[]{parameters.getRepoId(), parameters.getOperationToken()});
        return sendRequestParseResponse(httpClient, objectMapper, OperationProgress.class, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Tasks/{operationToken}", "GET", null, null, null, null, null,
                pathParameters, new HashMap<String, String>(), false);
    }

    @Override
    public boolean cancelOperation(ParametersForCancelOperation parameters) {
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "String"},
                new String[]{"repoId", "operationToken"},
                new Object[]{parameters.getRepoId(), parameters.getOperationToken()});
        return sendRequestParseResponse(httpClient, objectMapper, boolean.class, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/Tasks/{operationToken}", "DELETE", null, null, null, null, null,
                pathParameters, new HashMap<String, String>(), false);
    }
}
