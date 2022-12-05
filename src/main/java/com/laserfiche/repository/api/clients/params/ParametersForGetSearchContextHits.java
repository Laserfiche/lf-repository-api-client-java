package com.laserfiche.repository.api.clients.params;

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

    public ParametersForGetSearchContextHits setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    /**
     * The requested repository ID.
     *
     * @return String The return value
     */
    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetSearchContextHits setSearchToken(String searchToken) {
        this.searchToken = searchToken;
        return this;
    }

    /**
     * The requested searchToken.
     *
     * @return String The return value
     */
    public String getSearchToken() {
        return this.searchToken;
    }

    public ParametersForGetSearchContextHits setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
        return this;
    }

    /**
     * The search result listing row number to get context hits for.
     *
     * @return int The return value
     */
    public int getRowNumber() {
        return this.rowNumber;
    }

    public ParametersForGetSearchContextHits setPrefer(String prefer) {
        this.prefer = prefer;
        return this;
    }

    /**
     * An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     *
     * @return String The return value
     */
    public String getPrefer() {
        return this.prefer;
    }

    public ParametersForGetSearchContextHits setSelect(String select) {
        this.select = select;
        return this;
    }

    /**
     * Limits the properties returned in the result.
     *
     * @return String The return value
     */
    public String getSelect() {
        return this.select;
    }

    public ParametersForGetSearchContextHits setOrderby(String orderby) {
        this.orderby = orderby;
        return this;
    }

    /**
     * Specifies the order in which items are returned. The maximum number of expressions is 5.
     *
     * @return String The return value
     */
    public String getOrderby() {
        return this.orderby;
    }

    public ParametersForGetSearchContextHits setTop(int top) {
        this.top = top;
        return this;
    }

    /**
     * Limits the number of items returned from a collection.
     *
     * @return int The return value
     */
    public int getTop() {
        return this.top;
    }

    public ParametersForGetSearchContextHits setSkip(int skip) {
        this.skip = skip;
        return this;
    }

    /**
     * Excludes the specified number of items of the queried collection from the result.
     *
     * @return int The return value
     */
    public int getSkip() {
        return this.skip;
    }

    public ParametersForGetSearchContextHits setCount(boolean count) {
        this.count = count;
        return this;
    }

    /**
     * Indicates whether the total count of items within a collection are returned in the result.
     *
     * @return boolean The return value
     */
    public boolean isCount() {
        return this.count;
    }
}
