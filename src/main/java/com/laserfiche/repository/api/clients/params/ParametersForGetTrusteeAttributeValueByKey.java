package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForGetTrusteeAttributeValueByKey {

    /**
     * The requested repository ID.
     */
    String repoId;

    /**
     * The requested attribute key.
     */
    String attributeKey;

    /**
     * Boolean value that indicates whether to return attributes associated with everyone or the currently authenticated user.
     */
    boolean everyone;

    public ParametersForGetTrusteeAttributeValueByKey setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetTrusteeAttributeValueByKey setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
        return this;
    }

    public String getAttributeKey() {
        return this.attributeKey;
    }

    public ParametersForGetTrusteeAttributeValueByKey setEveryone(boolean everyone) {
        this.everyone = everyone;
        return this;
    }

    public boolean isEveryone() {
        return this.everyone;
    }
}
