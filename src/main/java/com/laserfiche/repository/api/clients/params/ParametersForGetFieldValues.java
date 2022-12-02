package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForGetFieldValues {

    /**
     * The requested repository ID.
     */
    String repoId;

    /**
     * The requested entry ID.
     */
    int entryId;

    /**
     * An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     */
    String prefer;

    /**
     * An optional query parameter used to indicate if the field values should be formatted.
     *            The default value is false.
     */
    boolean formatValue;

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     *            The value should be a standard language tag. The formatValue query parameter must be set to true, otherwise
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

    public ParametersForGetFieldValues setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetFieldValues setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    public int getEntryId() {
        return this.entryId;
    }

    public ParametersForGetFieldValues setPrefer(String prefer) {
        this.prefer = prefer;
        return this;
    }

    public String getPrefer() {
        return this.prefer;
    }

    public ParametersForGetFieldValues setFormatValue(boolean formatValue) {
        this.formatValue = formatValue;
        return this;
    }

    public boolean isFormatValue() {
        return this.formatValue;
    }

    public ParametersForGetFieldValues setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    public String getCulture() {
        return this.culture;
    }

    public ParametersForGetFieldValues setSelect(String select) {
        this.select = select;
        return this;
    }

    public String getSelect() {
        return this.select;
    }

    public ParametersForGetFieldValues setOrderby(String orderby) {
        this.orderby = orderby;
        return this;
    }

    public String getOrderby() {
        return this.orderby;
    }

    public ParametersForGetFieldValues setTop(int top) {
        this.top = top;
        return this;
    }

    public int getTop() {
        return this.top;
    }

    public ParametersForGetFieldValues setSkip(int skip) {
        this.skip = skip;
        return this;
    }

    public int getSkip() {
        return this.skip;
    }

    public ParametersForGetFieldValues setCount(boolean count) {
        this.count = count;
        return this;
    }

    public boolean isCount() {
        return this.count;
    }
}
