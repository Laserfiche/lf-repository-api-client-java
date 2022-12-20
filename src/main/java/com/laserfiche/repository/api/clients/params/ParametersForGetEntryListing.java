package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#getEntryListing(ParametersForGetEntryListing) getEntryListing}.
 */
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

    /**
     * Builder for setting the repoId parameter.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setRepoId(String repoId) {
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
     * Builder for setting the entryId parameter.
     *
     * @param entryId The folder ID.
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The folder ID.
     *
     * @return {@link int} The return value
     */
    public int getEntryId() {
        return this.entryId;
    }

    /**
     * Builder for setting the groupByEntryType parameter.
     *
     * @param groupByEntryType An optional query parameter used to indicate if the result should be grouped by entry type or not.
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setGroupByEntryType(boolean groupByEntryType) {
        this.groupByEntryType = groupByEntryType;
        return this;
    }

    /**
     * An optional query parameter used to indicate if the result should be grouped by entry type or not.
     *
     * @return {@link boolean} The return value
     */
    public boolean isGroupByEntryType() {
        return this.groupByEntryType;
    }

    /**
     * Builder for setting the fields parameter.
     *
     * @param fields Optional array of field names. Field values corresponding to the given field names will be returned for each entry.
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setFields(String[] fields) {
        this.fields = fields;
        return this;
    }

    /**
     * Optional array of field names. Field values corresponding to the given field names will be returned for each entry.
     *
     * @return {@link String[]} The return value
     */
    public String[] getFields() {
        return this.fields;
    }

    /**
     * Builder for setting the formatFields parameter.
     *
     * @param formatFields Boolean for if field values should be formatted. Only applicable if Fields are specified.
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setFormatFields(boolean formatFields) {
        this.formatFields = formatFields;
        return this;
    }

    /**
     * Boolean for if field values should be formatted. Only applicable if Fields are specified.
     *
     * @return {@link boolean} The return value
     */
    public boolean isFormatFields() {
        return this.formatFields;
    }

    /**
     * Builder for setting the prefer parameter.
     *
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setPrefer(String prefer) {
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
     * Builder for setting the culture parameter.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.
     *                The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise
     *                culture will not be used for formatting.
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise
     * culture will not be used for formatting.
     *
     * @return {@link String} The return value
     */
    public String getCulture() {
        return this.culture;
    }

    /**
     * Builder for setting the select parameter.
     *
     * @param select Limits the properties returned in the result.
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setSelect(String select) {
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
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setOrderby(String orderby) {
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
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setTop(int top) {
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
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setSkip(int skip) {
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
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setCount(boolean count) {
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
