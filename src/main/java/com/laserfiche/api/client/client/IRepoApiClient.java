package com.laserfiche.api.client.client;

import com.laserfiche.api.client.apiserver.*;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;

public interface IRepoApiClient {
    HttpRequestHandler DefaultRequestHeaders = null;
    AttributesApi attributeClient = null;
    AuditReasonsApi auditClient = null;
    EntriesApi entriesClient = null;
    FieldDefinitionsApi fieldDefinitionsClient = null;
    RepositoriesApi repositoriesClient = null;
    SearchesApi searchClient = null;
    ServerSessionApi serverSessionClient = null;
    SimpleSearchesApi simpleSearchesClient = null;
    TagDefinitionsApi tagDefinitionsClient = null;
    TasksApi taskClient = null;
    TemplateDefinitionsApi templateDefinitionsClient = null;
}
