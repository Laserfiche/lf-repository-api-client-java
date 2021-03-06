package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.OffsetDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Entry
 */

public class Entry {
  @SerializedName("id")
  private Integer id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("parentId")
  private Integer parentId = null;

  @SerializedName("fullPath")
  private String fullPath = null;

  @SerializedName("folderPath")
  private String folderPath = null;

  @SerializedName("creator")
  private String creator = null;

  @SerializedName("creationTime")
  private OffsetDateTime creationTime = null;

  @SerializedName("lastModifiedTime")
  private OffsetDateTime lastModifiedTime = null;

  @SerializedName("entryType")
  private EntryType entryType = null;

  @SerializedName("isContainer")
  private Boolean isContainer = null;

  @SerializedName("isLeaf")
  private Boolean isLeaf = null;

  @SerializedName("templateName")
  private String templateName = null;

  @SerializedName("templateId")
  private Integer templateId = null;

  @SerializedName("templateFieldNames")
  private List<String> templateFieldNames = null;

  @SerializedName("volumeName")
  private String volumeName = null;

  @SerializedName("rowNumber")
  private Integer rowNumber = null;

  @SerializedName("fields")
  private List<EntryFieldValue> fields = null;

  public Entry id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * The ID of the entry.
   * @return id
  **/
  @Schema(description = "The ID of the entry.")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Entry name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The name of the entry.
   * @return name
  **/
  @Schema(description = "The name of the entry.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Entry parentId(Integer parentId) {
    this.parentId = parentId;
    return this;
  }

   /**
   * The ID of the parent entry.
   * @return parentId
  **/
  @Schema(description = "The ID of the parent entry.")
  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public Entry fullPath(String fullPath) {
    this.fullPath = fullPath;
    return this;
  }

   /**
   * The full path in the Laserfiche repository to the entry.
   * @return fullPath
  **/
  @Schema(description = "The full path in the Laserfiche repository to the entry.")
  public String getFullPath() {
    return fullPath;
  }

  public void setFullPath(String fullPath) {
    this.fullPath = fullPath;
  }

  public Entry folderPath(String folderPath) {
    this.folderPath = folderPath;
    return this;
  }

   /**
   * The path in the Laserfiche repository to the parent folder.
   * @return folderPath
  **/
  @Schema(description = "The path in the Laserfiche repository to the parent folder.")
  public String getFolderPath() {
    return folderPath;
  }

  public void setFolderPath(String folderPath) {
    this.folderPath = folderPath;
  }

  public Entry creator(String creator) {
    this.creator = creator;
    return this;
  }

   /**
   * The name of the user that created this entry.
   * @return creator
  **/
  @Schema(description = "The name of the user that created this entry.")
  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public Entry creationTime(OffsetDateTime creationTime) {
    this.creationTime = creationTime;
    return this;
  }

   /**
   * The creation time of the entry.
   * @return creationTime
  **/
  @Schema(description = "The creation time of the entry.")
  public OffsetDateTime getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(OffsetDateTime creationTime) {
    this.creationTime = creationTime;
  }

  public Entry lastModifiedTime(OffsetDateTime lastModifiedTime) {
    this.lastModifiedTime = lastModifiedTime;
    return this;
  }

   /**
   * The last modification time of the entry.
   * @return lastModifiedTime
  **/
  @Schema(description = "The last modification time of the entry.")
  public OffsetDateTime getLastModifiedTime() {
    return lastModifiedTime;
  }

  public void setLastModifiedTime(OffsetDateTime lastModifiedTime) {
    this.lastModifiedTime = lastModifiedTime;
  }

  public Entry entryType(EntryType entryType) {
    this.entryType = entryType;
    return this;
  }

   /**
   * The type of the entry.
   * @return entryType
  **/
  @Schema(description = "The type of the entry.")
  public EntryType getEntryType() {
    return entryType;
  }

  public void setEntryType(EntryType entryType) {
    this.entryType = entryType;
  }

   /**
   * A boolean indicating if this entry is a container object; it can have other entries as children.
   * @return isContainer
  **/
  @Schema(description = "A boolean indicating if this entry is a container object; it can have other entries as children.")
  public Boolean isIsContainer() {
    return isContainer;
  }

   /**
   * A boolean indicating if this entry is a leaf object; it cannot have other entries as children.
   * @return isLeaf
  **/
  @Schema(description = "A boolean indicating if this entry is a leaf object; it cannot have other entries as children.")
  public Boolean isIsLeaf() {
    return isLeaf;
  }

  public Entry templateName(String templateName) {
    this.templateName = templateName;
    return this;
  }

   /**
   * The name of the template assigned to this entry.
   * @return templateName
  **/
  @Schema(description = "The name of the template assigned to this entry.")
  public String getTemplateName() {
    return templateName;
  }

  public void setTemplateName(String templateName) {
    this.templateName = templateName;
  }

  public Entry templateId(Integer templateId) {
    this.templateId = templateId;
    return this;
  }

