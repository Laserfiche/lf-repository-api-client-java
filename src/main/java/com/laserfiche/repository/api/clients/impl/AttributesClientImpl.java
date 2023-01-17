package com.laserfiche.repository.api.clients.impl;

import com.laserfiche.api.client.httphandlers.*;
import com.laserfiche.api.client.model.ApiException;
import com.laserfiche.api.client.model.ProblemDetails;
import com.laserfiche.repository.api.RepositoryApiClientInterceptor;
import com.laserfiche.repository.api.clients.AttributesClient;
import com.laserfiche.repository.api.clients.impl.model.Attribute;
import com.laserfiche.repository.api.clients.impl.model.ODataValueContextOfListOfAttribute;
import com.laserfiche.repository.api.clients.params.ParametersForGetTrusteeAttributeKeyValuePairs;
import com.laserfiche.repository.api.clients.params.ParametersForGetTrusteeAttributeValueByKey;
import kong.unirest.HttpResponse;
import kong.unirest.UnirestInstance;
import kong.unirest.UnirestParsingException;
import kong.unirest.json.JSONObject;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The Laserfiche Repository Attributes API client.
 */
public class AttributesClientImpl extends ApiClient implements AttributesClient {

    private HttpRequestHandler httpRequestHandler;

    public AttributesClientImpl(String baseUrl, UnirestInstance httpClient, HttpRequestHandler httpRequestHandler) {
        super(baseUrl, httpClient);
        this.httpRequestHandler = httpRequestHandler;
    }

    @Override
    public Attribute getTrusteeAttributeValueByKey(ParametersForGetTrusteeAttributeValueByKey parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(new String[]{"boolean"},
                new String[]{"everyone"}, new Object[]{parameters.isEveryone()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String", "String"},
                new String[]{"repoId", "attributeKey"},
                new Object[]{parameters.getRepoId(), parameters.getAttributeKey()});
        HttpResponse<Object> httpResponse = httpClient
                .get(baseUrl + "/v1/Repositories/{repoId}/Attributes/{attributeKey}")
                .queryString(queryParameters)
                .routeParam(pathParameters)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        if (httpResponse.getStatus() == 200) {
            try {
                String jsonString = new JSONObject(body).toString();
                return objectMapper.readValue(jsonString, Attribute.class);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            ProblemDetails problemDetails;
            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
            try {
                String jsonString = new JSONObject(body).toString();
                problemDetails = deserializeToProblemDetails(jsonString);
            } catch (IllegalStateException e) {
                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                        (parsingException.isPresent() ? parsingException
                                .get()
                                .getOriginalBody() : null), headersMap, null);
            }
            if (httpResponse.getStatus() == 400)
                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 401)
                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 403)
                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 404)
                throw new ApiException(decideErrorMessage(problemDetails, "Requested attribute key not found."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else if (httpResponse.getStatus() == 429)
                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
            else
                throw new RuntimeException(httpResponse.getStatusText());
        }
    }

    @Override
    public ODataValueContextOfListOfAttribute getTrusteeAttributeKeyValuePairs(
            ParametersForGetTrusteeAttributeKeyValuePairs parameters) {
        return doGetTrusteeAttributeKeyValuePairs(baseUrl + "/v1/Repositories/{repoId}/Attributes", parameters);
    }

    private ODataValueContextOfListOfAttribute doGetTrusteeAttributeKeyValuePairs(String url,
            ParametersForGetTrusteeAttributeKeyValuePairs parameters) {
        Map<String, Object> queryParameters = getParametersWithNonDefaultValue(
                new String[]{"boolean", "String", "String", "int", "int", "boolean"},
                new String[]{"everyone", "$select", "$orderby", "$top", "$skip", "$count"},
                new Object[]{parameters.isEveryone(), parameters.getSelect(), parameters.getOrderby(), parameters.getTop(), parameters.getSkip(), parameters.isCount()});
        Map<String, Object> pathParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"repoId"}, new Object[]{parameters.getRepoId()});
        Map<String, Object> headerParameters = getParametersWithNonDefaultValue(new String[]{"String"},
                new String[]{"prefer"}, new Object[]{parameters.getPrefer()});
        Map<String, String> headerParametersWithStringTypeValue = headerParameters
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()));

        ////////////////////////////////////
        int retryCount = 0;
        int maxRetries = 1;
        boolean shouldRetry = true;
        HttpRequestHandler httpRequestHandler = null;
        Request customRequest;
        HttpResponse<Object> httpResponse = null;
        while (retryCount <= maxRetries && shouldRetry) {
      //const beforeSendResult = await this._httpRequestHandler.beforeFetchRequestAsync(url, init);
            //customRequest = new RequestImpl();
            //BeforeSendResult beforeSendResult = httpRequestHandler.beforeSend(customRequest);
//            String absoluteUrl = "";
//            if (url.startsWith("http")) {
//                absoluteUrl = url;
//            } else {
//                String apiBasedAddress = beforeSendResult.getRegionalDomain();
//                //absoluteUrl = UrlUtils.combineURLs(apiBasedAddress, url);
//            }

            try {
                //response = await fetch(absoluteUrl, init);
                httpResponse = httpClient
                        .get(url)
                        .queryString(queryParameters)
                        .routeParam(pathParameters)
                        .headers(headerParametersWithStringTypeValue)
                        .asObject(Object.class);
                httpRequestHandler = this.httpRequestHandler;
                shouldRetry = httpRequestHandler.afterSend(new ResponseImpl((short) httpResponse.getStatus()));// || isRetryable(httpResponse,);
                        //(await this._httpRequestHandler.afterFetchResponseAsync(absoluteUrl, response, init)) ||
                //isRetryable(response, init);
                if (!shouldRetry) {
                    Object body = httpResponse.getBody();
                    if (httpResponse.getStatus() == 200) {
                        try {
                            String jsonString = new JSONObject(body).toString();
                            return objectMapper.readValue(jsonString, ODataValueContextOfListOfAttribute.class);
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                            return null;
                        }
                    } else {
                        ProblemDetails problemDetails;
                        Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
                        try {
                            String jsonString = new JSONObject(body).toString();
                            problemDetails = deserializeToProblemDetails(jsonString);
                        } catch (IllegalStateException e) {
                            Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
                            throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
                                    (parsingException.isPresent() ? parsingException
                                            .get()
                                            .getOriginalBody() : null), headersMap, null);
                        }
                        if (httpResponse.getStatus() == 400)
                            throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 401)
                            throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 403)
                            throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 404)
                            throw new ApiException(decideErrorMessage(problemDetails, "Not found."), httpResponse.getStatus(),
                                    httpResponse.getStatusText(), headersMap, problemDetails);
                        else if (httpResponse.getStatus() == 429)
                            throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
                                    httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
                        else
                            throw new RuntimeException(httpResponse.getStatusText());
                    }
                }
            } catch (Exception err) {
                if (retryCount >= maxRetries) {
                    throw err;
                }
                shouldRetry = true;
                System.err.println("Retrying fetch due to exception: "+ err);
            } finally {
                retryCount++;
            }
        }
        if (httpResponse == null){
            throw new IllegalStateException("Undefined response, there is a bug");
        }
        Object body = httpResponse.getBody();
        String jsonString = new JSONObject(body).toString();
        return objectMapper.readValue(jsonString, ODataValueContextOfListOfAttribute.class);


        ///////////////////////////


