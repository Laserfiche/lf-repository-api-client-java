package com.laseriche.api.client.integration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.laserfiche.api.client.model.AccessKey;
import com.nimbusds.jose.jwk.JWK;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    protected static String spKey;
    protected static AccessKey accessKey;

    protected static String repoId;

    @BeforeAll
    public static void setUp() {
        // Load environment variables
        Dotenv dotenv = Dotenv
                .configure()
                .filename("TestConfig.env")
                .load();

        // Read env variables
        Gson gson = new GsonBuilder().registerTypeAdapter(JWK.class, new JwkDeserializer()).create();

        String accessKeyStr = dotenv.get("DEV_CA_PUBLIC_USE_INTEGRATION_TEST_ACCESS_KEY");
        if (accessKeyStr == null) {
            throw new RuntimeException("DEV_CA_PUBLIC_USE_INTEGRATION_TEST_ACCESS_KEY environment variable is not set");
        }
        // Gson doesn't escape forward slash https://github.com/google/gson/issues/356
        accessKeyStr = accessKeyStr.replace("\\\"", "\"");

        accessKey = gson.fromJson(accessKeyStr, AccessKey.class);
        spKey = dotenv.get("DEV_CA_PUBLIC_USE_TESTOAUTHSERVICEPRINCIPAL_SERVICE_PRINCIPAL_KEY");
        repoId = dotenv.get("DEV_CA_PUBLIC_USE_REPOSITORY_ID_1");
    }
}
