package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#getFieldValues(ParametersForGetFieldValues) getFieldValues}.
 */
public class ParametersForGetFieldValues {

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
     * An optional query parameter used to indicate if the field values should be formatted.
     * The default value is false.
     */
    private boolean formatValue;

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag. The formatValue query parameter must be set to true, otherwise
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
     * Builder for setting the entryId parameter.
     *
     * @param entryId The requested entry ID.
     * @return {@link ParametersForGetFieldValues} The return value
     */
    public ParametersForGetFieldValues setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The requested entry ID.
     *
     * @return {@link int} The return value
     */
    public int getEntryId() {
        return this.entryId;
    }

    /**
     * Builder for setting the prefer parameter.
     *
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
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
     * Builder for setting the formatValue parameter.
     *
     * @param formatValue An optional query parameter used to indicate if the field values should be formatted.
     *                    The default value is false.
     * @return {@link ParametersForGetFieldValues} The return value
     */
    public ParametersForGetFieldValues setFormatValue(boolean formatValue) {
        this.formatValue = formatValue;
        return this;
    }

    /**
     * An optional query parameter used to indicate if the field values should be formatted.
     * The default value is false.
     *
     * @return {@link boolean} The return value
     */
    public boolean isFormatValue() {
        return this.formatValue;
    }

    /**
     * Builder for setting the culture parameter.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.
     *                The value should be a standard language tag. The formatValue query parameter must be set to true, otherwise
     *                culture will not be used for formatting.
     * @return {@link ParametersForGetFieldValues} The return value
     */
    public ParametersForGetFieldValues setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag. The formatValue query parameter must be set to true, otherwise
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
     * Builder for setting the orderby parameter.
     *
     * @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5.
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
     * Builder for setting the top parameter.
     *
     * @param top Limits the number of items returned from a collection.
     * @return {@link ParametersForGetFieldValues} The return value
     */
    public ParametersForGetFieldValues setTop(int top) {
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
     * @return {@link ParametersForGetFieldValues} The return value
     */
    public ParametersForGetFieldValues setSkip(int skip) {
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
     * @return {@link ParametersForGetFieldValues} The return value
     */
    public ParametersForGetFieldValues setCount(boolean count) {
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
