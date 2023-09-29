package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#listEntries(ParametersForListEntries) listEntries}.
 */
public class ParametersForListEntries {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * The folder ID.
     */
    private Integer entryId;

    /**
     * Indicates if the result should be grouped by entry type or not. The default value is false.
     */
    private Boolean groupByEntryType;

    /**
     * Optional array of field names. Field values corresponding to the given field names will be returned for each entry.
     */
    private String[] fields;

    /**
     * Indicates if field values should be formatted. Only applicable if Fields are specified. The default value is false.
     */
    private Boolean formatFieldValues;

    /**
     * An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     */
    private String prefer;

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting. The value should be a standard language tag. The formatFieldValues query parameter must be set to true, otherwise culture will not be used for formatting.
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
    private Integer top;

    /**
     * Excludes the specified number of items of the queried collection from the result.
     */
    private Integer skip;

    /**
     * Indicates whether the total count of items within a collection are returned in the result.
     */
    private Boolean count;

    /**
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForListEntries} The return value
     */
    public ParametersForListEntries setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
        return this;
    }

    /**
     * The requested repository ID.
     *
     * @return {@link String} The return value
     */
    public String getRepositoryId() {
        return this.repositoryId;
    }

    /**
     * Sets the value of the entryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param entryId The folder ID.
     * @return {@link ParametersForListEntries} The return value
     */
    public ParametersForListEntries setEntryId(Integer entryId) {
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
     * Sets the value of the groupByEntryType parameter and returns the current object, to enable chaining further setters.
     *
     * @param groupByEntryType Indicates if the result should be grouped by entry type or not. The default value is false.
     * @return {@link ParametersForListEntries} The return value
     */
    public ParametersForListEntries setGroupByEntryType(Boolean groupByEntryType) {
        this.groupByEntryType = groupByEntryType;
        return this;
    }

    /**
     * Indicates if the result should be grouped by entry type or not. The default value is false.
     *
     * @return {@link Boolean} The return value
     */
    public Boolean isGroupByEntryType() {
        return this.groupByEntryType;
    }

    /**
     * Sets the value of the fields parameter and returns the current object, to enable chaining further setters.
     *
     * @param fields Optional array of field names. Field values corresponding to the given field names will be returned for each entry.
     * @return {@link ParametersForListEntries} The return value
     */
    public ParametersForListEntries setFields(String... fields) {
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
     * Sets the value of the formatFieldValues parameter and returns the current object, to enable chaining further setters.
     *
     * @param formatFieldValues Indicates if field values should be formatted. Only applicable if Fields are specified. The default value is false.
     * @return {@link ParametersForListEntries} The return value
     */
    public ParametersForListEntries setFormatFieldValues(Boolean formatFieldValues) {
        this.formatFieldValues = formatFieldValues;
        return this;
    }

    /**
     * Indicates if field values should be formatted. Only applicable if Fields are specified. The default value is false.
     *
     * @return {@link Boolean} The return value
     */
    public Boolean isFormatFieldValues() {
        return this.formatFieldValues;
    }

    /**
     * Sets the value of the prefer parameter and returns the current object, to enable chaining further setters.
     *
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @return {@link ParametersForListEntries} The return value
     */
    public ParametersForListEntries setPrefer(String prefer) {
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
     * Sets the value of the culture parameter and returns the current object, to enable chaining further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting. The value should be a standard language tag. The formatFieldValues query parameter must be set to true, otherwise culture will not be used for formatting.
     * @return {@link ParametersForListEntries} The return value
     */
    public ParametersForListEntries setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting. The value should be a standard language tag. The formatFieldValues query parameter must be set to true, otherwise culture will not be used for formatting.
     *
     * @return {@link String} The return value
     */
    public String getCulture() {
        return this.culture;
    }

    /**
     * Sets the value of the select parameter and returns the current object, to enable chaining further setters.
     *
     * @param select Limits the properties returned in the result.
     * @return {@link ParametersForListEntries} The return value
     */
    public ParametersForListEntries setSelect(String select) {
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
     * @return {@link ParametersForListEntries} The return value
     */
    public ParametersForListEntries setOrderby(String orderby) {
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
     * @return {@link ParametersForListEntries} The return value
     */
    public ParametersForListEntries setTop(Integer top) {
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
     * Sets the value of the skip parameter and returns the current object, to enable chaining further setters.
     *
     * @param skip Excludes the specified number of items of the queried collection from the result.
     * @return {@link ParametersForListEntries} The return value
     */
    public ParametersForListEntries setSkip(Integer skip) {
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
     * Sets the value of the count parameter and returns the current object, to enable chaining further setters.
     *
     * @param count Indicates whether the total count of items within a collection are returned in the result.
     * @return {@link ParametersForListEntries} The return value
     */
    public ParametersForListEntries setCount(Boolean count) {
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
