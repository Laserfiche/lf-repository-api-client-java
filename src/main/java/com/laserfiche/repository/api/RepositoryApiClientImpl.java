package com.laserfiche.repository.api;

import com.laserfiche.api.client.deserialization.TokenClientObjectMapper;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.httphandlers.OAuthClientCredentialsHandler;
import com.laserfiche.api.client.httphandlers.UsernamePasswordHandler;
import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.clients.*;
import com.laserfiche.repository.api.clients.impl.*;
import com.laserfiche.repository.api.clients.impl.model.RepositoryInfo;
import kong.unirest.HttpResponse;
import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;
import kong.unirest.UnirestInstance;
import kong.unirest.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The Laserfiche Repository API client.
 */
public class RepositoryApiClientImpl implements RepositoryApiClient, AutoCloseable {
    private Map<String, String> defaultHeaders;
    private final UnirestInstance httpClient;
    private final AttributesClient attributesClient;
    private final AuditReasonsClient auditReasonsClient;
    private final EntriesClient entriesClient;
    private final FieldDefinitionsClient fieldDefinitionsClient;
    private final LinkDefinitionsClient linkDefinitionsClient;
    private final RepositoriesClient repositoriesClient;
    private final SearchesClient searchesClient;
    private final ServerSessionClient serverSessionClient;
    private final SimpleSearchesClient simpleSearchesClient;
    private final TagDefinitionsClient tagDefinitionsClient;
    private final TasksClient tasksClient;
    private final TemplateDefinitionsClient templateDefinitionsClient;

    protected RepositoryApiClientImpl(String baseUrl, HttpRequestHandler httpHandler) {
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }

        // Initialize object mapper
        ObjectMapper objectMapper = new TokenClientObjectMapper();

        // Initialize HTTP client
        httpClient = Unirest.spawnInstance();
        httpClient
                .config()
                .setObjectMapper(objectMapper);

