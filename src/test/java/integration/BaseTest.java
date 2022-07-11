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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BaseTest {
    protected static String spKey;
    protected static AccessKey accessKey;
    protected static String repoId;
    protected static RepositoryApiClient repositoryApiClient;

    @BeforeAll
    public static void setUp() {
        // Load environment variables
        Dotenv dotenv = Dotenv
                .configure()
                .filename(".env")
                .load();

        // Read env variables
        Gson gson = new GsonBuilder().registerTypeAdapter(JWK.class, new JwkDeserializer()).create();

        String accessKeyBase64 = dotenv.get("ACCESS_KEY");
        String accessKeyStr = decodeBase64(accessKeyBase64);
        // Gson doesn't escape forward slash https://github.com/google/gson/issues/356
        accessKeyStr = accessKeyStr.replace("\\\"", "\"");

        accessKey = gson.fromJson(accessKeyStr, AccessKey.class);
        spKey = dotenv.get("SERVICE_PRINCIPAL_KEY");
        repoId = dotenv.get("REPOSITORY_ID");
        repositoryApiClient = RepositoryApiClientImpl.CreateFromAccessKey(spKey, accessKey);
    }

    private static String decodeBase64(String encoded) {
        byte[] decodedBytes = Base64.getDecoder().decode(encoded);
        return new String(decodedBytes);
    }

    public static CompletableFuture<Entry> createEntry(RepositoryApiClient client, String entryName, Integer parentEntryId, Boolean autoRename) {
        PostEntryChildrenRequest request = new PostEntryChildrenRequest();
        request.setEntryType(PostEntryChildrenEntryType.FOLDER);
        request.setName(entryName);
        CompletableFuture<Entry> newEntry = client.getEntriesClient().createOrCopyEntry(repoId, parentEntryId, request, autoRename, null);
        Entry entry = newEntry.join();
        assertNotNull(newEntry);
        Assertions.assertSame(entry.getParentId(), parentEntryId);
        Assertions.assertSame(entry.getEntryType(), EntryType.FOLDER);
        return newEntry;
    }
}