package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * PDF-related options for importing an entry.
 */
@Schema(description = "PDF-related options for importing an entry.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImportEntryRequestPdfOptions {

    @JsonProperty("generateText")
    private Boolean generateText = false;

    @JsonProperty("generatePages")
    private Boolean generatePages = false;

    @JsonProperty("generatePagesImageType")
    private GeneratePagesImageType generatePagesImageType = null;

    @JsonProperty("keepPdfAfterImport")
    private Boolean keepPdfAfterImport = true;

    public ImportEntryRequestPdfOptions generateText(Boolean generateText) {
        this.generateText = generateText;
        return this;
    }

    /**
     * Returns indicates if the import operation should generate text. The default value is false.
     * @return generateText
     */
    @Schema(description = "Indicates if the import operation should generate text. The default value is false.")
    public Boolean isGenerateText() {
        return generateText;
    }

    public void setGenerateText(Boolean generateText) {
        this.generateText = generateText;
    }

    public ImportEntryRequestPdfOptions generatePages(Boolean generatePages) {
        this.generatePages = generatePages;
        return this;
    }

    /**
     * Returns indicates if the import operation should generate image pages. The default value is false.
     * @return generatePages
     */
    @Schema(description = "Indicates if the import operation should generate image pages. The default value is false.")
    public Boolean isGeneratePages() {
        return generatePages;
    }

    public void setGeneratePages(Boolean generatePages) {
        this.generatePages = generatePages;
    }

    public ImportEntryRequestPdfOptions generatePagesImageType(GeneratePagesImageType generatePagesImageType) {
        this.generatePagesImageType = generatePagesImageType;
        return this;
    }

    /**
     * Returns generatePagesImageType
     * @return generatePagesImageType
     */
    @Schema(description = "")
    public GeneratePagesImageType getGeneratePagesImageType() {
        return generatePagesImageType;
    }

    public void setGeneratePagesImageType(GeneratePagesImageType generatePagesImageType) {
        this.generatePagesImageType = generatePagesImageType;
    }

    public ImportEntryRequestPdfOptions keepPdfAfterImport(Boolean keepPdfAfterImport) {
        this.keepPdfAfterImport = keepPdfAfterImport;
        return this;
    }

    /**
     * Returns indicates if the PDF file should be retained as an electronic document after generating image pages. The default value is true. This option is only applicable when GeneratePages is true.
     * @return keepPdfAfterImport
     */
    @Schema(description = "Indicates if the PDF file should be retained as an electronic document after generating image pages. The default value is true. This option is only applicable when GeneratePages is true.")
    public Boolean isKeepPdfAfterImport() {
        return keepPdfAfterImport;
    }

    public void setKeepPdfAfterImport(Boolean keepPdfAfterImport) {
        this.keepPdfAfterImport = keepPdfAfterImport;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ImportEntryRequestPdfOptions importEntryRequestPdfOptions = (ImportEntryRequestPdfOptions) o;
        return Objects.equals(this.generateText, importEntryRequestPdfOptions.generateText) && Objects.equals(this.generatePages, importEntryRequestPdfOptions.generatePages) && Objects.equals(this.generatePagesImageType, importEntryRequestPdfOptions.generatePagesImageType) && Objects.equals(this.keepPdfAfterImport, importEntryRequestPdfOptions.keepPdfAfterImport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(generateText, generatePages, generatePagesImageType, keepPdfAfterImport);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ImportEntryRequestPdfOptions {\n");
        sb.append("    generateText: ").append(toIndentedString(generateText)).append("\n");
        sb.append("    generatePages: ").append(toIndentedString(generatePages)).append("\n");
        sb.append("    generatePagesImageType: ").append(toIndentedString(generatePagesImageType)).append("\n");
        sb.append("    keepPdfAfterImport: ").append(toIndentedString(keepPdfAfterImport)).append("\n");
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
