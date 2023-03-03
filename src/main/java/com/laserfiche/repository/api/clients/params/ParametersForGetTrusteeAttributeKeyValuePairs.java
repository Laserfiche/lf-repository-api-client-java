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
    private Boolean everyone;

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
    private Integer top;

    /**
     * Excludes the specified number of items of the queried collection from the result.
     */
    private Integer skip;

    /**
     * Indicates whether the total count of items within a collection are returned in the result.
     */
    private Boolean count;

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining further setters.
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
     * Sets the value of the everyone parameter and returns the current object, to enable chaining further setters.
     *
     * @param everyone Boolean value that indicates whether to return attributes key value pairs associated with everyone or the currently authenticated user.
     * @return {@link ParametersForGetTrusteeAttributeKeyValuePairs} The return value
     */
    public ParametersForGetTrusteeAttributeKeyValuePairs setEveryone(Boolean everyone) {
        this.everyone = everyone;
        return this;
    }

    /**
     * Boolean value that indicates whether to return attributes key value pairs associated with everyone or the currently authenticated user.
     *
     * @return {@link Boolean} The return value
     */
    public Boolean isEveryone() {
        return this.everyone;
    }

    /**
     * Sets the value of the prefer parameter and returns the current object, to enable chaining further setters.
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
     * Sets the value of the select parameter and returns the current object, to enable chaining further setters.
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
     * Sets the value of the orderby parameter and returns the current object, to enable chaining further setters.
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
     * Sets the value of the top parameter and returns the current object, to enable chaining further setters.
     *
     * @param top Limits the number of items returned from a collection.
     * @return {@link ParametersForGetTrusteeAttributeKeyValuePairs} The return value
     */
    public ParametersForGetTrusteeAttributeKeyValuePairs setTop(Integer top) {
        this.top = top;
        return this;
    }

    /**
     * Limits the number of items returned from a collection.
     *
     * @return {@link Integer} The return value
     */
    public Integer getTop() {
        return this.top;
    }

    /**
     * Sets the value of the skip parameter and returns the current object, to enable chaining further setters.
     *
     * @param skip Excludes the specified number of items of the queried collection from the result.
     * @return {@link ParametersForGetTrusteeAttributeKeyValuePairs} The return value
     */
    public ParametersForGetTrusteeAttributeKeyValuePairs setSkip(Integer skip) {
        this.skip = skip;
        return this;
    }

    /**
     * Excludes the specified number of items of the queried collection from the result.
     *
     * @return {@link Integer} The return value
     */
    public Integer getSkip() {
        return this.skip;
    }

    /**
     * Sets the value of the count parameter and returns the current object, to enable chaining further setters.
     *
     * @param count Indicates whether the total count of items within a collection are returned in the result.
     * @return {@link ParametersForGetTrusteeAttributeKeyValuePairs} The return value
     */
    public ParametersForGetTrusteeAttributeKeyValuePairs setCount(Boolean count) {
        this.count = count;
        return this;
    }

    /**
     * Indicates whether the total count of items within a collection are returned in the result.
     *
     * @return {@link Boolean} The return value
     */
    public Boolean isCount() {
        return this.count;
    }
}
