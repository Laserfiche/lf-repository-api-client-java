package com.laserfiche.repository.api.clients.params;

import com.laserfiche.repository.api.clients.impl.model.*;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ParametersForExportDocument {

    /**
     * The requested repository ID.
     */
    String repoId;

    /**
     * The requested document ID.
     */
    int entryId;

    /**
     * An optional header used to retrieve partial content of the edoc. Only supports single
     *            range with byte unit.
     */
    String range;

    Consumer<InputStream> inputStreamConsumer;

    public ParametersForExportDocument setRepoId(String repoId) {
        this.repoId = repoId;
        return this;
    }

    public String getRepoId() {
        return this.repoId;
    }

    public ParametersForExportDocument setEntryId(int entryId) {
        this.entryId = entryId;
        return this;
    }

    public int getEntryId() {
        return this.entryId;
    }

    public ParametersForExportDocument setRange(String range) {
        this.range = range;
        return this;
    }

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
