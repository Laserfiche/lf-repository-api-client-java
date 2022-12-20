package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.SimpleSearchRequest;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.SimpleSearchesClientImpl#createSimpleSearchOperation(ParametersForCreateSimpleSearchOperation) createSimpleSearchOperation}.
 */
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

    /**
     * Builder for setting the select parameter.
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
     * Builder for setting the orderby parameter.
     *
     * @param orderby Specifies the order in which items are returned. The maximum number of expressions is 5.
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
     * Builder for setting the count parameter.
     *
     * @param count Indicates whether the total count of items within a collection are returned in the result.
     * @return {@link ParametersForCreateSimpleSearchOperation} The return value
     */
    public ParametersForCreateSimpleSearchOperation setCount(boolean count) {
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

    /**
     * Builder for setting the repoId parameter.
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
     * Builder for setting the fields parameter.
     *
     * @param fields Optional array of field names. Field values corresponding to the given field names will be returned for each search result.
     * @return {@link ParametersForCreateSimpleSearchOperation} The return value
     */
    public ParametersForCreateSimpleSearchOperation setFields(String[] fields) {
        this.fields = fields;
        return this;
    }

    /**
     * Optional array of field names. Field values corresponding to the given field names will be returned for each search result.
     *
     * @return {@link String[]} The return value
     */
    public String[] getFields() {
        return this.fields;
    }

    /**
     * Builder for setting the formatFields parameter.
     *
     * @param formatFields Boolean for if field values should be formatted. Only applicable if Fields are specified.
     * @return {@link ParametersForCreateSimpleSearchOperation} The return value
     */
    public ParametersForCreateSimpleSearchOperation setFormatFields(boolean formatFields) {
        this.formatFields = formatFields;
        return this;
    }

    /**
     * Boolean for if field values should be formatted. Only applicable if Fields are specified.
     *
     * @return {@link boolean} The return value
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

    /**
     * Builder for setting the culture parameter.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting.
     *                The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise
     *                culture will not be used for formatting.
     * @return {@link ParametersForCreateSimpleSearchOperation} The return value
     */
    public ParametersForCreateSimpleSearchOperation setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     * The value should be a standard language tag. The formatFields query parameter must be set to true, otherwise
     * culture will not be used for formatting.
     *
     * @return {@link String} The return value
     */
    public String getCulture() {
        return this.culture;
    }
}
