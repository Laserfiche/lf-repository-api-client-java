package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Gets or Sets WatermarkPosition
 */
@JsonAdapter(WatermarkPosition.Adapter.class)
public enum WatermarkPosition {
  TOPLEFT("TopLeft"),
  TOPCENTER("TopCenter"),
  TOPRIGHT("TopRight"),
  MIDDLELEFT("MiddleLeft"),
  DEADCENTER("DeadCenter"),
  MIDDLERIGHT("MiddleRight"),
  BOTTOMLEFT("BottomLeft"),
  BOTTOMCENTER("BottomCenter"),
  BOTTOMRIGHT("BottomRight");

  private String value;

  WatermarkPosition(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static WatermarkPosition fromValue(String input) {
    for (WatermarkPosition b : WatermarkPosition.values()) {
      if (b.value.equals(input)) {
        return b;
      }
    }
    return null;
  }

  public static class Adapter extends TypeAdapter<WatermarkPosition> {
    @Override
    public void write(final JsonWriter jsonWriter, final WatermarkPosition enumeration) throws IOException {
      jsonWriter.value(String.valueOf(enumeration.getValue()));
    }

    @Override
    public WatermarkPosition read(final JsonReader jsonReader) throws IOException {
      Object value = jsonReader.nextString();
      return WatermarkPosition.fromValue((String)(value));
    }
  }
}
