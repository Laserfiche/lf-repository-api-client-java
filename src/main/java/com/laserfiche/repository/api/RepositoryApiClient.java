package com.laserfiche.repository.api;

import com.laserfiche.repository.api.clients.*;

import java.util.Map;

/**
 * The Laserfiche Repository API client.
 */
public interface RepositoryApiClient {
    /**
     * @return The Laserfiche Repository Attributes API client.
     */
    AttributesClient getAttributesClient();

    /**
     * @return The Laserfiche Repository Audit Reasons API client.
     */
    AuditReasonsClient getAuditReasonsClient();

    /**
     * @return The Laserfiche Repository Entries API client.
     */
    EntriesClient getEntriesClient();

    /**
     * @return The Laserfiche Repository Field Definitions API client.
     */
    FieldDefinitionsClient getFieldDefinitionsClient();

    /**
     * @return The Laserfiche Repository Repositories API client.
     */
    RepositoriesClient getRepositoryClient();

    LinkDefinitionsClient getLinkDefinitionsClient();

    /**
     * @return The Laserfiche Repository Searches API client.
     */
    SearchesClient getSearchesClient();

    /**
     * @return The Laserfiche Repository Server Session API client.
     */
    ServerSessionClient getServerSessionClient();

    /**
     * @return The Laserfiche Repository Simple Searches API client.
     */
    SimpleSearchesClient getSimpleSearchesClient();

    /**
     * @return The Laserfiche Repository Tag Definitions API client.
     */
    TagDefinitionsClient getTagDefinitionsClient();

    /**
     * @return The Laserfiche Repository Tasks API client.
     */
    TasksClient getTasksClient();

    /**
     * @return The Laserfiche Repository Template Definitions API client.
     */
    TemplateDefinitionsClient getTemplateDefinitionClient();

    /**
     * Set default headers that will be used for all requests.
     * @param defaultHeaders A key value pair of header name and value.
     */
    void setDefaultRequestHeaders(Map<String, String> defaultHeaders);

    /**
     * @return Default request headers in a key value pair of header name and value.
     */
    Map<String, String> getDefaultRequestHeaders();
}
