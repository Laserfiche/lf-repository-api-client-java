package com.laserfiche.repository.api;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.clients.*;
import com.laserfiche.repository.api.clients.impl.*;

import java.util.Map;

public class RepositoryApiClientImpl implements RepositoryApiClient {
    private Map<String, String> defaultHeaders;
    private final AttributesClient attributesClient;
    private final AuditReasonsClient auditReasonsClient;
    private final EntriesClient entriesClient;
    private final FieldDefinitionsClient fieldDefinitionsClient;
    private final LinkDefinitionsClient linkDefinitionsClient;
    private final RepositoriesClient repositoriesClient;
    private final SearchesClient searchesClient;
    private final SimpleSearchesClient simpleSearchesClient;
    private final TagDefinitionsClient tagDefinitionsClient;
    private final TasksClient tasksClient;
    private final TemplateDefinitionsClient templateDefinitionsClient;

    protected RepositoryApiClientImpl(String servicePrincipalKey, AccessKey accessKey, String baseUrlDebug) {
        String baseUrl = baseUrlDebug != null ? baseUrlDebug : "https://api." + accessKey.domain + "/repository";
        attributesClient = new AttributesClientImpl(baseUrl);
        auditReasonsClient = new AuditReasonsClientImpl(baseUrl);
        entriesClient = new EntriesClientImpl(baseUrl);
        fieldDefinitionsClient = new FieldDefinitionsClientImpl(baseUrl);
        linkDefinitionsClient = new LinkDefinitionsClientImpl(baseUrl);
        repositoriesClient = new RepositoriesClientImpl(baseUrl);
        searchesClient = new SearchesClientImpl(baseUrl);
        simpleSearchesClient = new SimpleSearchesClientImpl(baseUrl);
        tagDefinitionsClient = new TagDefinitionsClientImpl(baseUrl);
        tasksClient = new TasksClientImpl(baseUrl);
        templateDefinitionsClient = new TemplateDefinitionsClientImpl(baseUrl);
    }

    public static RepositoryApiClient CreateFromAccessKey(String servicePrincipalKey, AccessKey accessKey, String baseUrlDebug) {
        return new RepositoryApiClientImpl(servicePrincipalKey, accessKey, baseUrlDebug);
    }

    public static RepositoryApiClient CreateFromAccessKey(String servicePrincipalKey, AccessKey accessKey) {
        return CreateFromAccessKey(servicePrincipalKey, accessKey, null);
    }

    @Override
    public void setDefaultRequestHeaders(Map<String, String> defaultRequestHeaders) {
        defaultHeaders = defaultRequestHeaders;
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
}
