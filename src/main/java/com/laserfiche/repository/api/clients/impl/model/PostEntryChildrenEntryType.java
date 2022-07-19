package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Gets or Sets PostEntryChildrenEntryType
 */
@JsonAdapter(PostEntryChildrenEntryType.Adapter.class)
public enum PostEntryChildrenEntryType {
  FOLDER("Folder"),
  SHORTCUT("Shortcut");

  private String value;

  PostEntryChildrenEntryType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static PostEntryChildrenEntryType fromValue(String input) {
    for (PostEntryChildrenEntryType b : PostEntryChildrenEntryType.values()) {
      if (b.value.equals(input)) {
        return b;
      }
    }
    return null;
  }

  public static class Adapter extends TypeAdapter<PostEntryChildrenEntryType> {
    @Override
    public void write(final JsonWriter jsonWriter, final PostEntryChildrenEntryType enumeration) throws IOException {
      jsonWriter.value(String.valueOf(enumeration.getValue()));
    }

    @Override
    public PostEntryChildrenEntryType read(final JsonReader jsonReader) throws IOException {
      Object value = jsonReader.nextString();
      return PostEntryChildrenEntryType.fromValue((String)(value));
    }
  }
}
