package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;

/**
 * The encapsulated parameters for {@link
 * com.laserfiche.repository.api.clients.impl.SearchesClientImpl#getSearchResults(ParametersForGetSearchResults)
 * getSearchResults}.
 */
public class ParametersForGetSearchResults {

    /** The requested repository ID. */
    private String repoId;

    /** The requested searchToken. */
    private String searchToken;

    /**
     * An optional query parameter used to indicate if the result should be grouped by entry type or
     * not.
     */
    private Boolean groupByEntryType;

    /** If the search listing should be refreshed to show updated values. */
    private Boolean refresh;

    /**
     * Optional array of field names. Field values corresponding to the given field names will be
     * returned for each search result.
     */
    private String[] fields;

    /** Boolean for if field values should be formatted. Only applicable if Fields are specified. */
    private Boolean formatFields;

    /**
     * An optional odata header. Can be used to set the maximum page size using odata.maxpagesize.
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
     * @return {@link ParametersForGetSearchResults} The return value
     */
    public ParametersForGetSearchResults setRepoId(String repoId) {
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
     * Sets the value of the searchToken parameter and returns the current object, to enable
     * chaining further setters.
     *
     * @param searchToken The requested searchToken.
     * @return {@link ParametersForGetSearchResults} The return value
     */
    public ParametersForGetSearchResults setSearchToken(String searchToken) {
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
     * Sets the value of the groupByEntryType parameter and returns the current object, to enable
     * chaining further setters.
     *
     * @param groupByEntryType An optional query parameter used to indicate if the result should be
     *     grouped by entry type or not.
     * @return {@link ParametersForGetSearchResults} The return value
     */
    public ParametersForGetSearchResults setGroupByEntryType(Boolean groupByEntryType) {
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
     * Sets the value of the refresh parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param refresh If the search listing should be refreshed to show updated values.
     * @return {@link ParametersForGetSearchResults} The return value
     */
    public ParametersForGetSearchResults setRefresh(Boolean refresh) {
        this.refresh = refresh;
        return this;
    }

    /**
     * If the search listing should be refreshed to show updated values.
     *
     * @return {@link Boolean} The return value
     */
    public Boolean isRefresh() {
        return this.refresh;
    }

    /**
     * Sets the value of the fields parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param fields Optional array of field names. Field values corresponding to the given field
     *     names will be returned for each search result.
     * @return {@link ParametersForGetSearchResults} The return value
     */
    public ParametersForGetSearchResults setFields(String[] fields) {
        this.fields = fields;
        return this;
    }

    /**
     * Optional array of field names. Field values corresponding to the given field names will be
     * returned for each search result.
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
     * @return {@link ParametersForGetSearchResults} The return value
     */
    public ParametersForGetSearchResults setFormatFields(Boolean formatFields) {
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
     * @param prefer An optional odata header. Can be used to set the maximum page size using
     *     odata.maxpagesize.
     * @return {@link ParametersForGetSearchResults} The return value
     */
    public ParametersForGetSearchResults setPrefer(String prefer) {
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
     * Sets the value of the culture parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used
     *     for formatting. The value should be a standard language tag. The formatFields query
     *     parameter must be set to true, otherwise culture will not be used for formatting.
     * @return {@link ParametersForGetSearchResults} The return value
     */
    public ParametersForGetSearchResults setCulture(String culture) {
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
     * @return {@link ParametersForGetSearchResults} The return value
     */
    public ParametersForGetSearchResults setSelect(String select) {
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
     * @return {@link ParametersForGetSearchResults} The return value
     */
    public ParametersForGetSearchResults setOrderby(String orderby) {
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
     * @return {@link ParametersForGetSearchResults} The return value
     */
    public ParametersForGetSearchResults setTop(Integer top) {
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
     * @return {@link ParametersForGetSearchResults} The return value
     */
    public ParametersForGetSearchResults setSkip(Integer skip) {
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
     * @return {@link ParametersForGetSearchResults} The return value
     */
    public ParametersForGetSearchResults setCount(Boolean count) {
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
