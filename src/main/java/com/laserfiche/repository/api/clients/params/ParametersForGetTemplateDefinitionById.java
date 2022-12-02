package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForGetTemplateDefinitionById {

    /**
     * The requested repository ID.
     */
    String repoId;

    /**
     * The requested template definition ID.
     */
    int templateId;

    /**
     * An optional query parameter used to indicate the locale that should be used for formatting.
     *            The value should be a standard language tag.
     */
    String culture;

    /**
     * Limits the properties returned in the result.
     */
    String select;

    public ParametersForGetTemplateDefinitionById setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForGetTemplateDefinitionById setTemplateId(int templateId) {
        this.templateId = templateId;
        return this;
    }

    public int getTemplateId() {
        return this.templateId;
    }

    public ParametersForGetTemplateDefinitionById setCulture(String culture) {
        this.culture = culture;
        return this;
    }

    public String getCulture() {
        return this.culture;
    }

    public ParametersForGetTemplateDefinitionById setSelect(String select) {
        this.select = select;
        return this;
    }

    public String getSelect() {
        return this.select;
    }
}
