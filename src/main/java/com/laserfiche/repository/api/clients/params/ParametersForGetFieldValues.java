package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link
 * com.laserfiche.repository.api.clients.impl.EntriesClientImpl#getFieldValues(ParametersForGetFieldValues)
 * getFieldValues}.
 */
public class ParametersForGetFieldValues {

    /** The requested repository ID. */
    private String repoId;

    /** The requested entry ID. */
    private Integer entryId;

    /**
     * An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     */
    private String prefer;

    /**
     * An optional query parameter used to indicate if the field values should be formatted. The
     * default value is false.
     */
    private Boolean formatValue;

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag. The formatValue query parameter must be set to
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
     * @return {@link ParametersForGetFieldValues} The return value
     */
    public ParametersForGetFieldValues setRepoId(String repoId) {
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
     * @return {@link ParametersForGetFieldValues} The return value
     */
    public ParametersForGetFieldValues setEntryId(Integer entryId) {
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
     * @param prefer An optional OData header. Can be used to set the maximum page size using
     *     odata.maxpagesize.
     * @return {@link ParametersForGetFieldValues} The return value
     */
    public ParametersForGetFieldValues setPrefer(String prefer) {
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
     * Sets the value of the formatValue parameter and returns the current object, to enable
     * chaining further setters.
     *
     * @param formatValue An optional query parameter used to indicate if the field values should be
     *     formatted. The default value is false.
     * @return {@link ParametersForGetFieldValues} The return value
     */
    public ParametersForGetFieldValues setFormatValue(Boolean formatValue) {
        this.formatValue = formatValue;
        return this;
    }

    /**
     * An optional query parameter used to indicate if the field values should be formatted. The
     * default value is false.
     *
     * @return {@link Boolean} The return value
     */
    public Boolean isFormatValue() {
        return this.formatValue;
    }

    /**
     * Sets the value of the culture parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used
     *     for formatting. The value should be a standard language tag. The formatValue query
     *     parameter must be set to true, otherwise culture will not be used for formatting.
     * @return {@link ParametersForGetFieldValues} The return value
     */
    public ParametersForGetFieldValues setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag. The formatValue query parameter must be set to
     * true, otherwise culture will not be used for formatting.
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
     * @return {@link ParametersForGetFieldValues} The return value
     */
    public ParametersForGetFieldValues setSelect(String select) {
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
     * @return {@link ParametersForGetFieldValues} The return value
     */
    public ParametersForGetFieldValues setOrderby(String orderby) {
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
     * @return {@link ParametersForGetFieldValues} The return value
     */
    public ParametersForGetFieldValues setTop(Integer top) {
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
     * @return {@link ParametersForGetFieldValues} The return value
     */
    public ParametersForGetFieldValues setSkip(Integer skip) {
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
     * @return {@link ParametersForGetFieldValues} The return value
     */
    public ParametersForGetFieldValues setCount(Boolean count) {
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
