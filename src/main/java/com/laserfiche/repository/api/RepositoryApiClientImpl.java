package com.laserfiche.repository.api;

import com.laserfiche.api.client.deserialization.TokenClientObjectMapper;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.httphandlers.OAuthClientCredentialsHandler;
import com.laserfiche.api.client.httphandlers.UsernamePasswordHandler;
import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.clients.*;
import com.laserfiche.repository.api.clients.impl.*;
import kong.unirest.ObjectMapper;
import kong.unirest.Unirest;
import kong.unirest.UnirestInstance;

import java.util.Map;

/**
 * The Laserfiche Repository API client.
 */
public class RepositoryApiClientImpl implements RepositoryApiClient, AutoCloseable {
    private Map<String, String> defaultHeaders;
    private final RepositoryApiClientInterceptor interceptor;
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

    protected RepositoryApiClientImpl(RepositoryApiClientInterceptor interceptor, String baseUrl, HttpRequestHandler httpHandler) {
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
        }

        // Initialize object mapper
        ObjectMapper objectMapper = new TokenClientObjectMapper();

        // Initialize HTTP client
        httpClient = Unirest.spawnInstance();
        httpClient
                .config()
                .setObjectMapper(objectMapper)
                .interceptor(interceptor);
        this.interceptor = interceptor;

        // Initialize repository API clients
        attributesClient = new AttributesClientImpl(baseUrl, httpClient, httpHandler);
        auditReasonsClient = new AuditReasonsClientImpl(baseUrl, httpClient);
        entriesClient = new EntriesClientImpl(baseUrl, httpClient);
        fieldDefinitionsClient = new FieldDefinitionsClientImpl(baseUrl, httpClient);
        linkDefinitionsClient = new LinkDefinitionsClientImpl(baseUrl, httpClient);
        repositoriesClient = new RepositoriesClientImpl(baseUrl, httpClient);
        searchesClient = new SearchesClientImpl(baseUrl, httpClient);
        serverSessionClient = new ServerSessionClientImpl(baseUrl, httpClient);
        simpleSearchesClient = new SimpleSearchesClientImpl(baseUrl, httpClient);
        tagDefinitionsClient = new TagDefinitionsClientImpl(baseUrl, httpClient);
        tasksClient = new TasksClientImpl(baseUrl, httpClient);
        templateDefinitionsClient = new TemplateDefinitionsClientImpl(baseUrl, httpClient);
    }

    /**
     * Creates a new Laserfiche repository client that will use Laserfiche Cloud OAuth client credentials to get access tokens.
     * @param servicePrincipalKey The service principal key created for the service principal from the Laserfiche Account Administration.
     * @param accessKey The access key exported from the Laserfiche Developer Console.
     * @param baseUrlDebug Optional override for the Laserfiche repository API base url.
     * @return {@link RepositoryApiClient}
     */
    public static RepositoryApiClient createFromAccessKey(String servicePrincipalKey, AccessKey accessKey,
            String baseUrlDebug) {
        if (baseUrlDebug == null) {
            baseUrlDebug = "https://api." + accessKey.getDomain() + "/repository";
        }
        RepositoryApiClientInterceptor interceptor = new OAuthInterceptor(servicePrincipalKey, accessKey);
        HttpRequestHandler oauthHandler = new OAuthClientCredentialsHandler(servicePrincipalKey, accessKey);
        return new RepositoryApiClientImpl(interceptor, baseUrlDebug, oauthHandler);
    }

    /**
     * Creates a new Laserfiche repository client that will use Laserfiche Cloud OAuth client credentials to get access tokens.
     * @param servicePrincipalKey The service principal key created for the service principal from the Laserfiche Account Administration.
     * @param accessKey The access key exported from the Laserfiche Developer Console.
     * @return {@link RepositoryApiClient}
     */
    public static RepositoryApiClient createFromAccessKey(String servicePrincipalKey, AccessKey accessKey) {
        return createFromAccessKey(servicePrincipalKey, accessKey, null);
    }

    /**
     * Creates a new Laserfiche repository client that will use username and password to get access tokens for Laserfiche API.
     * Password credentials grant type is implemented by the Laserfiche Self-Hosted API server. Not available in cloud.
     * @param repositoryId The repository ID.
     * @param username The username.
     * @param password The password.
     * @param baseUrl API server base URL e.g., https://{APIServerName}/LFRepositoryAPI.
     * @return {@link RepositoryApiClient}
     */
    public static RepositoryApiClient createFromUsernamePassword(String repositoryId, String username, String password,
            String baseUrl) {
        RepositoryApiClientInterceptor interceptor = new SelfHostedInterceptor(repositoryId, username, password,
                baseUrl, null);
        HttpRequestHandler usernamePasswordHandler = new UsernamePasswordHandler(repositoryId, username, password, baseUrl, null);
        return new RepositoryApiClientImpl(interceptor, baseUrl, usernamePasswordHandler);
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
        if (interceptor != null) {
            interceptor.close();
        }
        httpClient.close();
    }
}
