package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.AttributesClientImpl#getTrusteeAttributeKeyValuePairs(ParametersForGetTrusteeAttributeKeyValuePairs) getTrusteeAttributeKeyValuePairs}.
 */
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

    /**
     * Builder for setting the repoId parameter.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForGetTrusteeAttributeKeyValuePairs} The return value
     */
    public ParametersForGetTrusteeAttributeKeyValuePairs setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    /**
     * The requested repository ID.
     *
     * @return {@link String} The return value
     */
    public String getRepoId() {
        return this.repoId;
    }

    /**
     * Builder for setting the everyone parameter.
     *
     * @param everyone Boolean value that indicates whether to return attributes key value pairs associated with everyone or the currently authenticated user.
     * @return {@link ParametersForGetTrusteeAttributeKeyValuePairs} The return value
     */
    public ParametersForGetTrusteeAttributeKeyValuePairs setEveryone(boolean everyone) {
        this.everyone = everyone;
        return this;
    }

    /**
     * Boolean value that indicates whether to return attributes key value pairs associated with everyone or the currently authenticated user.
     *
     * @return {@link boolean} The return value
     */
    public boolean isEveryone() {
        return this.everyone;
    }

    /**
     * Builder for setting the prefer parameter.
     *
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @return {@link ParametersForGetTrusteeAttributeKeyValuePairs} The return value
     */
    public ParametersForGetTrusteeAttributeKeyValuePairs setPrefer(String prefer) {
        this.prefer = prefer;
        return this;
    }

    /**
     * An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     *
     * @return {@link String} The return value
     */
    public String getPrefer() {
        return this.prefer;
    }

    /**
     * Builder for setting the select parameter.
     *
     * @param select Limits the properties returned in the result.
     * @return {@link ParametersForGetTrusteeAttributeKeyValuePairs} The return value
     */
    public ParametersForGetTrusteeAttributeKeyValuePairs setSelect(String select) {
        this.select = select;
        return this;
    }

    /**
     * Limits the properties returned in the result.
     *
     * @return {@link String} The return value
     */
    public String getSelect() {
        return this.select;
    }

    /**
     * Builder for setting the orderby parameter.
     *
     * @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5.
     * @return {@link ParametersForGetTrusteeAttributeKeyValuePairs} The return value
     */
    public ParametersForGetTrusteeAttributeKeyValuePairs setOrderby(String orderby) {
        this.orderby = orderby;
        return this;
    }

    /**
     * Specifies the order in which items are returned. The maximum number of expressions is 5.
     *
     * @return {@link String} The return value
     */
    public String getOrderby() {
        return this.orderby;
    }

    /**
     * Builder for setting the top parameter.
     *
     * @param top Limits the number of items returned from a collection.
     * @return {@link ParametersForGetTrusteeAttributeKeyValuePairs} The return value
     */
    public ParametersForGetTrusteeAttributeKeyValuePairs setTop(int top) {
        this.top = top;
        return this;
    }

    /**
     * Limits the number of items returned from a collection.
     *
     * @return {@link int} The return value
     */
    public int getTop() {
        return this.top;
    }

    /**
     * Builder for setting the skip parameter.
     *
     * @param skip Excludes the specified number of items of the queried collection from the result.
     * @return {@link ParametersForGetTrusteeAttributeKeyValuePairs} The return value
     */
    public ParametersForGetTrusteeAttributeKeyValuePairs setSkip(int skip) {
        this.skip = skip;
        return this;
    }

    /**
     * Excludes the specified number of items of the queried collection from the result.
     *
     * @return {@link int} The return value
     */
    public int getSkip() {
        return this.skip;
    }

    /**
     * Builder for setting the count parameter.
     *
     * @param count Indicates whether the total count of items within a collection are returned in the result.
     * @return {@link ParametersForGetTrusteeAttributeKeyValuePairs} The return value
     */
    public ParametersForGetTrusteeAttributeKeyValuePairs setCount(boolean count) {
        this.count = count;
        return this;
    }

    /**
     * Indicates whether the total count of items within a collection are returned in the result.
     *
     * @return {@link boolean} The return value
     */
    public boolean isCount() {
        return this.count;
    }
}
