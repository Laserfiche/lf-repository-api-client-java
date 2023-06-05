package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link
 * com.laserfiche.repository.api.clients.impl.SearchesClientImpl#getSearchContextHits(ParametersForGetSearchContextHits)
 * getSearchContextHits}.
 */
public class ParametersForGetSearchContextHits {

    /** The requested repository ID. */
    private String repoId;

    /** The requested searchToken. */
    private String searchToken;

    /** The search result listing row number to get context hits for. */
    private Integer rowNumber;

    /**
     * An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     */
    private String prefer;

    /** Limits the properties returned in the result. */
    private String select;

    /** Specifies the order in which items are returned. The maximum number of expressions is 5. */
    private String orderby;

    /** Limits the number of items returned from a collection. */
    private Integer top;

    /** Excludes the specified number of items of the queried collection from the result. */
    private Integer skip;

    /**
     * Indicates whether the total count of items within a collection are returned in the result.
     */
    private Boolean count;

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForGetSearchContextHits} The return value
     */
    public ParametersForGetSearchContextHits setRepoId(String repoId) {
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
     * Sets the value of the searchToken parameter and returns the current object, to enable
     * chaining further setters.
     *
     * @param searchToken The requested searchToken.
     * @return {@link ParametersForGetSearchContextHits} The return value
     */
    public ParametersForGetSearchContextHits setSearchToken(String searchToken) {
        this.searchToken = searchToken;
        return this;
    }

    /**
     * The requested searchToken.
     *
     * @return {@link String} The return value
     */
    public String getSearchToken() {
        return this.searchToken;
    }

    /**
     * Sets the value of the rowNumber parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param rowNumber The search result listing row number to get context hits for.
     * @return {@link ParametersForGetSearchContextHits} The return value
     */
    public ParametersForGetSearchContextHits setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
        return this;
    }

    /**
     * The search result listing row number to get context hits for.
     *
     * @return {@link Integer} The return value
     */
    public Integer getRowNumber() {
        return this.rowNumber;
    }

    /**
     * Sets the value of the prefer parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param prefer An optional OData header. Can be used to set the maximum page size using
     *     odata.maxpagesize.
     * @return {@link ParametersForGetSearchContextHits} The return value
     */
    public ParametersForGetSearchContextHits setPrefer(String prefer) {
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
     * Sets the value of the select parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param select Limits the properties returned in the result.
     * @return {@link ParametersForGetSearchContextHits} The return value
     */
    public ParametersForGetSearchContextHits setSelect(String select) {
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
     * Sets the value of the orderby parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param orderby Specifies the order in which items are returned. The maximum number of
     *     expressions is 5.
     * @return {@link ParametersForGetSearchContextHits} The return value
     */
    public ParametersForGetSearchContextHits setOrderby(String orderby) {
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
     * Sets the value of the top parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param top Limits the number of items returned from a collection.
     * @return {@link ParametersForGetSearchContextHits} The return value
     */
    public ParametersForGetSearchContextHits setTop(Integer top) {
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
     * Sets the value of the skip parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param skip Excludes the specified number of items of the queried collection from the result.
     * @return {@link ParametersForGetSearchContextHits} The return value
     */
    public ParametersForGetSearchContextHits setSkip(Integer skip) {
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
     * Sets the value of the count parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param count Indicates whether the total count of items within a collection are returned in
     *     the result.
     * @return {@link ParametersForGetSearchContextHits} The return value
     */
    public ParametersForGetSearchContextHits setCount(Boolean count) {
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
