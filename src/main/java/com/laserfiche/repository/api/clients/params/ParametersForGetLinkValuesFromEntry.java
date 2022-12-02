package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForGetLinkValuesFromEntry {

    /**
     * The requested repository ID.
     */
    String repoId;

    /**
     * The requested entry ID.
     */
    int entryId;

    /**
     * An optional odata header. Can be used to set the maximum page size using odata.maxpagesize.
     */
    String prefer;

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

    public ParametersForGetLinkValuesFromEntry setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetLinkValuesFromEntry setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    public int getEntryId() {
        return this.entryId;
    }

    public ParametersForGetLinkValuesFromEntry setPrefer(String prefer) {
        this.prefer = prefer;
        return this;
    }

    public String getPrefer() {
        return this.prefer;
    }

    public ParametersForGetLinkValuesFromEntry setSelect(String select) {
        this.select = select;
        return this;
    }

    public String getSelect() {
        return this.select;
    }

    public ParametersForGetLinkValuesFromEntry setOrderby(String orderby) {
        this.orderby = orderby;
        return this;
    }

    public String getOrderby() {
        return this.orderby;
    }

    public ParametersForGetLinkValuesFromEntry setTop(int top) {
        this.top = top;
        return this;
    }

    public int getTop() {
        return this.top;
    }

    public ParametersForGetLinkValuesFromEntry setSkip(int skip) {
        this.skip = skip;
        return this;
    }

    public int getSkip() {
        return this.skip;
    }

    public ParametersForGetLinkValuesFromEntry setCount(boolean count) {
        this.count = count;
        return this;
    }

    public boolean isCount() {
        return this.count;
    }
}
