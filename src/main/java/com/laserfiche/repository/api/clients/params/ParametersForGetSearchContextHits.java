package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.SearchesClientImpl#getSearchContextHits(ParametersForGetSearchContextHits) getSearchContextHits}.
 */
public class ParametersForGetSearchContextHits {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested searchToken.
     */
    private String searchToken;

    /**
     * The search result listing row number to get context hits for.
     */
    private int rowNumber;

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
     * Builder for setting the searchToken parameter.
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
     * Builder for setting the rowNumber parameter.
     *
     * @param rowNumber The search result listing row number to get context hits for.
     * @return {@link ParametersForGetSearchContextHits} The return value
     */
    public ParametersForGetSearchContextHits setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
        return this;
    }

    /**
     * The search result listing row number to get context hits for.
     *
     * @return {@link int} The return value
     */
    public int getRowNumber() {
        return this.rowNumber;
    }

    /**
     * Builder for setting the prefer parameter.
     *
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
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
     * Builder for setting the select parameter.
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
     * Builder for setting the orderby parameter.
     *
     * @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5.
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
     * Builder for setting the top parameter.
     *
     * @param top Limits the number of items returned from a collection.
     * @return {@link ParametersForGetSearchContextHits} The return value
     */
    public ParametersForGetSearchContextHits setTop(int top) {
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
     * @return {@link ParametersForGetSearchContextHits} The return value
     */
    public ParametersForGetSearchContextHits setSkip(int skip) {
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
     * @return {@link ParametersForGetSearchContextHits} The return value
     */
    public ParametersForGetSearchContextHits setCount(boolean count) {
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
