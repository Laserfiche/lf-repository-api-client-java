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
    AttributesClientImpl getAttributesClient();

    /**
     * @return The Laserfiche Repository Audit Reasons API client.
     */
    AuditReasonsClientImpl getAuditReasonsClient();

    /**
     * @return The Laserfiche Repository Entries API client.
     */
    EntriesClientImpl getEntriesClient();

    /**
     * @return The Laserfiche Repository Field Definitions API client.
     */
    FieldDefinitionsClientImpl getFieldDefinitionsClient();

    /**
     * @return The Laserfiche Repository Repositories API client.
     */
    RepositoriesClientImpl getRepositoryClient();

    /**
     * @return The Laserfiche Repository Searches API client.
     */
    SearchesClientImpl getSearchesClient();

    /**
     * @return The Laserfiche Repository Simple Searches API client.
     */
    SimpleSearchesClientImpl getSimpleSearchesClient();

    /**
     * @return The Laserfiche Repository Tag Definitions API client.
     */
    TagDefinitionsClientImpl getTagDefinitionsClient();

    /**
     * @return The Laserfiche Repository Tasks API client.
     */
    TasksClientImpl getTasksClient();

    /**
     * @return The Laserfiche Repository Template Definitions API client.
     */
    TemplateDefinitionsClientImpl getTemplateDefinitionClient();

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
