package com.laserfiche.repository.api.clients.params;

import java.io.InputStream;
import java.util.function.Consumer;

/**
 * The encapsulated parameters for {@link com.laserfiche.repository.api.clients.impl.EntriesClientImpl#exportDocument(ParametersForExportDocument) exportDocument}.
 */
public class ParametersForExportDocument {

    /**
     * The requested repository ID.
     */
    private String repoId;

    /**
     * The requested document ID.
     */
    private int entryId;

    /**
     * An optional header used to retrieve partial content of the edoc. Only supports single
     * range with byte unit.
     */
    private String range;

    private Consumer<InputStream> inputStreamConsumer;

    /**
     * Sets the value of the repoId parameter and returns the current object, to enable chaining further setters.
     *
     * @param repoId The requested repository ID.
     * @return {@link ParametersForExportDocument} The return value
     */
    public ParametersForExportDocument setRepoId(String repoId) {
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
     * Sets the value of the entryId parameter and returns the current object, to enable chaining further setters.
     *
     * @param entryId The requested document ID.
     * @return {@link ParametersForExportDocument} The return value
     */
    public ParametersForExportDocument setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    /**
     * The requested document ID.
     *
     * @return int The return value
     */
    public int getEntryId() {
        return this.entryId;
    }

    /**
     * Sets the value of the range parameter and returns the current object, to enable chaining further setters.
     *
     * @param range An optional header used to retrieve partial content of the edoc. Only supports single
     *              range with byte unit.
     * @return {@link ParametersForExportDocument} The return value
     */
    public ParametersForExportDocument setRange(String range) {
        this.range = range;
        return this;
    }

    /**
     * An optional header used to retrieve partial content of the edoc. Only supports single
     * range with byte unit.
     *
     * @return {@link String} The return value
     */
    public String getRange() {
        return this.range;
    }

    public ParametersForExportDocument setInputStreamConsumer(Consumer<InputStream> inputStreamConsumer) {
        this.inputStreamConsumer = inputStreamConsumer;
        return this;
    }

    public Consumer<InputStream> getInputStreamConsumer() {
        return this.inputStreamConsumer;
    }
}
