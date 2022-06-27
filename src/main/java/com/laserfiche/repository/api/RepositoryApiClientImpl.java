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
    private AuditReasonsApi auditReasonsApi;
    private EntriesApi entriesApi;
    private FieldDefinitionsApi fieldDefinitionsApi;
    private RepositoriesApi repositoriesApi;
    private SearchesApi searchesApi;
    private ServerSessionApi serverSessionApi;
    private SimpleSearchesApi simpleSearchesApi;
    private TagDefinitionsApi tagDefinitionsApi;
    private TasksApi tasksApi;
    private TemplateDefinitionsApi templateDefinitionsApi;

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
    public ServerSessionApi getServerSessionClient() {
        if (serverSessionApi == null) {
            serverSessionApi = createClient(ServerSessionApi.class);
        }
        return serverSessionApi;
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
