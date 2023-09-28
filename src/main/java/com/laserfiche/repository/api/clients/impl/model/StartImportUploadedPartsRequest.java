package com.laserfiche.repository.api.clients.impl.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.*;

/**
 * Request body for starting an asynchronous import entry task.
 */
@Schema(description = "Request body for starting an asynchronous import entry task.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StartImportUploadedPartsRequest {

    @JsonProperty("uploadId")
    private String uploadId = null;

    @JsonProperty("partETags")
    private List<String> partETags = new ArrayList<String>();

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("autoRename")
    private Boolean autoRename = false;

    @JsonProperty("pdfOptions")
    private ImportEntryRequestPdfOptions pdfOptions = null;

    @JsonProperty("importAsElectronicDocument")
    private Boolean importAsElectronicDocument = false;

    @JsonProperty("metadata")
    private ImportEntryRequestMetadata metadata = null;

    @JsonProperty("volumeName")
    private String volumeName = null;

    public StartImportUploadedPartsRequest uploadId(String uploadId) {
        this.uploadId = uploadId;
        return this;
    }

    /**
     * Returns the UploadId received when calling the CreateMultipartUploadUrls API to request upload URLs.
     * @return uploadId
     */
    @Schema(required = true, description = "The UploadId received when calling the CreateMultipartUploadUrls API to request upload URLs.")
    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public StartImportUploadedPartsRequest partETags(List<String> partETags) {
        this.partETags = partETags;
        return this;
    }

    public StartImportUploadedPartsRequest addPartETagsItem(String partETagsItem) {
        this.partETags.add(partETagsItem);
        return this;
    }

    /**
     * Returns the array of the ETag values received when writing the file chunks into the upload URLs. The ETag values should be in the order of their associated upload URLs, i.e. {eTag received when writing to the first URL}, {eTag received when writing to the second URL}, ...
     * @return partETags
     */
    @Schema(required = true, description = "The array of the ETag values received when writing the file chunks into the upload URLs. The ETag values should be in the order of their associated upload URLs, i.e. {eTag received when writing to the first URL}, {eTag received when writing to the second URL}, ...")
    public List<String> getPartETags() {
        return partETags;
    }

    public void setPartETags(List<String> partETags) {
        this.partETags = partETags;
    }

    public StartImportUploadedPartsRequest name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Returns the name for the imported entry.
     * @return name
     */
    @Schema(required = true, description = "The name for the imported entry.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StartImportUploadedPartsRequest autoRename(Boolean autoRename) {
        this.autoRename = autoRename;
        return this;
    }

    /**
     * Returns indicates if the entry should be automatically renamed if an entry already exists with the given name in the folder. The default value is false.
     * @return autoRename
     */
    @Schema(description = "Indicates if the entry should be automatically renamed if an entry already exists with the given name in the folder. The default value is false.")
    public Boolean isAutoRename() {
        return autoRename;
    }

    public void setAutoRename(Boolean autoRename) {
        this.autoRename = autoRename;
    }

    public StartImportUploadedPartsRequest pdfOptions(ImportEntryRequestPdfOptions pdfOptions) {
        this.pdfOptions = pdfOptions;
        return this;
    }

    /**
     * Returns pdfOptions
     * @return pdfOptions
     */
    @Schema(description = "")
    public ImportEntryRequestPdfOptions getPdfOptions() {
        return pdfOptions;
    }

    public void setPdfOptions(ImportEntryRequestPdfOptions pdfOptions) {
        this.pdfOptions = pdfOptions;
    }

    public StartImportUploadedPartsRequest importAsElectronicDocument(Boolean importAsElectronicDocument) {
        this.importAsElectronicDocument = importAsElectronicDocument;
        return this;
    }

    /**
     * Returns indicates if the document should be imported as an electronic document (true) or as image pages (false). The default value is false. This option is only applicable when importing the following document types: txt, tif, tiff, bmp, pcx, jpg, jpeg, gif, png.
     * @return importAsElectronicDocument
     */
    @Schema(description = "Indicates if the document should be imported as an electronic document (true) or as image pages (false). The default value is false. This option is only applicable when importing the following document types: txt, tif, tiff, bmp, pcx, jpg, jpeg, gif, png.")
    public Boolean isImportAsElectronicDocument() {
        return importAsElectronicDocument;
    }

    public void setImportAsElectronicDocument(Boolean importAsElectronicDocument) {
        this.importAsElectronicDocument = importAsElectronicDocument;
    }

    public StartImportUploadedPartsRequest metadata(ImportEntryRequestMetadata metadata) {
        this.metadata = metadata;
        return this;
    }

    /**
     * Returns metadata
     * @return metadata
     */
    @Schema(description = "")
    public ImportEntryRequestMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(ImportEntryRequestMetadata metadata) {
        this.metadata = metadata;
    }

    public StartImportUploadedPartsRequest volumeName(String volumeName) {
        this.volumeName = volumeName;
        return this;
    }

    /**
     * Returns the name of the volume to use. Will use the default parent entry volume if not specified. This is ignored in Laserfiche Cloud.
     * @return volumeName
     */
    @Schema(description = "The name of the volume to use. Will use the default parent entry volume if not specified. This is ignored in Laserfiche Cloud.")
    public String getVolumeName() {
        return volumeName;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StartImportUploadedPartsRequest startImportUploadedPartsRequest = (StartImportUploadedPartsRequest) o;
        return Objects.equals(this.uploadId, startImportUploadedPartsRequest.uploadId) && Objects.equals(this.partETags, startImportUploadedPartsRequest.partETags) && Objects.equals(this.name, startImportUploadedPartsRequest.name) && Objects.equals(this.autoRename, startImportUploadedPartsRequest.autoRename) && Objects.equals(this.pdfOptions, startImportUploadedPartsRequest.pdfOptions) && Objects.equals(this.importAsElectronicDocument, startImportUploadedPartsRequest.importAsElectronicDocument) && Objects.equals(this.metadata, startImportUploadedPartsRequest.metadata) && Objects.equals(this.volumeName, startImportUploadedPartsRequest.volumeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uploadId, partETags, name, autoRename, pdfOptions, importAsElectronicDocument, metadata, volumeName);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class StartImportUploadedPartsRequest {\n");
        sb.append("    uploadId: ").append(toIndentedString(uploadId)).append("\n");
        sb.append("    partETags: ").append(toIndentedString(partETags)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    autoRename: ").append(toIndentedString(autoRename)).append("\n");
        sb.append("    pdfOptions: ").append(toIndentedString(pdfOptions)).append("\n");
        sb.append("    importAsElectronicDocument: ").append(toIndentedString(importAsElectronicDocument)).append("\n");
        sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
        sb.append("    volumeName: ").append(toIndentedString(volumeName)).append("\n");
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
