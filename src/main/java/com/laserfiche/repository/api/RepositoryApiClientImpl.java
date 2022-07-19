package com.laserfiche.repository.api;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.clients.*;
import com.laserfiche.repository.api.clients.impl.*;
import com.laserfiche.repository.api.serialization.GsonCustomConverterFactory;
import com.laserfiche.repository.api.serialization.RepositoryApiDeserializer;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.util.Map;

public class RepositoryApiClientImpl implements RepositoryApiClient {
    private final Retrofit.Builder clientBuilder;
    private final OkHttpClient.Builder okBuilder;
    private Map<String, String> defaultHeaders;
    private AttributesClient attributesClient;
    private AuditReasonsClient auditReasonsClient;
    private EntriesClient entriesClient;
    private FieldDefinitionsClient fieldDefinitionsClient;
    private RepositoriesClient repositoriesClient;
    private SearchesClient searchesClient;
    private SimpleSearchesClient simpleSearchesClient;
    private TagDefinitionsClient tagDefinitionsClient;
    private TasksClient tasksClient;
    private TemplateDefinitionsClient templateDefinitionsClient;

    protected RepositoryApiClientImpl(String servicePrincipalKey, AccessKey accessKey, String baseUrlDebug) {
        String baseUrl = baseUrlDebug != null ? baseUrlDebug : "https://api." + accessKey.getDomain() + "/repository/";
        RepositoryApiDeserializer json = new RepositoryApiDeserializer();

        okBuilder = new OkHttpClient.Builder();
        addDefaultHeaderInterceptor();
        okBuilder.addInterceptor(new OAuthInterceptor(servicePrincipalKey, accessKey));

        clientBuilder = new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonCustomConverterFactory.create(json.getGson()));
    }

    public static RepositoryApiClientImpl CreateFromAccessKey(String servicePrincipalKey, AccessKey accessKey, String baseUrlDebug) {
        return new RepositoryApiClientImpl(servicePrincipalKey, accessKey, baseUrlDebug);
    }

    public static RepositoryApiClientImpl CreateFromAccessKey(String servicePrincipalKey, AccessKey accessKey) {
        return CreateFromAccessKey(servicePrincipalKey, accessKey, null);
    }

    private <C> C createClient(Class<C> clientInterface) {
        return clientBuilder.client(okBuilder.build())
                .build()
                .create(clientInterface);
    }

    private void addDefaultHeaderInterceptor() {
        okBuilder.addInterceptor(chain -> {
            okhttp3.Request.Builder builder = chain.request().newBuilder();
            builder.headers(Headers.of(defaultHeaders));
            okhttp3.Request request = builder.build();
            return chain.proceed(request);
        });
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
        if (attributesClient == null) {
            attributesClient = new AttributesClient();
            attributesClient.setClient(createClient(AttributesApi.class));
        }
        return attributesClient;
    }

    @Override
    public AuditReasonsClient getAuditReasonsClient() {
        if (auditReasonsClient == null) {
            auditReasonsClient = new AuditReasonsClient();
            auditReasonsClient.setClient(createClient(AuditReasonsApi.class));
        }
        return auditReasonsClient;
    }

    @Override
    public EntriesClient getEntriesClient() {
        if (entriesClient == null) {
            entriesClient = new EntriesClient();
            entriesClient.setClient(createClient(EntriesApi.class));
        }
        return entriesClient;
    }

    @Override
    public FieldDefinitionsClient getFieldDefinitionsClient() {
        if (fieldDefinitionsClient == null) {
            fieldDefinitionsClient = new FieldDefinitionsClient();
            fieldDefinitionsClient.setClient(createClient(FieldDefinitionsApi.class));
        }
        return fieldDefinitionsClient;
    }

    @Override
    public RepositoriesClient getRepositoryClient() {
        if (repositoriesClient == null) {
            repositoriesClient = new RepositoriesClient();
            repositoriesClient.setClient(createClient(RepositoriesApi.class));
        }
        return repositoriesClient;
    }

    @Override
    public SearchesClient getSearchesClient() {
        if (searchesClient == null) {
            searchesClient = new SearchesClient();
            searchesClient.setClient(createClient(SearchesApi.class));
        }
        return searchesClient;
    }

    @Override
    public SimpleSearchesClient getSimpleSearchesClient() {
        if (simpleSearchesClient == null) {
            simpleSearchesClient = new SimpleSearchesClient();
            simpleSearchesClient.setClient(createClient(SimpleSearchesApi.class));
        }
        return simpleSearchesClient;
    }

    @Override
    public TagDefinitionsClient getTagDefinitionsClient() {
        if (tagDefinitionsClient == null) {
            tagDefinitionsClient = new TagDefinitionsClient();
            tagDefinitionsClient.setClient(createClient(TagDefinitionsApi.class));
        }
        return tagDefinitionsClient;
    }

    @Override
    public TasksClient getTasksClient() {
        if (tasksClient == null) {
            tasksClient = new TasksClient();
            tasksClient.setClient(createClient(TasksApi.class));
        }
        return tasksClient;
    }

    @Override
    public TemplateDefinitionsClient getTemplateDefinitionClient() {
        if (templateDefinitionsClient == null) {
            templateDefinitionsClient = new TemplateDefinitionsClient();
            templateDefinitionsClient.setClient(createClient(TemplateDefinitionsApi.class));
        }
        return templateDefinitionsClient;
    }
}
