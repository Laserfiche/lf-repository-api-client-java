package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Response for CreateMultipartUploadUrls.
 */
@Schema(description = "Response for CreateMultipartUploadUrls.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateMultipartUploadUrlsResponse {

    @JsonProperty("uploadId")
    private String uploadId = null;

    @JsonProperty("urls")
    private List<String> urls = null;

    public CreateMultipartUploadUrlsResponse uploadId(String uploadId) {
        this.uploadId = uploadId;
        return this;
    }

    /**
     * Returns a unique identifier for the whole upload process.
     * @return uploadId
     */
    @Schema(description = "A unique identifier for the whole upload process.")
    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public CreateMultipartUploadUrlsResponse urls(List<String> urls) {
        this.urls = urls;
        return this;
    }

    public CreateMultipartUploadUrlsResponse addUrlsItem(String urlsItem) {
        if (this.urls == null) {
            this.urls = new ArrayList<String>();
        }
        this.urls.add(urlsItem);
        return this;
    }

    /**
     * Returns a list of URLs to which the file chunk should be written.
     * @return urls
     */
    @Schema(description = "A list of URLs to which the file chunk should be written.")
    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateMultipartUploadUrlsResponse createMultipartUploadUrlsResponse = (CreateMultipartUploadUrlsResponse) o;
        return Objects.equals(this.uploadId, createMultipartUploadUrlsResponse.uploadId) && Objects.equals(this.urls, createMultipartUploadUrlsResponse.urls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uploadId, urls);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateMultipartUploadUrlsResponse {\n");
        sb.append("    uploadId: ").append(toIndentedString(uploadId)).append("\n");
        sb.append("    urls: ").append(toIndentedString(urls)).append("\n");
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
