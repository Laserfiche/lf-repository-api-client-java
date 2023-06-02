package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.deserialization.ProblemDetailsDeserializer;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.clients.AttributesClient;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.laserfiche.repository.api.clients.params.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONObject;

/** The Laserfiche Repository Attributes API client. */
public class AttributesClientImpl extends ApiClient implements AttributesClient {

  public AttributesClientImpl(
      String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
    super(baseUrl, httpClient, httpRequestHandler);
  }

  @Override
  public Attribute getTrusteeAttributeValueByKey(
      ParametersForGetTrusteeAttributeValueByKey parameters) {
    Map<String, Object> queryParameters =
        ApiClientUtils.getParametersWithNonDefaultValue(
            new String[] {"boolean"},
            new String[] {"everyone"},
            new Object[] {parameters.isEveryone()});
    Map<String, Object> pathParameters =
        ApiClientUtils.getParametersWithNonDefaultValue(
            new String[] {"String", "String"},
            new String[] {"repoId", "attributeKey"},
            new Object[] {parameters.getRepoId(), parameters.getAttributeKey()});
    Function<HttpResponse<Object>, Attribute> parseResponse =
        (HttpResponse<Object> httpResponse) -> {
          Object body = httpResponse.getBody();
          Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
          if (httpResponse.getStatus() == 200) {
            try {
              String responseJson = new JSONObject(body).toString();
              return objectMapper.readValue(responseJson, Attribute.class);
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
            throw ApiClientUtils.createApiException(httpResponse, problemDetails);
          }
        };
    return ApiClientUtils.sendRequestWithRetry(
        httpClient,
        httpRequestHandler,
        baseUrl + "/v1/Repositories/{repoId}/Attributes/{attributeKey}",
        "GET",
        null,
        null,
        null,
        null,
        queryParameters,
        pathParameters,
        new HashMap<String, String>(),
        false,
        parseResponse);
  }

  @Override
  public ODataValueContextOfListOfAttribute getTrusteeAttributeKeyValuePairs(
      ParametersForGetTrusteeAttributeKeyValuePairs parameters) {
    return doGetTrusteeAttributeKeyValuePairs(
        baseUrl + "/v1/Repositories/{repoId}/Attributes", parameters);
  }

  private ODataValueContextOfListOfAttribute doGetTrusteeAttributeKeyValuePairs(
      String url, ParametersForGetTrusteeAttributeKeyValuePairs parameters) {
    Map<String, Object> queryParameters =
        ApiClientUtils.getParametersWithNonDefaultValue(
            new String[] {"boolean", "String", "String", "int", "int", "boolean"},
            new String[] {"everyone", "$select", "$orderby", "$top", "$skip", "$count"},
            new Object[] {
              parameters.isEveryone(),
              parameters.getSelect(),
              parameters.getOrderby(),
              parameters.getTop(),
              parameters.getSkip(),
              parameters.isCount()
            });
    Map<String, Object> pathParameters =
        ApiClientUtils.getParametersWithNonDefaultValue(
            new String[] {"String"},
            new String[] {"repoId"},
            new Object[] {parameters.getRepoId()});
    Map<String, Object> headerParameters =
        ApiClientUtils.getParametersWithNonDefaultValue(
            new String[] {"String"},
            new String[] {"prefer"},
            new Object[] {parameters.getPrefer()});
    Map<String, String> headerParametersWithStringTypeValue =
        headerParameters.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));
    Function<HttpResponse<Object>, ODataValueContextOfListOfAttribute> parseResponse =
        (HttpResponse<Object> httpResponse) -> {
          Object body = httpResponse.getBody();
          Map<String, String> headersMap = ApiClientUtils.getHeadersMap(httpResponse.getHeaders());
          if (httpResponse.getStatus() == 200) {
            try {
              String responseJson = new JSONObject(body).toString();
              return objectMapper.readValue(responseJson, ODataValueContextOfListOfAttribute.class);
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
            throw ApiClientUtils.createApiException(httpResponse, problemDetails);
          }
        };
    return ApiClientUtils.sendRequestWithRetry(
        httpClient,
        httpRequestHandler,
        url,
        "GET",
        null,
        null,
        null,
        null,
        queryParameters,
        pathParameters,
        headerParametersWithStringTypeValue,
        false,
        parseResponse);
  }

  @Override
  public ODataValueContextOfListOfAttribute getTrusteeAttributeKeyValuePairsNextLink(
      String nextLink, int maxPageSize) {
    return doGetTrusteeAttributeKeyValuePairs(
        nextLink,
        new ParametersForGetTrusteeAttributeKeyValuePairs()
            .setPrefer(ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, null)));
  }

  @Override
  public void getTrusteeAttributeKeyValuePairsForEach(
      Function<ODataValueContextOfListOfAttribute, Boolean> callback,
      Integer maxPageSize,
      ParametersForGetTrusteeAttributeKeyValuePairs parameters) {
    parameters.setPrefer(
        ApiClientUtils.mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
    ODataValueContextOfListOfAttribute response = getTrusteeAttributeKeyValuePairs(parameters);
    while (response != null && callback.apply(response)) {
      String nextLink = response.getOdataNextLink();
      response = getTrusteeAttributeKeyValuePairsNextLink(nextLink, maxPageSize);
    }
  }
}
