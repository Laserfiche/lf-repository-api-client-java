package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Enumeration of Laserfiche template field formats.
 */
@JsonAdapter(WFieldFormat.Adapter.class)
public enum WFieldFormat {
  NONE("None"),
  SHORTDATE("ShortDate"),
  LONGDATE("LongDate"),
  SHORTDATETIME("ShortDateTime"),
  LONGDATETIME("LongDateTime"),
  SHORTTIME("ShortTime"),
  LONGTIME("LongTime"),
  GENERALNUMBER("GeneralNumber"),
  CURRENCY("Currency"),
  PERCENT("Percent"),
  SCIENTIFIC("Scientific"),
  CUSTOM("Custom");

  private String value;

  WFieldFormat(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static WFieldFormat fromValue(String input) {
    for (WFieldFormat b : WFieldFormat.values()) {
      if (b.value.equals(input)) {
        return b;
      }
    }
    return null;
  }

  public static class Adapter extends TypeAdapter<WFieldFormat> {
    @Override
    public void write(final JsonWriter jsonWriter, final WFieldFormat enumeration) throws IOException {
      jsonWriter.value(String.valueOf(enumeration.getValue()));
    }

    @Override
    public WFieldFormat read(final JsonReader jsonReader) throws IOException {
      Object value = jsonReader.nextString();
      return WFieldFormat.fromValue((String)(value));
    }
  }
}
