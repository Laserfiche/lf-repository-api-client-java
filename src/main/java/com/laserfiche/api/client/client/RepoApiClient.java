package com.laserfiche.api.client.client;
import com.laserfiche.api.client.ApiClient;
import com.laserfiche.api.client.ApiException;
import com.laserfiche.api.client.apiserver.*;
import com.laserfiche.api.client.client.IRepoApiClient;
import com.laserfiche.api.client.oauth.*;
import com.laserfiche.api.client.httphandlers.HttpRequestHandler;
import com.laserfiche.api.client.httphandlers.OAuthClientCredentialsHandler;
import com.laserfiche.api.client.model.AccessKey;
import com.squareup.okhttp.OkHttpClient;
import com.laserfiche.api.client.auth.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.laserfiche.api.client.client.BaseTest.*;

public class RepoApiClient {
    private String baseUrl;
    public AttributesApi attributesClient;
    public AuditReasonsApi auditReasonsClient;
    public EntriesApi entriesClient;
    public FieldDefinitionsApi fieldDefinitionsClient;
    public RepositoriesApi repositoriesClient;
    public SearchesApi searchClient;
    public ServerSessionApi serverSessionClient;
    public SimpleSearchesApi simpleSearchesClient;
    public TagDefinitionsApi tagDefinitionsClient;
    public TasksApi tasksClient;
    public TemplateDefinitionsApi templateDefinitionsClient;
    private RepositoryApiClientHttpHandler repositoryApiClientHttpHandler;

    private RepoApiClient(HttpRequestHandler httpRequestHandler) throws ApiException, IOException, ExecutionException, InterruptedException {
        this.repositoryApiClientHttpHandler = new RepositoryApiClientHttpHandler(httpRequestHandler);
        ApiClient client = new ApiClient();
        client.setBasePath("https://api.a.clouddev.laserfiche.com/repository");
        client.setAccessToken("eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhcGlzZXJ2ZXJAYXBpc2VydmVyLmNvbSIsImNsaWVudF9pZCI6IjljMGU0NTY1LWFkYzMtNGU3ZC04MzBlLWM0NzNkYWIwZmIwMyIsImNzaWQiOiI5NDgyMjQ2NjAiLCJ0cmlkIjoiMTAwMSIsIm5hbWUiOiJhcGlzZXJ2ZXIiLCJ1dHlwIjoiRnVsbCIsImd0aWQiOiIxMTQwMzMiLCJhY3NzayI6IkVpU3R2d1VRa1ZMbnA0Yk5XT3FiSlNBaFBuUE9rWExwZDFzcFZRcG5Qc2lSM1lpRXUvMU1KVzMxemNteUZ0MHAiLCJhdWQiOiJ1cy5sYXNlcmZpY2hlLmNvbSIsImV4cCI6MTY1NTIxODU2MywiaXNzIjoic2lnbmluLmxhc2VyZmljaGUuY29tIiwibmJmIjoxNjU1MjEzMTc5LCJpYXQiOjE2NTUyMTQ5NjN9.af39WdmjY46Zc0ESi2oyml5PzLdlE12MRXoDG3v-BbSsHjrdcbdz8dFCktfkz6uS0sQS22yehdT5QHfldPp4sA");
        this.attributesClient = new AttributesApi(client);
        this.auditReasonsClient = new AuditReasonsApi(client);
        this.entriesClient = new EntriesApi(client);
        this.fieldDefinitionsClient = new FieldDefinitionsApi(client);
        this.repositoriesClient = new RepositoriesApi(client);
        this.searchClient = new SearchesApi(client);
        this.serverSessionClient = new ServerSessionApi(client);
        this.simpleSearchesClient = new SimpleSearchesApi(client);
        this.tagDefinitionsClient = new TagDefinitionsApi(client);
        this.tasksClient = new TasksApi(client);
        this.templateDefinitionsClient = new TemplateDefinitionsApi(client);

    }
    public static RepoApiClient CreateFromHttpRequestHandler(HttpRequestHandler httpRequestHandler,String baseUrl) throws ApiException, IOException, ExecutionException, InterruptedException {
        if(httpRequestHandler == null){
            throw new NullPointerException("Argument cannot be null: httpRequestHandler");
        }
        RepoApiClient repoClient = new RepoApiClient(httpRequestHandler);
        return repoClient;

    }
    public static RepoApiClient CreateFromAccessKey(String servicePrincipalKey, AccessKey accessKey, String baseUrl) throws ApiException, IOException, ExecutionException, InterruptedException {
        OAuthClientCredentialsHandler handler = new OAuthClientCredentialsHandler(servicePrincipalKey,accessKey);
        return RepoApiClient.CreateFromHttpRequestHandler(handler,baseUrl);
    }
}

