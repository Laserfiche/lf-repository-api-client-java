package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.File;
import java.util.Objects;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen", date = "2022-12-14T10:52:17.843020700-05:00[America/New_York]")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParentEntryIdFileNameBody {

    @JsonProperty("electronicDocument")
    private File electronicDocument = null;

    @JsonProperty("request")
    private PostEntryWithEdocMetadataRequest request = null;

    public ParentEntryIdFileNameBody electronicDocument(File electronicDocument) {
        this.electronicDocument = electronicDocument;
        return this;
    }

    /**
 * Returns electronicDocument
 * @return electronicDocument
 
*/
    @Schema(description = "")
    public File getElectronicDocument() {
        return electronicDocument;
    }

    public void setElectronicDocument(File electronicDocument) {
        this.electronicDocument = electronicDocument;
    }

    public ParentEntryIdFileNameBody request(PostEntryWithEdocMetadataRequest request) {
        this.request = request;
        return this;
    }

    /**
 * Returns request
 * @return request
 
*/
    @Schema(description = "")
    public PostEntryWithEdocMetadataRequest getRequest() {
        return request;
    }

    public void setRequest(PostEntryWithEdocMetadataRequest request) {
        this.request = request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParentEntryIdFileNameBody parentEntryIdFileNameBody = (ParentEntryIdFileNameBody) o;
        return Objects.equals(this.electronicDocument, parentEntryIdFileNameBody.electronicDocument) && Objects.equals(this.request, parentEntryIdFileNameBody.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Objects.hashCode(electronicDocument), request);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ParentEntryIdFileNameBody {\n");
        sb.append("    electronicDocument: ").append(toIndentedString(electronicDocument)).append("\n");
        sb.append("    request: ").append(toIndentedString(request)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
