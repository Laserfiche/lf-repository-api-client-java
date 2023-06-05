package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.SimpleSearchRequest;

/**
 * The encapsulated parameters for {@link
 * com.laserfiche.repository.api.clients.impl.SimpleSearchesClientImpl#createSimpleSearchOperation(ParametersForCreateSimpleSearchOperation)
 * createSimpleSearchOperation}.
 */
public class ParametersForCreateSimpleSearchOperation {

    /** Limits the properties returned in the result. */
    private String select;

    /** Specifies the order in which items are returned. The maximum number of expressions is 5. */
    private String orderby;

    /**
     * Indicates whether the total count of items within a collection are returned in the result.
     */
    private Boolean count;

    /** The requested repository ID. */
    private String repoId;

    /**
     * Optional array of field names. Field values corresponding to the given field names will be
     * returned for each search result.
     */
    private String[] fields;

    /** Boolean for if field values should be formatted. Only applicable if Fields are specified. */
    private Boolean formatFields;

    private SimpleSearchRequest requestBody;

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag. The formatFields query parameter must be set to
     * true, otherwise culture will not be used for formatting.
     */
    private String culture;

    /**
     * Sets the value of the select parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param select Limits the properties returned in the result.
     * @return {@link ParametersForCreateSimpleSearchOperation} The return value
     */
    public ParametersForCreateSimpleSearchOperation setSelect(String select) {
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
     * @return {@link ParametersForCreateSimpleSearchOperation} The return value
     */
    public ParametersForCreateSimpleSearchOperation setOrderby(String orderby) {
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
     * Sets the value of the count parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param count Indicates whether the total count of items within a collection are returned in
     *     the result.
     * @return {@link ParametersForCreateSimpleSearchOperation} The return value
     */
    public ParametersForCreateSimpleSearchOperation setCount(Boolean count) {
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

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForCreateSimpleSearchOperation} The return value
     */
    public ParametersForCreateSimpleSearchOperation setRepoId(String repoId) {
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
     * Sets the value of the fields parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param fields Optional array of field names. Field values corresponding to the given field
     *     names will be returned for each search result.
     * @return {@link ParametersForCreateSimpleSearchOperation} The return value
     */
    public ParametersForCreateSimpleSearchOperation setFields(String[] fields) {
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
     * @return {@link ParametersForCreateSimpleSearchOperation} The return value
     */
    public ParametersForCreateSimpleSearchOperation setFormatFields(Boolean formatFields) {
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

    public ParametersForCreateSimpleSearchOperation setRequestBody(
            SimpleSearchRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public SimpleSearchRequest getRequestBody() {
        return this.requestBody;
    }

    /**
     * Sets the value of the culture parameter and returns the current object, to enable chaining
     * further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used
     *     for formatting. The value should be a standard language tag. The formatFields query
     *     parameter must be set to true, otherwise culture will not be used for formatting.
     * @return {@link ParametersForCreateSimpleSearchOperation} The return value
     */
    public ParametersForCreateSimpleSearchOperation setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag. The formatFields query parameter must be set to
     * true, otherwise culture will not be used for formatting.
     *
     * @return {@link String} The return value
     */
    public String getCulture() {
        return this.culture;
    }
}
