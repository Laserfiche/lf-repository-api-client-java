package com.laserfiche.repository.api.clients.params;

public class ParametersForGetEntryListing {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The folder ID.
     */
    private int entryId;

    /**
     * An optional query parameter used to indicate if the result should be grouped by entry type or not.
     */
    private boolean groupByEntryType;

    /**
     * Optional array of field names. Field values corresponding to the given field names will be returned for each entry.
     */
    private String[] fields;

    /**
     * Boolean for if field values should be formatted. Only applicable if Fields are specified.
     */
    private boolean formatFields;

    /**
     * An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     */
    private String prefer;

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise
     * culture will not be used for formatting.
     */
    private String culture;

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

    public ParametersForGetEntryListing setRepoId(String repoId) {
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

    public ParametersForGetEntryListing setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }


    /**
     * Returns the value of 'entryId' field, which is the folder ID.
     *
     * @return int The return value
     */
    public int getEntryId() {
        return this.entryId;
    }

    public ParametersForGetEntryListing setGroupByEntryType(boolean groupByEntryType) {
        this.groupByEntryType = groupByEntryType;
        return this;
    }


    /**
     * Returns the value of 'groupByEntryType' field, which is an optional query parameter used to indicate if the result should be grouped by entry type or not.
     *
     * @return boolean The return value
     */
    public boolean isGroupByEntryType() {
        return this.groupByEntryType;
    }

    public ParametersForGetEntryListing setFields(String[] fields) {
        this.fields = fields;
        return this;
    }


    /**
     * Returns the value of 'fields' field, which is optional array of field names. Field values corresponding to the given field names will be returned for each entry.
     *
     * @return String[] The return value
     */
    public String[] getFields() {
        return this.fields;
    }

    public ParametersForGetEntryListing setFormatFields(boolean formatFields) {
        this.formatFields = formatFields;
        return this;
    }


    /**
     * Returns the value of 'formatFields' field, which is boolean for if field values should be formatted. Only applicable if Fields are specified.
     *
     * @return boolean The return value
     */
    public boolean isFormatFields() {
        return this.formatFields;
    }

    public ParametersForGetEntryListing setPrefer(String prefer) {
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

    public ParametersForGetEntryListing setCulture(String culture) {
        this.culture = culture;
        return this;
    }


    /**
     * Returns the value of 'culture' field, which is an optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise
     * culture will not be used for formatting.
     *
     * @return String The return value
     */
    public String getCulture() {
        return this.culture;
    }

    public ParametersForGetEntryListing setSelect(String select) {
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

    public ParametersForGetEntryListing setOrderby(String orderby) {
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

    public ParametersForGetEntryListing setTop(int top) {
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

    public ParametersForGetEntryListing setSkip(int skip) {
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

    public ParametersForGetEntryListing setCount(boolean count) {
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
