package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForGetSearchResults {

    /**
     * The requested repository ID.
     */
    String repoId;

    /**
     * The requested searchToken.
     */
    String searchToken;

    /**
     * An optional query parameter used to indicate if the result should be grouped by entry type or not.
     */
    boolean groupByEntryType;

    /**
     * If the search listing should be refreshed to show updated values.
     */
    boolean refresh;

    /**
     * Optional array of field names. Field values corresponding to the given field names will be returned for each search result.
     */
    String[] fields;

    /**
     * Boolean for if field values should be formatted. Only applicable if Fields are specified.
     */
    boolean formatFields;

    /**
     * An optional odata header. Can be used to set the maximum page size using odata.maxpagesize.
     */
    String prefer;

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     *            The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise
     *            culture will not be used for formatting.
     */
    String culture;

    /**
     * Limits the properties returned in the result.
     */
    String select;

    /**
     * Specifies the order in which items are returned. The maximum number of expressions is 5.
     */
    String orderby;

    /**
     * Limits the number of items returned from a collection.
     */
    int top;

    /**
     * Excludes the specified number of items of the queried collection from the result.
     */
    int skip;

    /**
     * Indicates whether the total count of items within a collection are returned in the result.
     */
    boolean count;

    public ParametersForGetSearchResults setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetSearchResults setSearchToken(String searchToken) {
        this.searchToken = searchToken;
        return this;
    }

    public String getSearchToken() {
        return this.searchToken;
    }

    public ParametersForGetSearchResults setGroupByEntryType(boolean groupByEntryType) {
        this.groupByEntryType = groupByEntryType;
        return this;
    }

    public boolean isGroupByEntryType() {
        return this.groupByEntryType;
    }

    public ParametersForGetSearchResults setRefresh(boolean refresh) {
        this.refresh = refresh;
        return this;
    }

    public boolean isRefresh() {
        return this.refresh;
    }

    public ParametersForGetSearchResults setFields(String[] fields) {
        this.fields = fields;
        return this;
    }

    public String[] getFields() {
        return this.fields;
    }

    public ParametersForGetSearchResults setFormatFields(boolean formatFields) {
        this.formatFields = formatFields;
        return this;
    }

    public boolean isFormatFields() {
        return this.formatFields;
    }

    public ParametersForGetSearchResults setPrefer(String prefer) {
        this.prefer = prefer;
        return this;
    }

    public String getPrefer() {
        return this.prefer;
    }

    public ParametersForGetSearchResults setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    public String getCulture() {
        return this.culture;
    }

    public ParametersForGetSearchResults setSelect(String select) {
        this.select = select;
        return this;
    }

    public String getSelect() {
        return this.select;
    }

    public ParametersForGetSearchResults setOrderby(String orderby) {
        this.orderby = orderby;
        return this;
    }

    public String getOrderby() {
        return this.orderby;
    }

    public ParametersForGetSearchResults setTop(int top) {
        this.top = top;
        return this;
    }

    public int getTop() {
        return this.top;
    }

    public ParametersForGetSearchResults setSkip(int skip) {
        this.skip = skip;
        return this;
    }

    public int getSkip() {
        return this.skip;
    }

    public ParametersForGetSearchResults setCount(boolean count) {
        this.count = count;
        return this;
    }

    public boolean isCount() {
        return this.count;
    }
}
