package com.laserfiche.repository.api.clients.impl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/** Gets or Sets EntryType */
public enum EntryType {
  FOLDER("Folder"),
  RECORD_SERIES("RecordSeries"),
  DOCUMENT("Document"),
  SHORTCUT("Shortcut");

  private String value;

  EntryType(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static EntryType fromValue(String input) {
    for (EntryType b : EntryType.values()) {
      if (b.value.equals(input)) {
        return b;
      }
    }
    return null;
  }
}
