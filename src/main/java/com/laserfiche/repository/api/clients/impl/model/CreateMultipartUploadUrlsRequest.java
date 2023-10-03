package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * Request body for creating multipart upload urls.
 */
@Schema(description = "Request body for creating multipart upload urls.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateMultipartUploadUrlsRequest {

    @JsonProperty("uploadId")
    private String uploadId = "";

    @JsonProperty("startingPartNumber")
    private Integer startingPartNumber = 1;

    @JsonProperty("numberOfParts")
    private Integer numberOfParts = null;

    @JsonProperty("fileName")
    private String fileName = null;

    @JsonProperty("mimeType")
    private String mimeType = null;

    public CreateMultipartUploadUrlsRequest uploadId(String uploadId) {
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

    public CreateMultipartUploadUrlsRequest startingPartNumber(Integer startingPartNumber) {
        this.startingPartNumber = startingPartNumber;
        return this;
    }

    /**
     * Returns determines the starting position of the requested parts among all the parts associated with this upload. The default value is 1.
     * @return startingPartNumber
     */
    @Schema(description = "Determines the starting position of the requested parts among all the parts associated with this upload. The default value is 1.")
    public Integer getStartingPartNumber() {
        return startingPartNumber;
    }

    public void setStartingPartNumber(Integer startingPartNumber) {
        this.startingPartNumber = startingPartNumber;
    }

    public CreateMultipartUploadUrlsRequest numberOfParts(Integer numberOfParts) {
        this.numberOfParts = numberOfParts;
        return this;
    }

    /**
     * Returns the value must be in the range [1, 100], meaning that in each call to the CreateMultipartUploadUrls api, a maximum of 100 Upload URLs can be requested. Further, each file chunk written to an Upload URL should be at least 5 MB. There is no minimum size limit for the last chunk.
     * @return numberOfParts
     */
    @Schema(required = true, description = "The value must be in the range [1, 100], meaning that in each call to the CreateMultipartUploadUrls api, a maximum of 100 Upload URLs can be requested. Further, each file chunk written to an Upload URL should be at least 5 MB. There is no minimum size limit for the last chunk.")
    public Integer getNumberOfParts() {
        return numberOfParts;
    }

    public void setNumberOfParts(Integer numberOfParts) {
        this.numberOfParts = numberOfParts;
    }

    public CreateMultipartUploadUrlsRequest fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    /**
     * Returns the name of the file to be uploaded. The file extension in the name will be used as the extension of the imported entry.
     * @return fileName
     */
    @Schema(description = "The name of the file to be uploaded. The file extension in the name will be used as the extension of the imported entry.")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public CreateMultipartUploadUrlsRequest mimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    /**
     * Returns the mime-type of the file to be uploaded.
     * @return mimeType
     */
    @Schema(description = "The mime-type of the file to be uploaded.")
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateMultipartUploadUrlsRequest createMultipartUploadUrlsRequest = (CreateMultipartUploadUrlsRequest) o;
        return Objects.equals(this.uploadId, createMultipartUploadUrlsRequest.uploadId) && Objects.equals(this.startingPartNumber, createMultipartUploadUrlsRequest.startingPartNumber) && Objects.equals(this.numberOfParts, createMultipartUploadUrlsRequest.numberOfParts) && Objects.equals(this.fileName, createMultipartUploadUrlsRequest.fileName) && Objects.equals(this.mimeType, createMultipartUploadUrlsRequest.mimeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uploadId, startingPartNumber, numberOfParts, fileName, mimeType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateMultipartUploadUrlsRequest {\n");
        sb.append("    uploadId: ").append(toIndentedString(uploadId)).append("\n");
        sb.append("    startingPartNumber: ").append(toIndentedString(startingPartNumber)).append("\n");
        sb.append("    numberOfParts: ").append(toIndentedString(numberOfParts)).append("\n");
        sb.append("    fileName: ").append(toIndentedString(fileName)).append("\n");
        sb.append("    mimeType: ").append(toIndentedString(mimeType)).append("\n");
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
