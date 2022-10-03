package com.laserfiche.repository.api;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.clients.*;
import com.laserfiche.repository.api.clients.impl.*;
import com.laserfiche.repository.api.clients.impl.deserialization.RepositoryClientObjectMapper;
import kong.unirest.Unirest;
import kong.unirest.UnirestInstance;

import java.util.Map;

public class RepositoryApiClientImpl implements RepositoryApiClient, AutoCloseable {
    private Map<String, String> defaultHeaders;
    private UnirestInstance httpClient;
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

    protected RepositoryApiClientImpl(String servicePrincipalKey, AccessKey accessKey, String baseUrlDebug) {
        String baseUrl = baseUrlDebug != null ? baseUrlDebug : "https://api." + accessKey.domain + "/repository";
        httpClient = Unirest.spawnInstance();

        httpClient
                .config()
                .setObjectMapper(new RepositoryClientObjectMapper())
                .interceptor(new OAuthInterceptor(servicePrincipalKey, accessKey));

        attributesClient = new AttributesClientImpl(baseUrl, httpClient);
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

    public static RepositoryApiClient CreateFromAccessKey(String servicePrincipalKey, AccessKey accessKey,
            String baseUrlDebug) {
        return new RepositoryApiClientImpl(servicePrincipalKey, accessKey, baseUrlDebug);
    }

    public static RepositoryApiClient CreateFromAccessKey(String servicePrincipalKey, AccessKey accessKey) {
        return CreateFromAccessKey(servicePrincipalKey, accessKey, null);
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
     * From the AutoCloseable interface
     */
    @Override
    public void close() {
        httpClient.shutDown();
    }
}