   /**
   * The id of the template assigned to this entry.
   * @return templateId
  **/
  @Schema(description = "The id of the template assigned to this entry.")
  public Integer getTemplateId() {
    return templateId;
  }

  public void setTemplateId(Integer templateId) {
    this.templateId = templateId;
  }

  public Entry templateFieldNames(List<String> templateFieldNames) {
    this.templateFieldNames = templateFieldNames;
    return this;
  }

  public Entry addTemplateFieldNamesItem(String templateFieldNamesItem) {
    if (this.templateFieldNames == null) {
      this.templateFieldNames = new ArrayList<String>();
    }
    this.templateFieldNames.add(templateFieldNamesItem);
    return this;
  }

   /**
   * The names of the fields assigned to the template assigned to this entry.
   * @return templateFieldNames
  **/
  @Schema(description = "The names of the fields assigned to the template assigned to this entry.")
  public List<String> getTemplateFieldNames() {
    return templateFieldNames;
  }

  public void setTemplateFieldNames(List<String> templateFieldNames) {
    this.templateFieldNames = templateFieldNames;
  }

  public Entry volumeName(String volumeName) {
    this.volumeName = volumeName;
    return this;
  }

   /**
   * The name of the volume that this entry is associated with.
   * @return volumeName
  **/
  @Schema(description = "The name of the volume that this entry is associated with.")
  public String getVolumeName() {
    return volumeName;
  }

  public void setVolumeName(String volumeName) {
    this.volumeName = volumeName;
  }

  public Entry rowNumber(Integer rowNumber) {
    this.rowNumber = rowNumber;
    return this;
  }

   /**
   * Row number assigned to this entry in the listing.
   * @return rowNumber
  **/
  @Schema(description = "Row number assigned to this entry in the listing.")
  public Integer getRowNumber() {
    return rowNumber;
  }

  public void setRowNumber(Integer rowNumber) {
    this.rowNumber = rowNumber;
  }

  public Entry fields(List<EntryFieldValue> fields) {
    this.fields = fields;
    return this;
  }

  public Entry addFieldsItem(EntryFieldValue fieldsItem) {
    if (this.fields == null) {
      this.fields = new ArrayList<EntryFieldValue>();
    }
    this.fields.add(fieldsItem);
    return this;
  }

   /**
   * The fields assigned to this entry.
   * @return fields
  **/
  @Schema(description = "The fields assigned to this entry.")
  public List<EntryFieldValue> getFields() {
    return fields;
  }

  public void setFields(List<EntryFieldValue> fields) {
    this.fields = fields;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Entry entry = (Entry) o;
    return Objects.equals(this.id, entry.id) &&
        Objects.equals(this.name, entry.name) &&
        Objects.equals(this.parentId, entry.parentId) &&
        Objects.equals(this.fullPath, entry.fullPath) &&
        Objects.equals(this.folderPath, entry.folderPath) &&
        Objects.equals(this.creator, entry.creator) &&
        Objects.equals(this.creationTime, entry.creationTime) &&
        Objects.equals(this.lastModifiedTime, entry.lastModifiedTime) &&
        Objects.equals(this.entryType, entry.entryType) &&
        Objects.equals(this.isContainer, entry.isContainer) &&
        Objects.equals(this.isLeaf, entry.isLeaf) &&
        Objects.equals(this.templateName, entry.templateName) &&
        Objects.equals(this.templateId, entry.templateId) &&
        Objects.equals(this.templateFieldNames, entry.templateFieldNames) &&
        Objects.equals(this.volumeName, entry.volumeName) &&
        Objects.equals(this.rowNumber, entry.rowNumber) &&
        Objects.equals(this.fields, entry.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, parentId, fullPath, folderPath, creator, creationTime, lastModifiedTime, entryType, isContainer, isLeaf, templateName, templateId, templateFieldNames, volumeName, rowNumber, fields);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Entry {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
    sb.append("    fullPath: ").append(toIndentedString(fullPath)).append("\n");
    sb.append("    folderPath: ").append(toIndentedString(folderPath)).append("\n");
    sb.append("    creator: ").append(toIndentedString(creator)).append("\n");
    sb.append("    creationTime: ").append(toIndentedString(creationTime)).append("\n");
    sb.append("    lastModifiedTime: ").append(toIndentedString(lastModifiedTime)).append("\n");
    sb.append("    entryType: ").append(toIndentedString(entryType)).append("\n");
    sb.append("    isContainer: ").append(toIndentedString(isContainer)).append("\n");
    sb.append("    isLeaf: ").append(toIndentedString(isLeaf)).append("\n");
    sb.append("    templateName: ").append(toIndentedString(templateName)).append("\n");
    sb.append("    templateId: ").append(toIndentedString(templateId)).append("\n");
    sb.append("    templateFieldNames: ").append(toIndentedString(templateFieldNames)).append("\n");
    sb.append("    volumeName: ").append(toIndentedString(volumeName)).append("\n");
    sb.append("    rowNumber: ").append(toIndentedString(rowNumber)).append("\n");
    sb.append("    fields: ").append(toIndentedString(fields)).append("\n");
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
