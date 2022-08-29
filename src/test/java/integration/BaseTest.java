package integration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.laserfiche.api.client.model.AccessKey;
import com.laserfiche.repository.api.RepositoryApiClient;
import com.laserfiche.repository.api.RepositoryApiClientImpl;
import com.laserfiche.repository.api.clients.impl.model.*;
import com.nimbusds.jose.jwk.JWK;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BaseTest {
    protected static String spKey;
    protected static AccessKey accessKey;
    protected static String repoId;
    protected static Map<String, String> testHeaders;
    protected static RepositoryApiClient repositoryApiClient;

    @BeforeAll
    public static void setUp() {
        spKey = System.getenv("SERVICE_PRINCIPAL_KEY");
        String accessKeyBase64 = System.getenv("ACCESS_KEY");
        repoId = System.getenv("REPOSITORY_ID");
        String testHeaderValue = System.getenv("TEST_HEADER");
        if (spKey == null && accessKeyBase64 == null && repoId == null && testHeaderValue == null) {
            // Load environment variables
            Dotenv dotenv = Dotenv
                    .configure()
                    .filename(".env")
                    .load();
            accessKeyBase64 = dotenv.get("ACCESS_KEY");
            spKey = dotenv.get("SERVICE_PRINCIPAL_KEY");
            repoId = dotenv.get("REPOSITORY_ID");
            testHeaderValue = dotenv.get("TEST_HEADER");
        }
        accessKey = AccessKey.CreateFromBase64EncodedAccessKey(accessKeyBase64);
        testHeaders = new HashMap<>();
        testHeaders.put(testHeaderValue, "true");
        repositoryApiClient = RepositoryApiClientImpl.CreateFromAccessKey(spKey, accessKey);
        repositoryApiClient.setDefaultRequestHeaders(testHeaders);
    }

    public static CompletableFuture<Entry> createEntry(RepositoryApiClient client, String entryName, Integer parentEntryId, Boolean autoRename) {
        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.entryType = PostEntryChildrenEntryType.FOLDER;
        request.name = entryName;

        return client.getEntriesClient().createOrCopyEntry(repoId, parentEntryId, request, autoRename, null);
    }

    public static CompletableFuture<Boolean> allFalse(List<TemplateFieldInfo> arr) {
        for (TemplateFieldInfo templateFieldInfo : arr) {
            if (templateFieldInfo.isRequired) {
                return CompletableFuture.supplyAsync(() -> false);
            }
        }
        return CompletableFuture.supplyAsync(() -> true);
    }
}