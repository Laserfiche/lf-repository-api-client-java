package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#getTagsAssignedToEntry(ParametersForGetTagsAssignedToEntry) getTagsAssignedToEntry}.
 */
public class ParametersForGetTagsAssignedToEntry {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested entry ID.
     */
    private int entryId;

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
     * Sets the value of the repoId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForGetTagsAssignedToEntry} The return value
     */
    public ParametersForGetTagsAssignedToEntry setRepoId(String repoId) {
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
     * Sets the value of the entryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param entryId The requested entry ID.
     * @return {@link ParametersForGetTagsAssignedToEntry} The return value
     */
    public ParametersForGetTagsAssignedToEntry setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The requested entry ID.
     *
     * @return int The return value
     */
    public int getEntryId() {
        return this.entryId;
    }

    /**
     * Sets the value of the prefer parameter and returns the current object, to enable chaining further setters.
     *
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @return {@link ParametersForGetTagsAssignedToEntry} The return value
     */
    public ParametersForGetTagsAssignedToEntry setPrefer(String prefer) {
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
     * @return {@link ParametersForGetTagsAssignedToEntry} The return value
     */
    public ParametersForGetTagsAssignedToEntry setSelect(String select) {
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
     * @return {@link ParametersForGetTagsAssignedToEntry} The return value
     */
    public ParametersForGetTagsAssignedToEntry setOrderby(String orderby) {
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
     * @return {@link ParametersForGetTagsAssignedToEntry} The return value
     */
    public ParametersForGetTagsAssignedToEntry setTop(int top) {
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

    /**
     * Sets the value of the skip parameter and returns the current object, to enable chaining further setters.
     *
     * @param skip Excludes the specified number of items of the queried collection from the result.
     * @return {@link ParametersForGetTagsAssignedToEntry} The return value
     */
    public ParametersForGetTagsAssignedToEntry setSkip(int skip) {
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

    /**
     * Sets the value of the count parameter and returns the current object, to enable chaining further setters.
     *
     * @param count Indicates whether the total count of items within a collection are returned in the result.
     * @return {@link ParametersForGetTagsAssignedToEntry} The return value
     */
    public ParametersForGetTagsAssignedToEntry setCount(boolean count) {
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
