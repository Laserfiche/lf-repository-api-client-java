package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.SimpleSearchRequest;

public class ParametersForCreateSimpleSearchOperation {

    /**
     * Limits the properties returned in the result.
     */
    private String select;

    /**
     * Specifies the order in which items are returned. The maximum number of expressions is 5.
     */
    private String orderby;

    /**
     * Indicates whether the total count of items within a collection are returned in the result.
     */
    private boolean count;

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * Optional array of field names. Field values corresponding to the given field names will be returned for each search result.
     */
    private String[] fields;

    /**
     * Boolean for if field values should be formatted. Only applicable if Fields are specified.
     */
    private boolean formatFields;

    private SimpleSearchRequest requestBody;

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise
     * culture will not be used for formatting.
     */
    private String culture;

    public ParametersForCreateSimpleSearchOperation setSelect(String select) {
        this.select = select;
        return this;
    }

    /**
     * Returns the value of 'select' field, which is limits the properties returned in the result.
     *
     * @return String The return value
     */
    public String getSelect() {
        return this.select;
    }

    public ParametersForCreateSimpleSearchOperation setOrderby(String orderby) {
        this.orderby = orderby;
        return this;
    }

    /**
     * Returns the value of 'orderby' field, which is specifies the order in which items are returned. The maximum number of expressions is 5.
     *
     * @return String The return value
     */
    public String getOrderby() {
        return this.orderby;
    }

    public ParametersForCreateSimpleSearchOperation setCount(boolean count) {
        this.count = count;
        return this;
    }

    /**
     * Returns the value of 'count' field, which is indicates whether the total count of items within a collection are returned in the result.
     *
     * @return boolean The return value
     */
    public boolean isCount() {
        return this.count;
    }

    public ParametersForCreateSimpleSearchOperation setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    /**
     * Returns the value of 'repoId' field, which is the requested repository ID.
     *
     * @return String The return value
     */
    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForCreateSimpleSearchOperation setFields(String[] fields) {
        this.fields = fields;
        return this;
    }

    /**
     * Returns the value of 'fields' field, which is optional array of field names. Field values corresponding to the given field names will be returned for each search result.
     *
     * @return String[] The return value
     */
    public String[] getFields() {
        return this.fields;
    }

    public ParametersForCreateSimpleSearchOperation setFormatFields(boolean formatFields) {
        this.formatFields = formatFields;
        return this;
    }

    /**
     * Returns the value of 'formatFields' field, which is boolean for if field values should be formatted. Only applicable if Fields are specified.
     *
     * @return boolean The return value
     */
    public boolean isFormatFields() {
        return this.formatFields;
    }

    public ParametersForCreateSimpleSearchOperation setRequestBody(SimpleSearchRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public SimpleSearchRequest getRequestBody() {
        return this.requestBody;
    }

    public ParametersForCreateSimpleSearchOperation setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * Returns the value of 'culture' field, which is an optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise
     * culture will not be used for formatting.
     *
     * @return String The return value
     */
    public String getCulture() {
        return this.culture;
    }
}
