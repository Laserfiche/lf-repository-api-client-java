package com.laserfiche.repository.api;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.client.*;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RepositoryApiClientImpl implements RepositoryApiClient {
    private final Retrofit.Builder clientBuilder;
    private final OkHttpClient.Builder okBuilder;

    private AttributesApi attributesApi;
    private AttributesClient attributesClient;
    private AuditReasonsApi auditReasonsApi;
    private AuditReasonsClient auditReasonsClient;
    private EntriesApi entriesApi;
    private EntriesClient entriesClient;
    private FieldDefinitionsApi fieldDefinitionsApi;
    private FieldDefinitionsClient fieldDefinitionsClient;
    private RepositoriesApi repositoriesApi;
    private RepositoriesClient repositoriesClient;
    private SearchesApi searchesApi;
    private SearchesClient searchesClient;
    private SimpleSearchesApi simpleSearchesApi;
    private SimpleSearchesClient simpleSearchesClient;
    private TagDefinitionsApi tagDefinitionsApi;
    private TagDefinitionsClient tagDefinitionsClient;
    private TasksApi tasksApi;
    private TasksClient tasksClient;
    private TemplateDefinitionsApi templateDefinitionsApi;
    private TemplateDefinitionsClient templateDefinitionsClient;

    protected RepositoryApiClientImpl(String servicePrincipalKey, AccessKey accessKey, String baseUrlDebug) {
        String baseUrl = baseUrlDebug != null ? baseUrlDebug : "https://api." + accessKey.getDomain() + "/repository/";

        okBuilder = new OkHttpClient.Builder();
        okBuilder.addInterceptor(new OAuthInterceptor(servicePrincipalKey, accessKey));
        ClientJson json = new ClientJson();
        clientBuilder = new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonCustomConverterFactory.create(json.getGson()));
    }

    public static RepositoryApiClient CreateFromAccessKey(String servicePrincipalKey, AccessKey accessKey, String baseUrlDebug) {
        return new RepositoryApiClientImpl(servicePrincipalKey, accessKey, baseUrlDebug);
    }

    public static RepositoryApiClient CreateFromAccessKey(String servicePrincipalKey, AccessKey accessKey) {
        return CreateFromAccessKey(servicePrincipalKey, accessKey, null);
    }

    private <C> C createClient(Class<C> clientInterface) {
        return clientBuilder.client(okBuilder.build())
                .build()
                .create(clientInterface);
    }

    @Override
    public AttributesApi getAttributesClient() {
        if (attributesClient == null) {
            attributesClient = new AttributesClient();
        }
        if (attributesApi == null) {
            attributesApi = createClient(AttributesApi.class);
        }
        return attributesApi;
    }

    @Override
    public AuditReasonsApi getAuditReasonsClient() {
        if (auditReasonsApi == null) {
            auditReasonsApi = createClient(AuditReasonsApi.class);
        }
        return auditReasonsApi;
    }

    @Override
    public EntriesApi getEntriesClient() {
        if (entriesApi == null) {
            entriesApi = createClient(EntriesApi.class);
        }
        return entriesApi;
    }

    @Override
    public FieldDefinitionsApi getFieldDefinitionsClient() {
        if (fieldDefinitionsApi == null) {
            fieldDefinitionsApi = createClient(FieldDefinitionsApi.class);
        }
        return fieldDefinitionsApi;
    }

    @Override
    public RepositoriesApi getRepositoryClient() {
        if (repositoriesApi == null) {
            repositoriesApi = createClient(RepositoriesApi.class);
        }
        return repositoriesApi;
    }

    @Override
    public SearchesApi getSearchesClient() {
        if (searchesApi == null) {
            searchesApi = createClient(SearchesApi.class);
        }
        return searchesApi;
    }

    @Override
    public SimpleSearchesApi getSimpleSearchesClient() {
        if (simpleSearchesApi == null) {
            simpleSearchesApi = createClient(SimpleSearchesApi.class);
        }
        return simpleSearchesApi;
    }

    @Override
    public TagDefinitionsApi getTagDefinitionsClient() {
        if (tagDefinitionsApi == null) {
            tagDefinitionsApi = createClient(TagDefinitionsApi.class);
        }
        return tagDefinitionsApi;
    }

    @Override
    public TasksApi getTasksClient() {
        if (tasksApi == null) {
            tasksApi = createClient(TasksApi.class);
        }
        return tasksApi;
    }

    @Override
    public TemplateDefinitionsApi getTemplateDefinitionClient() {
        if (templateDefinitionsApi == null) {
            templateDefinitionsApi = createClient(TemplateDefinitionsApi.class);
        }
        return templateDefinitionsApi;
    }
}