        // Initialize repository API clients
        attributesClient = new AttributesClientImpl(baseUrl, httpClient, httpHandler);
        auditReasonsClient = new AuditReasonsClientImpl(baseUrl, httpClient, httpHandler);
        entriesClient = new EntriesClientImpl(baseUrl, httpClient, httpHandler);
        fieldDefinitionsClient = new FieldDefinitionsClientImpl(baseUrl, httpClient, httpHandler);
        linkDefinitionsClient = new LinkDefinitionsClientImpl(baseUrl, httpClient, httpHandler);
        repositoriesClient = new RepositoriesClientImpl(baseUrl, httpClient, httpHandler);
        searchesClient = new SearchesClientImpl(baseUrl, httpClient, httpHandler);
        serverSessionClient = new ServerSessionClientImpl(baseUrl, httpClient, httpHandler);
        simpleSearchesClient = new SimpleSearchesClientImpl(baseUrl, httpClient, httpHandler);
        tagDefinitionsClient = new TagDefinitionsClientImpl(baseUrl, httpClient, httpHandler);
        tasksClient = new TasksClientImpl(baseUrl, httpClient, httpHandler);
        templateDefinitionsClient = new TemplateDefinitionsClientImpl(baseUrl, httpClient, httpHandler);
    }

    /**
     * Creates a new Laserfiche repository client that will use Laserfiche Cloud OAuth client credentials to get access tokens.
     *
     * @param servicePrincipalKey The service principal key created for the service principal from the Laserfiche Account Administration.
     * @param accessKey           The access key exported from the Laserfiche Developer Console.
     * @param baseUrlDebug        Optional override for the Laserfiche repository API base url.
     * @return {@link RepositoryApiClient}
     */
    public static RepositoryApiClient createFromAccessKey(String servicePrincipalKey, AccessKey accessKey, String scope,
            String baseUrlDebug) {
        if (baseUrlDebug == null) {
            baseUrlDebug = "https://api." + accessKey.getDomain() + "/repository";
        }
        HttpRequestHandler oauthHandler = new OAuthClientCredentialsHandler(servicePrincipalKey, accessKey, scope);
        return new RepositoryApiClientImpl(baseUrlDebug, oauthHandler);
    }

    /**
     * Creates a new Laserfiche repository client that will use Laserfiche Cloud OAuth client credentials to get access tokens.
     *
     * @param servicePrincipalKey The service principal key created for the service principal from the Laserfiche Account Administration.
     * @param accessKey           The access key exported from the Laserfiche Developer Console.
     * @param scope               The requested space-delimited scopes for the access token.
     * @return {@link RepositoryApiClient}
     */
    public static RepositoryApiClient createFromAccessKey(String servicePrincipalKey, AccessKey accessKey,
            String scope) {
        return createFromAccessKey(servicePrincipalKey, accessKey, scope, null);
    }

    /**
     * Creates a new Laserfiche repository client that will use Laserfiche Cloud OAuth client credentials to get access tokens.
     *
     * @param servicePrincipalKey The service principal key created for the service principal from the Laserfiche Account Administration.
     * @param accessKey           The access key exported from the Laserfiche Developer Console.
     * @return {@link RepositoryApiClient}
     */
    public static RepositoryApiClient createFromAccessKey(String servicePrincipalKey, AccessKey accessKey) {
        return createFromAccessKey(servicePrincipalKey, accessKey, null);
    }

    /**
     * Creates a new Laserfiche repository client that will use username and password to get access tokens for Laserfiche API.
     * Password credentials grant type is implemented by the Laserfiche Self-Hosted API server. Not available in cloud.
     *
     * @param repositoryId The repository ID.
     * @param username     The username.
     * @param password     The password.
     * @param baseUrl      API server base URL e.g., https://{APIServerName}/LFRepositoryAPI.
     * @return {@link RepositoryApiClient}
     */
    public static RepositoryApiClient createFromUsernamePassword(String repositoryId, String username, String password,
            String baseUrl) {
        HttpRequestHandler usernamePasswordHandler = new UsernamePasswordHandler(repositoryId, username, password,
                baseUrl, null);
        return new RepositoryApiClientImpl(baseUrl, usernamePasswordHandler);
    }

    /**
     * Returns the repository resource list that current user has access to given the API server base URL. Only available in Laserfiche Self-Hosted.
     *
     * @param url API server base URL e.g., https://{APIServerName}/LFRepositoryAPI
     * @return Get the respository resource list successfully.
     */
    public static RepositoryInfo[] getSelfHostedRepositoryList(String url) {
        Map<String, String> headerKeyValuePairs = new HashMap<>();
        headerKeyValuePairs.put("accept", "application/json");
        HttpResponse<Object> httpResponse = null;
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        String baseUrl = url + "/v1/Repositories";
        String responseJson;
        ObjectMapper objectMapper = new TokenClientObjectMapper();
        UnirestInstance httpClient = Unirest.spawnInstance();
        httpClient
                .config()
                .setObjectMapper(objectMapper);
        httpResponse = httpClient
                .get(baseUrl)
                .headers(headerKeyValuePairs)
                .asObject(Object.class);
        Object body = httpResponse.getBody();
        responseJson = new JSONArray(((ArrayList) body).toArray()).toString();
        return objectMapper.readValue(responseJson, RepositoryInfo[].class);
    }

    @Override
    public void setDefaultRequestHeaders(Map<String, String> defaultRequestHeaders) {
        defaultHeaders = defaultRequestHeaders;
        httpClient
                .config()
                .clearDefaultHeaders();
        for (String Key : defaultRequestHeaders.keySet()) {
            httpClient
                    .config()
                    .setDefaultHeader(Key, defaultHeaders.get(Key));
        }
    }

    @Override
    public Map<String, String> getDefaultRequestHeaders() {
        return defaultHeaders;
    }

    @Override
    public AttributesClient getAttributesClient() {
        return attributesClient;
    }

    @Override
    public AuditReasonsClient getAuditReasonsClient() {
        return auditReasonsClient;
    }

    @Override
    public EntriesClient getEntriesClient() {
        return entriesClient;
    }

    @Override
    public FieldDefinitionsClient getFieldDefinitionsClient() {
        return fieldDefinitionsClient;
    }

    @Override
    public LinkDefinitionsClient getLinkDefinitionsClient() {
        return linkDefinitionsClient;
    }

    @Override
    public RepositoriesClient getRepositoryClient() {
        return repositoriesClient;
    }

    @Override
    public SearchesClient getSearchesClient() {
        return searchesClient;
    }

    @Override
    public SimpleSearchesClient getSimpleSearchesClient() {
        return simpleSearchesClient;
    }

    @Override
    public TagDefinitionsClient getTagDefinitionsClient() {
        return tagDefinitionsClient;
    }

    @Override
    public TasksClient getTasksClient() {
        return tasksClient;
    }

    @Override
    public TemplateDefinitionsClient getTemplateDefinitionClient() {
        return templateDefinitionsClient;
    }

    /**
     * Release the thread pool held by the underlying HTTP clients.
     */
    @Override
    public void close() {
        httpClient.close();
    }
}
