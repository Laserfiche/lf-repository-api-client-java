package com.laserfiche.repository.api;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.clients.*;
import com.laserfiche.repository.api.serialization.GsonCustomConverterFactory;
import com.laserfiche.repository.api.serialization.RepositoryApiDeserializer;
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
    private LinkDefinitionsClient linkDefinitionsClient;
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

    public static RepositoryApiClient CreateFromAccessKey(String servicePrincipalKey, AccessKey accessKey, String baseUrlDebug) {
        return new RepositoryApiClientImpl(servicePrincipalKey, accessKey, baseUrlDebug);
    }

    public static RepositoryApiClient CreateFromAccessKey(String servicePrincipalKey, AccessKey accessKey) {
        return CreateFromAccessKey(servicePrincipalKey, accessKey, null);
    }

    private void addDefaultHeaderInterceptor() {
        okBuilder.addInterceptor(chain -> {
            okhttp3.Request.Builder builder = chain.request().newBuilder();
            if (defaultHeaders != null) {
                defaultHeaders.forEach(builder::addHeader);
            }
            okhttp3.Request request = builder.build();
            return chain.proceed(request);
        });
    }

    @Override
    public void setDefaultRequestHeaders(Map<String, String> defaultRequestHeaders) {
        defaultHeaders = defaultRequestHeaders;
        okBuilder.addInterceptor(chain -> chain.proceed(chain.request().newBuilder().addHeader(String.valueOf(defaultHeaders.keySet()), "true").build()));
    }

    @Override
    public Map<String, String> getDefaultRequestHeaders() {
        return defaultHeaders;
    }

    @Override
    public AttributesClient getAttributesClient() {
        if (attributesClient == null) {
            attributesClient = new AttributesClientImpl(clientBuilder, okBuilder);
        }
        return attributesClient;
    }

    @Override
    public AuditReasonsClient getAuditReasonsClient() {
        if (auditReasonsClient == null) {
            auditReasonsClient = new AuditReasonsClientImpl(clientBuilder, okBuilder);
        }
        return auditReasonsClient;
    }

    @Override
    public EntriesClient getEntriesClient() {
        if (entriesClient == null) {
            entriesClient = new EntriesClientImpl(clientBuilder, okBuilder);
        }
        return entriesClient;
    }

    @Override
    public FieldDefinitionsClient getFieldDefinitionsClient() {
        if (fieldDefinitionsClient == null) {
            fieldDefinitionsClient = new FieldDefinitionsClientImpl(clientBuilder, okBuilder);
        }
        return fieldDefinitionsClient;
    }

    @Override
    public LinkDefinitionsClient getLinkDefinitionsClient() {
        if (linkDefinitionsClient == null) {
            linkDefinitionsClient = new LinkDefinitionsClientImpl(clientBuilder, okBuilder);
        }
        return linkDefinitionsClient;
    }

    @Override
    public RepositoriesClient getRepositoryClient() {
        if (repositoriesClient == null) {
            repositoriesClient = new RepositoriesClientImpl(clientBuilder, okBuilder);
        }
        return repositoriesClient;
    }

    @Override
    public SearchesClient getSearchesClient() {
        if (searchesClient == null) {
            searchesClient = new SearchesClientImpl(clientBuilder, okBuilder);
        }
        return searchesClient;
    }

    @Override
    public SimpleSearchesClient getSimpleSearchesClient() {
        if (simpleSearchesClient == null) {
            simpleSearchesClient = new SimpleSearchesClientImpl(clientBuilder, okBuilder);
        }
        return simpleSearchesClient;
    }

    @Override
    public TagDefinitionsClient getTagDefinitionsClient() {
        if (tagDefinitionsClient == null) {
            tagDefinitionsClient = new TagDefinitionsClientImpl(clientBuilder, okBuilder);
        }
        return tagDefinitionsClient;
    }

    @Override
    public TasksClient getTasksClient() {
        if (tasksClient == null) {
            tasksClient = new TasksClientImpl(clientBuilder, okBuilder);
        }
        return tasksClient;
    }

    @Override
    public TemplateDefinitionsClient getTemplateDefinitionClient() {
        if (templateDefinitionsClient == null) {
            templateDefinitionsClient = new TemplateDefinitionsClientImpl(clientBuilder, okBuilder);
        }
        return templateDefinitionsClient;
    }
}
