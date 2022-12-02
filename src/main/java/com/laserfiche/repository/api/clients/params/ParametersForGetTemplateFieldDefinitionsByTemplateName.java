package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForGetTemplateFieldDefinitionsByTemplateName {

    /**
     * The requested repository ID.
     */
    String repoId;

    /**
     * A required query parameter for the requested template name.
     */
    String templateName;

    /**
     * An optional OData header. Can be used to set the maximum page size using odata.maxpagesize.
     */
    String prefer;

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     *            The value should be a standard language tag.
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

    public ParametersForGetTemplateFieldDefinitionsByTemplateName setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetTemplateFieldDefinitionsByTemplateName setTemplateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public ParametersForGetTemplateFieldDefinitionsByTemplateName setPrefer(String prefer) {
        this.prefer = prefer;
        return this;
    }

    public String getPrefer() {
        return this.prefer;
    }

    public ParametersForGetTemplateFieldDefinitionsByTemplateName setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    public String getCulture() {
        return this.culture;
    }

    public ParametersForGetTemplateFieldDefinitionsByTemplateName setSelect(String select) {
        this.select = select;
        return this;
    }

    public String getSelect() {
        return this.select;
    }

    public ParametersForGetTemplateFieldDefinitionsByTemplateName setOrderby(String orderby) {
        this.orderby = orderby;
        return this;
    }

    public String getOrderby() {
        return this.orderby;
    }

    public ParametersForGetTemplateFieldDefinitionsByTemplateName setTop(int top) {
        this.top = top;
        return this;
    }

    public int getTop() {
        return this.top;
    }

    public ParametersForGetTemplateFieldDefinitionsByTemplateName setSkip(int skip) {
        this.skip = skip;
        return this;
    }

    public int getSkip() {
        return this.skip;
    }

    public ParametersForGetTemplateFieldDefinitionsByTemplateName setCount(boolean count) {
        this.count = count;
        return this;
    }

    public boolean isCount() {
        return this.count;
    }
}
