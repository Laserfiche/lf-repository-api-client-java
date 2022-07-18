package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Gets or Sets EntryType
 */
@JsonAdapter(EntryType.Adapter.class)
public enum EntryType {
  FOLDER("Folder"),
  RECORDSERIES("RecordSeries"),
  DOCUMENT("Document"),
  SHORTCUT("Shortcut");

  private String value;

  EntryType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static EntryType fromValue(String input) {
    for (EntryType b : EntryType.values()) {
      if (b.value.equals(input)) {
        return b;
      }
    }
    return null;
  }

  public static class Adapter extends TypeAdapter<EntryType> {
    @Override
    public void write(final JsonWriter jsonWriter, final EntryType enumeration) throws IOException {
      jsonWriter.value(String.valueOf(enumeration.getValue()));
    }

    @Override
    public EntryType read(final JsonReader jsonReader) throws IOException {
      Object value = jsonReader.nextString();
      return EntryType.fromValue((String)(value));
    }
  }
}
