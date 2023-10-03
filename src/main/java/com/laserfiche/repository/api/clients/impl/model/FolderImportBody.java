package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.File;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FolderImportBody {

    @JsonProperty("file")
    private File file = null;

    @JsonProperty("request")
    private ImportEntryRequest request = null;

    public FolderImportBody file(File file) {
        this.file = file;
        return this;
    }

    /**
     * Returns file
     *
     * @return file
     */
    @Schema(required = true, description = "")
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FolderImportBody request(ImportEntryRequest request) {
        this.request = request;
        return this;
    }

    /**
     * Returns request
     *
     * @return request
     */
    @Schema(required = true, description = "")
    public ImportEntryRequest getRequest() {
        return request;
    }

    public void setRequest(ImportEntryRequest request) {
        this.request = request;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FolderImportBody folderImportBody = (FolderImportBody) o;
        return Objects.equals(this.file, folderImportBody.file) && Objects.equals(this.request, folderImportBody.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Objects.hashCode(file), request);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class FolderImportBody {\n");
        sb.append("    file: ").append(toIndentedString(file)).append("\n");
        sb.append("    request: ").append(toIndentedString(request)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
