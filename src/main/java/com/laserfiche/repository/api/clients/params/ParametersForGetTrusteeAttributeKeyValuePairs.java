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


    /**
     * Returns the value of 'repoId' field, which is the requested repository ID.
     *
     * @return String The return value
     */
    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetTrusteeAttributeKeyValuePairs setEveryone(boolean everyone) {
        this.everyone = everyone;
        return this;
    }


    /**
     * Returns the value of 'everyone' field, which is boolean value that indicates whether to return attributes key value pairs associated with everyone or the currently authenticated user.
     *
     * @return boolean The return value
     */
    public boolean isEveryone() {
        return this.everyone;
    }

    public ParametersForGetTrusteeAttributeKeyValuePairs setPrefer(String prefer) {
        this.prefer = prefer;
        return this;
    }


    /**
     * Returns the value of 'prefer' field, which is an optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     *
     * @return String The return value
     */
    public String getPrefer() {
        return this.prefer;
    }

    public ParametersForGetTrusteeAttributeKeyValuePairs setSelect(String select) {
        this.select = select;
        return this;
    }


    /**
     * Returns the value of 'select' field, which is limits the properties returned in the result.
     *
     * @return String The return value
     */
    public String getSelect() {
        return this.select;
    }

    public ParametersForGetTrusteeAttributeKeyValuePairs setOrderby(String orderby) {
        this.orderby = orderby;
        return this;
    }


    /**
     * Returns the value of 'orderby' field, which is specifies the order in which items are returned. The maximum number of expressions is 5.
     *
     * @return String The return value
     */
    public String getOrderby() {
        return this.orderby;
    }

    public ParametersForGetTrusteeAttributeKeyValuePairs setTop(int top) {
        this.top = top;
        return this;
    }


    /**
     * Returns the value of 'top' field, which is limits the number of items returned from a collection.
     *
     * @return int The return value
     */
    public int getTop() {
        return this.top;
    }

    public ParametersForGetTrusteeAttributeKeyValuePairs setSkip(int skip) {
        this.skip = skip;
        return this;
    }


    /**
     * Returns the value of 'skip' field, which is excludes the specified number of items of the queried collection from the result.
     *
     * @return int The return value
     */
    public int getSkip() {
        return this.skip;
    }

    public ParametersForGetTrusteeAttributeKeyValuePairs setCount(boolean count) {
        this.count = count;
        return this;
    }


    /**
     * Returns the value of 'count' field, which is indicates whether the total count of items within a collection are returned in the result.
     *
     * @return boolean The return value
     */
    public boolean isCount() {
        return this.count;
    }
}
