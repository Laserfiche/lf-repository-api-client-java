package com.laserfiche.repository.api.clients.params;

public class ParametersForGetTrusteeAttributeKeyValuePairs {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * Boolean value that indicates whether to return attributes key value pairs associated with everyone or the currently authenticated user.
     */
    private boolean everyone;

    /**
     * An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     */
    private String prefer;

    /**
     * Limits the properties returned in the result.
     */
    private String select;

    /**
     * Specifies the order in which items are returned. The maximum number of expressions is 5.
     */
    private String orderby;

    /**
     * Limits the number of items returned from a collection.
     */
    private int top;

    /**
     * Excludes the specified number of items of the queried collection from the result.
     */
    private int skip;

    /**
     * Indicates whether the total count of items within a collection are returned in the result.
     */
    private boolean count;

    public ParametersForGetTrusteeAttributeKeyValuePairs setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetTrusteeAttributeKeyValuePairs setEveryone(boolean everyone) {
        this.everyone = everyone;
        return this;
    }

    public boolean isEveryone() {
        return this.everyone;
    }

    public ParametersForGetTrusteeAttributeKeyValuePairs setPrefer(String prefer) {
        this.prefer = prefer;
        return this;
    }

    public String getPrefer() {
        return this.prefer;
    }

    public ParametersForGetTrusteeAttributeKeyValuePairs setSelect(String select) {
        this.select = select;
        return this;
    }

    public String getSelect() {
        return this.select;
    }

    public ParametersForGetTrusteeAttributeKeyValuePairs setOrderby(String orderby) {
        this.orderby = orderby;
        return this;
    }

    public String getOrderby() {
        return this.orderby;
    }

    public ParametersForGetTrusteeAttributeKeyValuePairs setTop(int top) {
        this.top = top;
        return this;
    }

    public int getTop() {
        return this.top;
    }

    public ParametersForGetTrusteeAttributeKeyValuePairs setSkip(int skip) {
        this.skip = skip;
        return this;
    }

    public int getSkip() {
        return this.skip;
    }

    public ParametersForGetTrusteeAttributeKeyValuePairs setCount(boolean count) {
        this.count = count;
        return this;
    }

    public boolean isCount() {
        return this.count;
    }
}
