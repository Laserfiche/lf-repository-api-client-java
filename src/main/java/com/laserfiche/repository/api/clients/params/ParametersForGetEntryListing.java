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
     * The requested repository ID.
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
     * The folder ID.
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
     * An optional query parameter used to indicate if the result should be grouped by entry type or not.
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
     * Optional array of field names. Field values corresponding to the given field names will be returned for each entry.
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
     * Boolean for if field values should be formatted. Only applicable if Fields are specified.
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
     * An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
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
     * An optional query parameter used to indicate the locale that should be used for formatting.
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
     * Limits the properties returned in the result.
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
     * Specifies the order in which items are returned. The maximum number of expressions is 5.
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
     * Limits the number of items returned from a collection.
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
     * Excludes the specified number of items of the queried collection from the result.
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
     * Indicates whether the total count of items within a collection are returned in the result.
     *
     * @return boolean The return value
     */
    public boolean isCount() {
        return this.count;
    }
}
