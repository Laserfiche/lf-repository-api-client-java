package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link
 * com.laserfiche.repository.api.clients.impl.EntriesClientImpl#getLinkValuesFromEntry(ParametersForGetLinkValuesFromEntry)
 * getLinkValuesFromEntry}.
 */
public class ParametersForGetLinkValuesFromEntry {

    /** The requested repository ID. */
    private String repoId;

    /** The requested entry ID. */
    private Integer entryId;

    /**
     * An optional odata header. Can be used to set the maximum page size using odata.maxpagesize.
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
     * @return {@link ParametersForGetLinkValuesFromEntry} The return value
     */
    public ParametersForGetLinkValuesFromEntry setRepoId(String repoId) {
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
     * Sets the value of the entryId parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param entryId The requested entry ID.
     * @return {@link ParametersForGetLinkValuesFromEntry} The return value
     */
    public ParametersForGetLinkValuesFromEntry setEntryId(Integer entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The requested entry ID.
     *
     * @return {@link Integer} The return value
     */
    public Integer getEntryId() {
        return this.entryId;
    }

    /**
     * Sets the value of the prefer parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param prefer An optional odata header. Can be used to set the maximum page size using
     *     odata.maxpagesize.
     * @return {@link ParametersForGetLinkValuesFromEntry} The return value
     */
    public ParametersForGetLinkValuesFromEntry setPrefer(String prefer) {
        this.prefer = prefer;
        return this;
    }

    /**
     * An optional odata header. Can be used to set the maximum page size using odata.maxpagesize.
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
     * @return {@link ParametersForGetLinkValuesFromEntry} The return value
     */
    public ParametersForGetLinkValuesFromEntry setSelect(String select) {
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
     * @return {@link ParametersForGetLinkValuesFromEntry} The return value
     */
    public ParametersForGetLinkValuesFromEntry setOrderby(String orderby) {
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
     * @return {@link ParametersForGetLinkValuesFromEntry} The return value
     */
    public ParametersForGetLinkValuesFromEntry setTop(Integer top) {
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
     * @return {@link ParametersForGetLinkValuesFromEntry} The return value
     */
    public ParametersForGetLinkValuesFromEntry setSkip(Integer skip) {
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
     * @return {@link ParametersForGetLinkValuesFromEntry} The return value
     */
    public ParametersForGetLinkValuesFromEntry setCount(Boolean count) {
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
