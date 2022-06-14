package com.laseriche.api.client.integration;
import com.laserfiche.api.client.ApiException;
import com.laserfiche.api.client.apiserver.AuditReasonsApi;
import com.laserfiche.api.client.client.RepoApiClient;
import com.laserfiche.api.client.model.*;
import com.laserfiche.api.client.oauth.TokenApiClient;
import com.laserfiche.api.client.oauth.TokenApiImpl;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
public class AttributeApiClientTest extends BaseTest{
    @Test
    public void getAttributesAsync() throws ApiException, IOException, ExecutionException, InterruptedException {
        RepoApiClient client = RepoApiClient.CreateFromAccessKey(spKey, accessKey,"a.clouddev.laserfiche.ca");
        //System.out.println(repoId);
        List<RepositoryInfo> result = client.repositoriesClient.getRepositoryList();
        System.out.println(result);
        System.out.println(result.get(0).getRepoId());
        String repoId = result.get(0).getRepoId();
        //AuditReasons auditReasons = client.auditReasonsClient.getAuditReasons(repoId);
        //ODataValueContextOfListOfAttribute auditReasons = client.attributesClient.getTrusteeAttributeKeyValuePairs(repoId, true,null,null,null, null,null,null);
        //System.out.println(auditReasons);
        ODataValueContextOfIListOfWFieldInfo result3 = client.fieldDefinitionsClient.getFieldDefinitions(repoId,null,null,null,null,null,null,null);
        System.out.println(result3.getAtOdataCount());
    }
}