//        HttpResponse<Object> httpResponse = httpClient
//                .get(url)
//                .queryString(queryParameters)
//                .routeParam(pathParameters)
//                .headers(headerParametersWithStringTypeValue)
//                .asObject(Object.class);

//        Object body = httpResponse.getBody();
//        if (httpResponse.getStatus() == 200) {
//            try {
//                String jsonString = new JSONObject(body).toString();
//                return objectMapper.readValue(jsonString, ODataValueContextOfListOfAttribute.class);
//            } catch (IllegalStateException e) {
//                e.printStackTrace();
//                return null;
//            }
//        } else {
//            ProblemDetails problemDetails;
//            Map<String, String> headersMap = getHeadersMap(httpResponse.getHeaders());
//            try {
//                String jsonString = new JSONObject(body).toString();
//                problemDetails = deserializeToProblemDetails(jsonString);
//            } catch (IllegalStateException e) {
//                Optional<UnirestParsingException> parsingException = httpResponse.getParsingError();
//                throw new ApiException(httpResponse.getStatusText(), httpResponse.getStatus(),
//                        (parsingException.isPresent() ? parsingException
//                                .get()
//                                .getOriginalBody() : null), headersMap, null);
//            }
//            if (httpResponse.getStatus() == 400)
//                throw new ApiException(decideErrorMessage(problemDetails, "Invalid or bad request."),
//                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
//            else if (httpResponse.getStatus() == 401)
//                throw new ApiException(decideErrorMessage(problemDetails, "Access token is invalid or expired."),
//                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
//            else if (httpResponse.getStatus() == 403)
//                throw new ApiException(decideErrorMessage(problemDetails, "Access denied for the operation."),
//                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
//            else if (httpResponse.getStatus() == 404)
//                throw new ApiException(decideErrorMessage(problemDetails, "Not found."), httpResponse.getStatus(),
//                        httpResponse.getStatusText(), headersMap, problemDetails);
//            else if (httpResponse.getStatus() == 429)
//                throw new ApiException(decideErrorMessage(problemDetails, "Rate limit is reached."),
//                        httpResponse.getStatus(), httpResponse.getStatusText(), headersMap, problemDetails);
//            else
//                throw new RuntimeException(httpResponse.getStatusText());
//        }
    }

    @Override
    public ODataValueContextOfListOfAttribute getTrusteeAttributeKeyValuePairsNextLink(String nextLink,
            int maxPageSize) {
        return doGetTrusteeAttributeKeyValuePairs(nextLink,
                new ParametersForGetTrusteeAttributeKeyValuePairs().setPrefer(
                        mergeMaxSizeIntoPrefer(maxPageSize, null)));
    }

    @Override
    public void getTrusteeAttributeKeyValuePairsForEach(Function<ODataValueContextOfListOfAttribute, Boolean> callback,
            Integer maxPageSize, ParametersForGetTrusteeAttributeKeyValuePairs parameters) {
        parameters.setPrefer(mergeMaxSizeIntoPrefer(maxPageSize, parameters.getPrefer()));
        ODataValueContextOfListOfAttribute response = getTrusteeAttributeKeyValuePairs(parameters);
        while (response != null && callback.apply(response)) {
            String nextLink = response.getOdataNextLink();
            response = getTrusteeAttributeKeyValuePairsNextLink(nextLink, maxPageSize);
        }
    }
}
