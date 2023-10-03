package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.OffsetDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Base type for all types which represent entry objects in a Laserfiche repository.
 */
@Schema(description = "Base type for all types which represent entry objects in a Laserfiche repository.")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaClientCodegen")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "entryType", visible = true, defaultImpl = Entry.class)
@JsonSubTypes({ @JsonSubTypes.Type(value = Document.class, name = "Document"), @JsonSubTypes.Type(value = Folder.class, name = "Folder"), @JsonSubTypes.Type(value = Shortcut.class, name = "Shortcut"), @JsonSubTypes.Type(value = RecordSeries.class, name = "RecordSeries") })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Entry {

    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("parentId")
    private Integer parentId = null;

    @JsonProperty("fullPath")
    private String fullPath = null;

    @JsonProperty("folderPath")
    private String folderPath = null;

    @JsonProperty("creator")
    private String creator = null;

    @JsonProperty("creationTime")
    private OffsetDateTime creationTime = null;

    @JsonProperty("lastModifiedTime")
    private OffsetDateTime lastModifiedTime = null;

    @JsonProperty("entryType")
    private EntryType entryType = null;

    @JsonProperty("isContainer")
    private Boolean isContainer = null;

    @JsonProperty("isLeaf")
    private Boolean isLeaf = null;

    @JsonProperty("templateName")
    private String templateName = null;

    @JsonProperty("templateId")
    private Integer templateId = null;

    @JsonProperty("templateFieldNames")
    private List<String> templateFieldNames = null;

    @JsonProperty("volumeName")
    private String volumeName = null;

    @JsonProperty("rowNumber")
    private Integer rowNumber = null;

    @JsonProperty("fields")
    private List<Field> fields = null;

    public Entry id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Returns the ID of the entry.
     * @return id
     */
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
     * Returns the name of the entry.
     * @return name
     */
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
     * Returns the ID of the parent entry.
     * @return parentId
     */
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
     * Returns the full path in the Laserfiche repository to the entry.
     * @return fullPath
     */
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
     * Returns the path in the Laserfiche repository to the parent folder.
     * @return folderPath
     */
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
     * Returns the name of the user that created this entry.
     * @return creator
     */
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
     * Returns the creation time of the entry.
     * @return creationTime
     */
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
     * Returns the last modification time of the entry.
     * @return lastModifiedTime
     */
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
     * Returns entryType
     * @return entryType
     */
    @Schema(description = "")
    public EntryType getEntryType() {
        return entryType;
    }

    public void setEntryType(EntryType entryType) {
        this.entryType = entryType;
    }

    public Entry isContainer(Boolean isContainer) {
        this.isContainer = isContainer;
        return this;
    }

    /**
     * Returns a boolean indicating if this entry is a container object; it can have other entries as children.
     * @return isContainer
     */
    @Schema(description = "A boolean indicating if this entry is a container object; it can have other entries as children.")
    @JsonProperty("isContainer")
    public Boolean isContainer() {
        return isContainer;
    }

    public void setIsContainer(Boolean isContainer) {
        this.isContainer = isContainer;
    }

    public Entry isLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
        return this;
    }

    /**
     * Returns a boolean indicating if this entry is a leaf object; it cannot have other entries as children.
     * @return isLeaf
     */
    @Schema(description = "A boolean indicating if this entry is a leaf object; it cannot have other entries as children.")
    @JsonProperty("isLeaf")
    public Boolean isLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Entry templateName(String templateName) {
        this.templateName = templateName;
        return this;
    }

    /**
     * Returns the name of the template assigned to this entry.
     * @return templateName
     */
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
     * Returns the id of the template assigned to this entry.
     * @return templateId
     */
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
     * Returns the names of the fields assigned to the template assigned to this entry.
     * @return templateFieldNames
     */
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
     * Returns the name of the volume that this entry is associated with.
     * @return volumeName
     */
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
     * Returns row number assigned to this entry in the listing.
     * @return rowNumber
     */
    @Schema(description = "Row number assigned to this entry in the listing.")
    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Entry fields(List<Field> fields) {
        this.fields = fields;
        return this;
    }

    public Entry addFieldsItem(Field fieldsItem) {
        if (this.fields == null) {
            this.fields = new ArrayList<Field>();
        }
        this.fields.add(fieldsItem);
        return this;
    }

    /**
     * Returns the fields assigned to this entry.
     * @return fields
     */
    @Schema(description = "The fields assigned to this entry.")
    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
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
        return Objects.equals(this.id, entry.id) && Objects.equals(this.name, entry.name) && Objects.equals(this.parentId, entry.parentId) && Objects.equals(this.fullPath, entry.fullPath) && Objects.equals(this.folderPath, entry.folderPath) && Objects.equals(this.creator, entry.creator) && Objects.equals(this.creationTime, entry.creationTime) && Objects.equals(this.lastModifiedTime, entry.lastModifiedTime) && Objects.equals(this.entryType, entry.entryType) && Objects.equals(this.isContainer, entry.isContainer) && Objects.equals(this.isLeaf, entry.isLeaf) && Objects.equals(this.templateName, entry.templateName) && Objects.equals(this.templateId, entry.templateId) && Objects.equals(this.templateFieldNames, entry.templateFieldNames) && Objects.equals(this.volumeName, entry.volumeName) && Objects.equals(this.rowNumber, entry.rowNumber) && Objects.equals(this.fields, entry.fields);
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

    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
