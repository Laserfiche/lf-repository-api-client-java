package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.SimpleSearchesClientImpl#searchEntry(ParametersForSearchEntry) searchEntry}.
 */
public class ParametersForSearchEntry {

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
    private Boolean count;

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * Optional array of field names. Field values corresponding to the given field names will be returned for each search result.
     */
    private String[] fields;

    /**
     * Indicates if field values should be formatted. Only applicable if Fields are specified. The default value is false.
     */
    private Boolean formatFieldValues;

    private SearchEntryRequest requestBody;

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting. The value should be a standard language tag. The formatFieldValues query parameter must be set to true, otherwise culture will not be used for formatting.
     */
    private String culture;

    /**
     * Sets the value of the select parameter and returns the current object, to enable chaining further setters.
     *
     * @param select Limits the properties returned in the result.
     * @return {@link ParametersForSearchEntry} The return value
     */
    public ParametersForSearchEntry setSelect(String select) {
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
     * @return {@link ParametersForSearchEntry} The return value
     */
    public ParametersForSearchEntry setOrderby(String orderby) {
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
     * Sets the value of the count parameter and returns the current object, to enable chaining further setters.
     *
     * @param count Indicates whether the total count of items within a collection are returned in the result.
     * @return {@link ParametersForSearchEntry} The return value
     */
    public ParametersForSearchEntry setCount(Boolean count) {
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
     * Sets the value of the repositoryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repositoryId The requested repository ID.
     * @return {@link ParametersForSearchEntry} The return value
     */
    public ParametersForSearchEntry setRepositoryId(String repositoryId) {
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
     * Sets the value of the fields parameter and returns the current object, to enable chaining further setters.
     *
     * @param fields Optional array of field names. Field values corresponding to the given field names will be returned for each search result.
     * @return {@link ParametersForSearchEntry} The return value
     */
    public ParametersForSearchEntry setFields(String[] fields) {
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
     * Sets the value of the formatFieldValues parameter and returns the current object, to enable chaining further setters.
     *
     * @param formatFieldValues Indicates if field values should be formatted. Only applicable if Fields are specified. The default value is false.
     * @return {@link ParametersForSearchEntry} The return value
     */
    public ParametersForSearchEntry setFormatFieldValues(Boolean formatFieldValues) {
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

    public ParametersForSearchEntry setRequestBody(SearchEntryRequest requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public SearchEntryRequest getRequestBody() {
        return this.requestBody;
    }

    /**
     * Sets the value of the culture parameter and returns the current object, to enable chaining further setters.
     *
     * @param culture An optional query parameter used to indicate the locale that should be used for formatting. The value should be a standard language tag. The formatFieldValues query parameter must be set to true, otherwise culture will not be used for formatting.
     * @return {@link ParametersForSearchEntry} The return value
     */
    public ParametersForSearchEntry setCulture(String culture) {
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
}
