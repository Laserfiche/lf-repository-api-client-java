package com.laserfiche.repository.api.clients.params;

import java.io.InputStream;
import java.util.function.Consumer;

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

    public ParametersForExportDocument setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    /**
     * The requested repository ID.
     *
     * @return String The return value
     */
    public String getRepoId() {
        return this.repoId;
    }

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

    public ParametersForExportDocument setRange(String range) {
        this.range = range;
        return this;
    }

    /**
     * An optional header used to retrieve partial content of the edoc. Only supports single
     * range with byte unit.
     *
     * @return String The return value
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
