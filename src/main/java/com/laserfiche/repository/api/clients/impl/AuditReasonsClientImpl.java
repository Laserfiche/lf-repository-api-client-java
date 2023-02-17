package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.AuditReasonsClient;
import com.laserfiche.repository.api.clients.impl.model.AuditReasons;
import com.laserfiche.repository.api.clients.params.ParametersForGetAuditReasons;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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
        Map<String, Object> pathParameters = ApiClientUtils.getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"repoId"}, new Object[]{parameters.getRepoId()});
        Function<HttpResponse<Object>, AuditReasons> parseResponse = (HttpResponse<Object> httpResponse) -> {
            Object body = httpResponse.getBody();
            Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
            if (httpResponse.getStatus() == 200) {
                try {
                    String responseJson = new JSONObject(body).toString();
                    return objectMapper.readValue(responseJson, AuditReasons.class);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
            } else {
                ProblemDetails problemDetails;
                try {
                    String jsonString = new JSONObject(body).toString();
                    problemDetails = ProblemDetailsDeserializer.deserialize(objectMapper, jsonString);
                } catch (Exception e) {
                    throw ApiException.create(httpResponse.getStatus(), headersMap, null, e);
                }
                if (httpResponse.getStatus() == 400)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 401)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 403)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 404)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else if (httpResponse.getStatus() == 429)
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
                else
                    throw ApiException.create(httpResponse.getStatus(), headersMap, problemDetails, null);
            }
        };
        return ApiClientUtils.sendRequestWithRetry(httpClient, httpRequestHandler,
                baseUrl + "/v1/Repositories/{repoId}/AuditReasons", "GET", null, null, null, null, null, pathParameters,
                new HashMap<String, String>(), false, parseResponse);
    }
}

