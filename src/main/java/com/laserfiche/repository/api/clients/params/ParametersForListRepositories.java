package com.laserfiche.repository.api.clients.params;

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
