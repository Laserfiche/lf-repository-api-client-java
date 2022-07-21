package com.laserfiche.repository.api;

import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.clients.*;
import com.laserfiche.repository.api.clients.impl.*;
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
    private AuditReasonsClientImpl auditReasonsClient;
    private EntriesClientImpl entriesClient;
    private FieldDefinitionsClientImpl fieldDefinitionsClient;
    private LinkDefinitionsClientImpl linkDefinitionsClient;
    private RepositoriesClientImpl repositoriesClient;
    private SearchesClientImpl searchesClient;
    private SimpleSearchesClientImpl simpleSearchesClient;
    private TagDefinitionsClientImpl tagDefinitionsClient;
    private TasksClientImpl tasksClient;
    private TemplateDefinitionsClientImpl templateDefinitionsClient;

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
            defaultHeaders.forEach(builder::addHeader);
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
            attributesClient = new AttributesClientImpl(clientBuilder, okBuilder);
        }
        return attributesClient;
    }

    @Override
    public AuditReasonsClientImpl getAuditReasonsClient() {
        if (auditReasonsClient == null) {
            auditReasonsClient = new AuditReasonsClientImpl(clientBuilder, okBuilder);
        }
        return auditReasonsClient;
    }

    @Override
    public EntriesClientImpl getEntriesClient() {
        if (entriesClient == null) {
            entriesClient = new EntriesClientImpl(clientBuilder, okBuilder);
        }
        return entriesClient;
    }

    @Override
    public FieldDefinitionsClientImpl getFieldDefinitionsClient() {
        if (fieldDefinitionsClient == null) {
            fieldDefinitionsClient = new FieldDefinitionsClientImpl(clientBuilder, okBuilder);
        }
        return fieldDefinitionsClient;
    }

    @Override
    public LinkDefinitionsClientImpl getLinkDefinitionsClient() {
        if (linkDefinitionsClient == null) {
            linkDefinitionsClient = new LinkDefinitionsClientImpl(clientBuilder, okBuilder);
        }
        return linkDefinitionsClient;
    }

    @Override
    public RepositoriesClientImpl getRepositoryClient() {
        if (repositoriesClient == null) {
            repositoriesClient = new RepositoriesClientImpl(clientBuilder, okBuilder);
        }
        return repositoriesClient;
    }

    @Override
    public SearchesClientImpl getSearchesClient() {
        if (searchesClient == null) {
            searchesClient = new SearchesClientImpl(clientBuilder, okBuilder);
        }
        return searchesClient;
    }

    @Override
    public SimpleSearchesClientImpl getSimpleSearchesClient() {
        if (simpleSearchesClient == null) {
            simpleSearchesClient = new SimpleSearchesClientImpl(clientBuilder, okBuilder);
        }
        return simpleSearchesClient;
    }

    @Override
    public TagDefinitionsClientImpl getTagDefinitionsClient() {
        if (tagDefinitionsClient == null) {
            tagDefinitionsClient = new TagDefinitionsClientImpl(clientBuilder, okBuilder);
        }
        return tagDefinitionsClient;
    }

    @Override
    public TasksClientImpl getTasksClient() {
        if (tasksClient == null) {
            tasksClient = new TasksClientImpl(clientBuilder, okBuilder);
        }
        return tasksClient;
    }

    @Override
    public TemplateDefinitionsClientImpl getTemplateDefinitionClient() {
        if (templateDefinitionsClient == null) {
            templateDefinitionsClient = new TemplateDefinitionsClientImpl(clientBuilder, okBuilder);
        }
        return templateDefinitionsClient;
    }
}
