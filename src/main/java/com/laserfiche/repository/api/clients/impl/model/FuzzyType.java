package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Gets or Sets FuzzyType
 */
@JsonAdapter(FuzzyType.Adapter.class)
public enum FuzzyType {
  NUMBER_0(0),
  NUMBER_1(1),
  NUMBER_2(2);

  private Integer value;

  FuzzyType(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static FuzzyType fromValue(Integer input) {
    for (FuzzyType b : FuzzyType.values()) {
      if (b.value.equals(input)) {
        return b;
      }
    }
    return null;
  }

  public static class Adapter extends TypeAdapter<FuzzyType> {
    @Override
    public void write(final JsonWriter jsonWriter, final FuzzyType enumeration) throws IOException {
      jsonWriter.value(String.valueOf(enumeration.getValue()));
    }

    @Override
    public FuzzyType read(final JsonReader jsonReader) throws IOException {
      Object value = jsonReader.nextInt();
      return FuzzyType.fromValue((Integer)(value));
    }
  }
}
