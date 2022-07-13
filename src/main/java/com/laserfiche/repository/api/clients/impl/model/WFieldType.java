package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Gets or Sets WFieldType
 */
@JsonAdapter(WFieldType.Adapter.class)
public enum WFieldType {
  DATETIME("DateTime"),
  BLOB("Blob"),
  DATE("Date"),
  SHORTINTEGER("ShortInteger"),
  LONGINTEGER("LongInteger"),
  LIST("List"),
  NUMBER("Number"),
  STRING("String"),
  TIME("Time");

  private String value;

  WFieldType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static WFieldType fromValue(String input) {
    for (WFieldType b : WFieldType.values()) {
      if (b.value.equals(input)) {
        return b;
      }
    }
    return null;
  }

  public static class Adapter extends TypeAdapter<WFieldType> {
    @Override
    public void write(final JsonWriter jsonWriter, final WFieldType enumeration) throws IOException {
      jsonWriter.value(String.valueOf(enumeration.getValue()));
    }

    @Override
    public WFieldType read(final JsonReader jsonReader) throws IOException {
      Object value = jsonReader.nextString();
      return WFieldType.fromValue((String)(value));
    }
  }
}
