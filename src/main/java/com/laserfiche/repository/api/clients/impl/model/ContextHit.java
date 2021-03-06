package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

/**
 * ContextHit
 */

public class ContextHit {
  @SerializedName("hitType")
  private HitType hitType = null;

  @SerializedName("isAnnotationHit")
  private Boolean isAnnotationHit = null;

  @SerializedName("annotationId")
  private Integer annotationId = null;

  @SerializedName("pageNumber")
  private Integer pageNumber = null;

  @SerializedName("pageOffset")
  private Integer pageOffset = null;

  @SerializedName("context")
  private String context = null;

  @SerializedName("highlight1Offset")
  private Integer highlight1Offset = null;

  @SerializedName("highlight1Length")
  private Integer highlight1Length = null;

  @SerializedName("highlight2Offset")
  private Integer highlight2Offset = null;

  @SerializedName("highlight2Length")
  private Integer highlight2Length = null;

  @SerializedName("hitWidth")
  private Integer hitWidth = null;

  @SerializedName("edocHitCount")
  private Integer edocHitCount = null;

  @SerializedName("fieldHitCount")
  private Integer fieldHitCount = null;

  @SerializedName("fieldName")
  private String fieldName = null;

  @SerializedName("hitNumber")
  private Integer hitNumber = null;

  public ContextHit hitType(HitType hitType) {
    this.hitType = hitType;
    return this;
  }

   /**
   * Get hitType
   * @return hitType
  **/
  @Schema(description = "")
  public HitType getHitType() {
    return hitType;
  }

  public void setHitType(HitType hitType) {
    this.hitType = hitType;
  }

  public ContextHit isAnnotationHit(Boolean isAnnotationHit) {
    this.isAnnotationHit = isAnnotationHit;
    return this;
  }

   /**
   * Get isAnnotationHit
   * @return isAnnotationHit
  **/
  @Schema(description = "")
  public Boolean isIsAnnotationHit() {
    return isAnnotationHit;
  }

  public void setIsAnnotationHit(Boolean isAnnotationHit) {
    this.isAnnotationHit = isAnnotationHit;
  }

  public ContextHit annotationId(Integer annotationId) {
    this.annotationId = annotationId;
    return this;
  }

   /**
   * Get annotationId
   * @return annotationId
  **/
  @Schema(description = "")
  public Integer getAnnotationId() {
    return annotationId;
  }

  public void setAnnotationId(Integer annotationId) {
    this.annotationId = annotationId;
  }

