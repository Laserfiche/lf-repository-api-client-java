package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * Represents a context hit for a search result.
 */
@Schema(description = "Represents a context hit for a search result.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchContextHit {

    @JsonProperty("hitType")
    private HitType hitType = null;

    @JsonProperty("isAnnotationHit")
    private Boolean isAnnotationHit = null;

    @JsonProperty("annotationId")
    private Integer annotationId = null;

    @JsonProperty("pageNumber")
    private Integer pageNumber = null;

    @JsonProperty("pageOffset")
    private Integer pageOffset = null;

    @JsonProperty("context")
    private String context = null;

    @JsonProperty("highlight1Offset")
    private Integer highlight1Offset = null;

    @JsonProperty("highlight1Length")
    private Integer highlight1Length = null;

    @JsonProperty("highlight2Offset")
    private Integer highlight2Offset = null;

    @JsonProperty("highlight2Length")
    private Integer highlight2Length = null;

    @JsonProperty("hitWidth")
    private Integer hitWidth = null;

    @JsonProperty("edocHitCount")
    private Integer edocHitCount = null;

    @JsonProperty("fieldHitCount")
    private Integer fieldHitCount = null;

    @JsonProperty("fieldName")
    private String fieldName = null;

    @JsonProperty("hitNumber")
    private Integer hitNumber = null;

    public SearchContextHit hitType(HitType hitType) {
        this.hitType = hitType;
        return this;
    }

    /**
     * Returns hitType
     * @return hitType
     */
    @Schema(description = "")
    public HitType getHitType() {
        return hitType;
    }

    public void setHitType(HitType hitType) {
        this.hitType = hitType;
    }

    public SearchContextHit isAnnotationHit(Boolean isAnnotationHit) {
        this.isAnnotationHit = isAnnotationHit;
        return this;
    }

    /**
     * Returns a boolean indicating if this context hit occurs on an annotation.
     * @return isAnnotationHit
     */
    @Schema(description = "A boolean indicating if this context hit occurs on an annotation.")
    @JsonProperty("isAnnotationHit")
    public Boolean isAnnotationHit() {
        return isAnnotationHit;
    }

    public void setIsAnnotationHit(Boolean isAnnotationHit) {
        this.isAnnotationHit = isAnnotationHit;
    }

    public SearchContextHit annotationId(Integer annotationId) {
        this.annotationId = annotationId;
        return this;
    }

    /**
     * Returns the ID of the annotation that the context hit is in.
     * @return annotationId
     */
    @Schema(description = "The ID of the annotation that the context hit is in.")
    public Integer getAnnotationId() {
        return annotationId;
    }

    public void setAnnotationId(Integer annotationId) {
        this.annotationId = annotationId;
    }

    public SearchContextHit pageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    /**
     * Returns the page number in the document of the search hit&#x27;s context.
     * @return pageNumber
     */
    @Schema(description = "The page number in the document of the search hit's context.")
    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public SearchContextHit pageOffset(Integer pageOffset) {
        this.pageOffset = pageOffset;
        return this;
    }

    /**
     * Returns the offset from the beginning of the page of the starting character of the search hit&#x27;s context line.
     * @return pageOffset
     */
    @Schema(description = "The offset from the beginning of the page of the starting character of the search hit's context line.")
    public Integer getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(Integer pageOffset) {
        this.pageOffset = pageOffset;
    }

    public SearchContextHit context(String context) {
        this.context = context;
        return this;
    }

    /**
     * Returns the line of context for the search hit.
     * @return context
     */
    @Schema(description = "The line of context for the search hit.")
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public SearchContextHit highlight1Offset(Integer highlight1Offset) {
        this.highlight1Offset = highlight1Offset;
        return this;
    }

    /**
     * Returns the character offset from the beginning of the context line of the start of the first highlight.
     * @return highlight1Offset
     */
    @Schema(description = "The character offset from the beginning of the context line of the start of the first highlight.")
    public Integer getHighlight1Offset() {
        return highlight1Offset;
    }

    public void setHighlight1Offset(Integer highlight1Offset) {
        this.highlight1Offset = highlight1Offset;
    }

    public SearchContextHit highlight1Length(Integer highlight1Length) {
        this.highlight1Length = highlight1Length;
        return this;
    }

    /**
     * Returns the length of the first highlight in characters.
     * @return highlight1Length
     */
    @Schema(description = "The length of the first highlight in characters.")
    public Integer getHighlight1Length() {
        return highlight1Length;
    }

    public void setHighlight1Length(Integer highlight1Length) {
        this.highlight1Length = highlight1Length;
    }

    public SearchContextHit highlight2Offset(Integer highlight2Offset) {
        this.highlight2Offset = highlight2Offset;
        return this;
    }

    /**
     * Returns the character offset from the beginning of the context line of the start of the second highlight.
     * @return highlight2Offset
     */
    @Schema(description = "The character offset from the beginning of the context line of the start of the second highlight.")
    public Integer getHighlight2Offset() {
        return highlight2Offset;
    }

    public void setHighlight2Offset(Integer highlight2Offset) {
        this.highlight2Offset = highlight2Offset;
    }

    public SearchContextHit highlight2Length(Integer highlight2Length) {
        this.highlight2Length = highlight2Length;
        return this;
    }

    /**
     * Returns the length of the second highlight in characters.
     * @return highlight2Length
     */
    @Schema(description = "The length of the second highlight in characters.")
    public Integer getHighlight2Length() {
        return highlight2Length;
    }

    public void setHighlight2Length(Integer highlight2Length) {
        this.highlight2Length = highlight2Length;
    }

    public SearchContextHit hitWidth(Integer hitWidth) {
        this.hitWidth = hitWidth;
        return this;
    }

    /**
     * Returns the number of words in the context hit.
     * @return hitWidth
     */
    @Schema(description = "The number of words in the context hit.")
    public Integer getHitWidth() {
        return hitWidth;
    }

    public void setHitWidth(Integer hitWidth) {
        this.hitWidth = hitWidth;
    }

    public SearchContextHit edocHitCount(Integer edocHitCount) {
        this.edocHitCount = edocHitCount;
        return this;
    }

    /**
     * Returns the number of hits in the electronic document.
     * @return edocHitCount
     */
    @Schema(description = "The number of hits in the electronic document.")
    public Integer getEdocHitCount() {
        return edocHitCount;
    }

    public void setEdocHitCount(Integer edocHitCount) {
        this.edocHitCount = edocHitCount;
    }

    public SearchContextHit fieldHitCount(Integer fieldHitCount) {
        this.fieldHitCount = fieldHitCount;
        return this;
    }

    /**
     * Returns the number of hits in the template.
     * @return fieldHitCount
     */
    @Schema(description = "The number of hits in the template.")
    public Integer getFieldHitCount() {
        return fieldHitCount;
    }

    public void setFieldHitCount(Integer fieldHitCount) {
        this.fieldHitCount = fieldHitCount;
    }

    public SearchContextHit fieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    /**
     * Returns the name of a template field containing the hit.
     * @return fieldName
     */
    @Schema(description = "The name of a template field containing the hit.")
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public SearchContextHit hitNumber(Integer hitNumber) {
        this.hitNumber = hitNumber;
        return this;
    }

    /**
     * Returns the hit number.
     * @return hitNumber
     */
    @Schema(description = "The hit number.")
    public Integer getHitNumber() {
        return hitNumber;
    }

    public void setHitNumber(Integer hitNumber) {
        this.hitNumber = hitNumber;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SearchContextHit searchContextHit = (SearchContextHit) o;
        return Objects.equals(this.hitType, searchContextHit.hitType) && Objects.equals(this.isAnnotationHit, searchContextHit.isAnnotationHit) && Objects.equals(this.annotationId, searchContextHit.annotationId) && Objects.equals(this.pageNumber, searchContextHit.pageNumber) && Objects.equals(this.pageOffset, searchContextHit.pageOffset) && Objects.equals(this.context, searchContextHit.context) && Objects.equals(this.highlight1Offset, searchContextHit.highlight1Offset) && Objects.equals(this.highlight1Length, searchContextHit.highlight1Length) && Objects.equals(this.highlight2Offset, searchContextHit.highlight2Offset) && Objects.equals(this.highlight2Length, searchContextHit.highlight2Length) && Objects.equals(this.hitWidth, searchContextHit.hitWidth) && Objects.equals(this.edocHitCount, searchContextHit.edocHitCount) && Objects.equals(this.fieldHitCount, searchContextHit.fieldHitCount) && Objects.equals(this.fieldName, searchContextHit.fieldName) && Objects.equals(this.hitNumber, searchContextHit.hitNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hitType, isAnnotationHit, annotationId, pageNumber, pageOffset, context, highlight1Offset, highlight1Length, highlight2Offset, highlight2Length, hitWidth, edocHitCount, fieldHitCount, fieldName, hitNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SearchContextHit {\n");
        sb.append("    hitType: ").append(toIndentedString(hitType)).append("\n");
        sb.append("    isAnnotationHit: ").append(toIndentedString(isAnnotationHit)).append("\n");
        sb.append("    annotationId: ").append(toIndentedString(annotationId)).append("\n");
        sb.append("    pageNumber: ").append(toIndentedString(pageNumber)).append("\n");
        sb.append("    pageOffset: ").append(toIndentedString(pageOffset)).append("\n");
        sb.append("    context: ").append(toIndentedString(context)).append("\n");
        sb.append("    highlight1Offset: ").append(toIndentedString(highlight1Offset)).append("\n");
        sb.append("    highlight1Length: ").append(toIndentedString(highlight1Length)).append("\n");
        sb.append("    highlight2Offset: ").append(toIndentedString(highlight2Offset)).append("\n");
        sb.append("    highlight2Length: ").append(toIndentedString(highlight2Length)).append("\n");
        sb.append("    hitWidth: ").append(toIndentedString(hitWidth)).append("\n");
        sb.append("    edocHitCount: ").append(toIndentedString(edocHitCount)).append("\n");
        sb.append("    fieldHitCount: ").append(toIndentedString(fieldHitCount)).append("\n");
        sb.append("    fieldName: ").append(toIndentedString(fieldName)).append("\n");
        sb.append("    hitNumber: ").append(toIndentedString(hitNumber)).append("\n");
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
