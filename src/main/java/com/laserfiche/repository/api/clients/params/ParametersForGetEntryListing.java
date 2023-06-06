package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;

/**
 * The encapsulated parameters for {@link
 * com.laserfiche.repository.api.clients.impl.EntriesClientImpl#getEntryListing(ParametersForGetEntryListing)
 * getEntryListing}.
 */
public class ParametersForGetEntryListing {

    /** The requested repository ID. */
    private String repoId;

    /** The folder ID. */
    private Integer entryId;

    /**
     * An optional query parameter used to indicate if the result should be grouped by entry type or
     * not.
     */
    private Boolean groupByEntryType;

    /**
     * Optional array of field names. Field values corresponding to the given field names will be
     * returned for each entry.
     */
    private String[] fields;

    /** Boolean for if field values should be formatted. Only applicable if Fields are specified. */
    private Boolean formatFields;

    /**
     * An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     */
    private String prefer;

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag. The formatFields query parameter must be set to
     * true, otherwise culture will not be used for formatting.
     */
    private String culture;

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
     * Sets the value of the entryId parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param entryId The folder ID.
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setEntryId(Integer entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The folder ID.
     *
     * @return {@link Integer} The return value
     */
    public Integer getEntryId() {
        return this.entryId;
    }

    /**
     * Sets the value of the groupByEntryType parameter and returns the current object, to enable
     * chaining further setters.
     *
     * @param groupByEntryType An optional query parameter used to indicate if the result should be
     *     grouped by entry type or not.
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setGroupByEntryType(Boolean groupByEntryType) {
        this.groupByEntryType = groupByEntryType;
        return this;
    }

    /**
     * An optional query parameter used to indicate if the result should be grouped by entry type or
     * not.
     *
     * @return {@link Boolean} The return value
     */
    public Boolean isGroupByEntryType() {
        return this.groupByEntryType;
    }

    /**
     * Sets the value of the fields parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param fields Optional array of field names. Field values corresponding to the given field
     *     names will be returned for each entry.
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setFields(String[] fields) {
        this.fields = fields;
        return this;
    }

    /**
     * Optional array of field names. Field values corresponding to the given field names will be
     * returned for each entry.
     *
     * @return {@link String[]} The return value
     */
    public String[] getFields() {
        return this.fields;
    }

    /**
     * Sets the value of the formatFields parameter and returns the current object, to enable
     * chaining further setters.
     *
     * @param formatFields Boolean for if field values should be formatted. Only applicable if
     *     Fields are specified.
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setFormatFields(Boolean formatFields) {
        this.formatFields = formatFields;
        return this;
    }

    /**
     * Boolean for if field values should be formatted. Only applicable if Fields are specified.
     *
     * @return {@link Boolean} The return value
     */
    public Boolean isFormatFields() {
        return this.formatFields;
    }

    /**
     * Sets the value of the prefer parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param prefer An optional OData header. Can be used to set the maximum page size using
     *     odata.maxpagesize.
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
     * Sets the value of the culture parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used
     *     for formatting. The value should be a standard language tag. The formatFields query
     *     parameter must be set to true, otherwise culture will not be used for formatting.
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     * <br>
     * The value should be a standard language tag. The formatFields query parameter must be set to
     * true, otherwise<br>
     * culture will not be used for formatting.
     *
     * @return {@link String} The return value
     */
    public String getCulture() {
        return this.culture;
    }

    /**
     * Sets the value of the select parameter and returns the current object, to enable chaining
     * further setters.
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
     * Sets the value of the orderby parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param orderby Specifies the order in which items are returned. The maximum number of
     *     expressions is 5.
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
     * Sets the value of the top parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param top Limits the number of items returned from a collection.
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setTop(Integer top) {
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
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setSkip(Integer skip) {
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
     * @return {@link ParametersForGetEntryListing} The return value
     */
    public ParametersForGetEntryListing setCount(Boolean count) {
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