  public ContextHit pageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
    return this;
  }

   /**
   * Get pageNumber
   * @return pageNumber
  **/
  @Schema(description = "")
  public Integer getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  public ContextHit pageOffset(Integer pageOffset) {
    this.pageOffset = pageOffset;
    return this;
  }

   /**
   * Get pageOffset
   * @return pageOffset
  **/
  @Schema(description = "")
  public Integer getPageOffset() {
    return pageOffset;
  }

  public void setPageOffset(Integer pageOffset) {
    this.pageOffset = pageOffset;
  }

  public ContextHit context(String context) {
    this.context = context;
    return this;
  }

   /**
   * Get context
   * @return context
  **/
  @Schema(description = "")
  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public ContextHit highlight1Offset(Integer highlight1Offset) {
    this.highlight1Offset = highlight1Offset;
    return this;
  }

   /**
   * Get highlight1Offset
   * @return highlight1Offset
  **/
  @Schema(description = "")
  public Integer getHighlight1Offset() {
    return highlight1Offset;
  }

  public void setHighlight1Offset(Integer highlight1Offset) {
    this.highlight1Offset = highlight1Offset;
  }

  public ContextHit highlight1Length(Integer highlight1Length) {
    this.highlight1Length = highlight1Length;
    return this;
  }

   /**
   * Get highlight1Length
   * @return highlight1Length
  **/
  @Schema(description = "")
  public Integer getHighlight1Length() {
    return highlight1Length;
  }

  public void setHighlight1Length(Integer highlight1Length) {
    this.highlight1Length = highlight1Length;
  }

  public ContextHit highlight2Offset(Integer highlight2Offset) {
    this.highlight2Offset = highlight2Offset;
    return this;
  }

   /**
   * Get highlight2Offset
   * @return highlight2Offset
  **/
  @Schema(description = "")
  public Integer getHighlight2Offset() {
    return highlight2Offset;
  }

  public void setHighlight2Offset(Integer highlight2Offset) {
    this.highlight2Offset = highlight2Offset;
  }

  public ContextHit highlight2Length(Integer highlight2Length) {
    this.highlight2Length = highlight2Length;
    return this;
  }

   /**
   * Get highlight2Length
   * @return highlight2Length
  **/
  @Schema(description = "")
  public Integer getHighlight2Length() {
    return highlight2Length;
  }

  public void setHighlight2Length(Integer highlight2Length) {
    this.highlight2Length = highlight2Length;
  }

  public ContextHit hitWidth(Integer hitWidth) {
    this.hitWidth = hitWidth;
    return this;
  }

   /**
   * Get hitWidth
   * @return hitWidth
  **/
  @Schema(description = "")
  public Integer getHitWidth() {
    return hitWidth;
  }

  public void setHitWidth(Integer hitWidth) {
    this.hitWidth = hitWidth;
  }

  public ContextHit edocHitCount(Integer edocHitCount) {
    this.edocHitCount = edocHitCount;
    return this;
  }

   /**
   * Get edocHitCount
   * @return edocHitCount
  **/
  @Schema(description = "")
  public Integer getEdocHitCount() {
    return edocHitCount;
  }

  public void setEdocHitCount(Integer edocHitCount) {
    this.edocHitCount = edocHitCount;
  }

  public ContextHit fieldHitCount(Integer fieldHitCount) {
    this.fieldHitCount = fieldHitCount;
    return this;
  }

   /**
   * Get fieldHitCount
   * @return fieldHitCount
  **/
  @Schema(description = "")
  public Integer getFieldHitCount() {
    return fieldHitCount;
  }

  public void setFieldHitCount(Integer fieldHitCount) {
    this.fieldHitCount = fieldHitCount;
  }

  public ContextHit fieldName(String fieldName) {
    this.fieldName = fieldName;
    return this;
  }

   /**
   * Get fieldName
   * @return fieldName
  **/
  @Schema(description = "")
  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public ContextHit hitNumber(Integer hitNumber) {
    this.hitNumber = hitNumber;
    return this;
  }

   /**
   * Get hitNumber
   * @return hitNumber
  **/
  @Schema(description = "")
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
    ContextHit contextHit = (ContextHit) o;
    return Objects.equals(this.hitType, contextHit.hitType) &&
        Objects.equals(this.isAnnotationHit, contextHit.isAnnotationHit) &&
        Objects.equals(this.annotationId, contextHit.annotationId) &&
        Objects.equals(this.pageNumber, contextHit.pageNumber) &&
        Objects.equals(this.pageOffset, contextHit.pageOffset) &&
        Objects.equals(this.context, contextHit.context) &&
        Objects.equals(this.highlight1Offset, contextHit.highlight1Offset) &&
        Objects.equals(this.highlight1Length, contextHit.highlight1Length) &&
        Objects.equals(this.highlight2Offset, contextHit.highlight2Offset) &&
        Objects.equals(this.highlight2Length, contextHit.highlight2Length) &&
        Objects.equals(this.hitWidth, contextHit.hitWidth) &&
        Objects.equals(this.edocHitCount, contextHit.edocHitCount) &&
        Objects.equals(this.fieldHitCount, contextHit.fieldHitCount) &&
        Objects.equals(this.fieldName, contextHit.fieldName) &&
        Objects.equals(this.hitNumber, contextHit.hitNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hitType, isAnnotationHit, annotationId, pageNumber, pageOffset, context, highlight1Offset, highlight1Length, highlight2Offset, highlight2Length, hitWidth, edocHitCount, fieldHitCount, fieldName, hitNumber);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContextHit {\n");
    
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

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
