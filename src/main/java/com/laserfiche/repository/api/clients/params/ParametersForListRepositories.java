package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.RepositoriesClientImpl#listRepositories(ParametersForListRepositories) listRepositories}.
 */
public class ParametersForListRepositories {

    private String prefer;

    public ParametersForListRepositories setPrefer(String prefer) {
        this.prefer = prefer;
        return this;
    }

    public String getPrefer() {
        return this.prefer;
    }
}
