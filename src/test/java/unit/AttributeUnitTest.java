/*package unit;

import com.laserfiche.repository.api.clients.AttributesClient;
import com.laserfiche.repository.api.clients.AttributesClientImpl;
import com.laserfiche.repository.api.clients.impl.model.Attribute;
import com.laserfiche.repository.api.serialization.GsonCustomConverterFactory;
import com.laserfiche.repository.api.serialization.RepositoryApiDeserializer;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.concurrent.CompletableFuture;

public class AttributeUnitTest {
    @Mock
    private OkHttpClient.Builder build;

    @Mock
    private OkHttpClient.Builder okBuilder;

    @Mock
    private Retrofit.Builder clientBuilder;

    @Test
    void getTrusteeAttributeKeyValuePairs_Success() {
        // ARRANGE
        String baseAddress = "http://api.a.clouddev.laserfiche.ca/";
        String repoId = "repoId";
        String attributeKey = "attributekey";
        Attribute attributeValue = new Attribute().key("attributekey1").value("attributevalue1");
        //Mock both the okhttp application interceptor and clientbuilder
        RepositoryApiDeserializer json = new RepositoryApiDeserializer();
        okBuilder = new OkHttpClient.Builder();
        okBuilder.addInterceptor(chain -> {
            okhttp3.Request.Builder builder = chain.request().newBuilder();
            okhttp3.Request request = builder.build();
            return chain.proceed(request);
        });
        clientBuilder = new Retrofit
                .Builder()
                .baseUrl(baseAddress)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonCustomConverterFactory.create(json.getGson()));

        AttributesClient client = new AttributesClientImpl(clientBuilder, okBuilder);

        //ACT
        CompletableFuture<Attribute> result = client.getTrusteeAttributeValueByKey(repoId, attributeKey, null);
        Attribute attributeResponse = result.join();

        //ASSERT
        assertNotNull(attributeResponse);
        assertTrue(attributeValue.getValue() == attributeResponse.getValue());
        assertTrue(attributeValue.getKey() == attributeResponse.getKey());

        //VERIFY
        verify(client).getTrusteeAttributeValueByKey(repoId,attributeKey,null);
        verify(okBuilder).interceptors();
    }

}*/