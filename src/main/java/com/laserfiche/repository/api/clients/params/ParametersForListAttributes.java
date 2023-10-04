package com.laserfiche.repository.api.clients.params;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.AttributesClientImpl#listAttributes(ParametersForListAttributes) listAttributes}.
 */
public class ParametersForListAttributes {

    /**
     * The requested repository ID.
     */
    private String repositoryId;

    /**
     * Indicates if attributes associated with the &quot;Everyone&quot; group or the currently authenticated user is returned. The default value is false.
     */
    private Boolean everyone;

    /**
     * An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     */
    private String prefer;

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
     * @return {@link ParametersForListAttributes} The return value
     */
    public ParametersForListAttributes setRepositoryId(String repositoryId) {
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
     * Sets the value of the everyone parameter and returns the current object, to enable chaining further setters.
     *
     * @param everyone Indicates if attributes associated with the &quot;Everyone&quot; group or the currently authenticated user is returned. The default value is false.
     * @return {@link ParametersForListAttributes} The return value
     */
    public ParametersForListAttributes setEveryone(Boolean everyone) {
        this.everyone = everyone;
        return this;
    }

    /**
     * Indicates if attributes associated with the &amp;quot;Everyone&amp;quot; group or the currently authenticated user is returned. The default value is false.
     *
     * @return {@link Boolean} The return value
     */
    public Boolean isEveryone() {
        return this.everyone;
    }

    /**
     * Sets the value of the prefer parameter and returns the current object, to enable chaining further setters.
     *
     * @param prefer An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     * @return {@link ParametersForListAttributes} The return value
     */
    public ParametersForListAttributes setPrefer(String prefer) {
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
     * Sets the value of the select parameter and returns the current object, to enable chaining further setters.
     *
     * @param select Limits the properties returned in the result.
     * @return {@link ParametersForListAttributes} The return value
     */
    public ParametersForListAttributes setSelect(String select) {
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
     * @return {@link ParametersForListAttributes} The return value
     */
    public ParametersForListAttributes setOrderby(String orderby) {
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
     * @return {@link ParametersForListAttributes} The return value
     */
    public ParametersForListAttributes setTop(Integer top) {
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
     * @return {@link ParametersForListAttributes} The return value
     */
    public ParametersForListAttributes setSkip(Integer skip) {
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
     * @return {@link ParametersForListAttributes} The return value
     */
    public ParametersForListAttributes setCount(Boolean count) {
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