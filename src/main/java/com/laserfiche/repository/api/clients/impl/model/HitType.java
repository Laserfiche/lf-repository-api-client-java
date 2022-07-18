package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Gets or Sets HitType
 */
@JsonAdapter(HitType.Adapter.class)
public enum HitType {
  NUMBER_0(0),
  NUMBER_1(1),
  NUMBER_2(2),
  NUMBER_3(3),
  NUMBER_4(4),
  NUMBER_5(5),
  NUMBER_6(6),
  NUMBER_7(7),
  NUMBER_8(8),
  NUMBER_9(9),
  NUMBER_10(10),
  NUMBER_11(11),
  NUMBER_12(12),
  NUMBER_13(13),
  NUMBER_14(14),
  NUMBER_15(15);

  private Integer value;

  HitType(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static HitType fromValue(Integer input) {
    for (HitType b : HitType.values()) {
      if (b.value.equals(input)) {
        return b;
      }
    }
    return null;
  }

  public static class Adapter extends TypeAdapter<HitType> {
    @Override
    public void write(final JsonWriter jsonWriter, final HitType enumeration) throws IOException {
      jsonWriter.value(String.valueOf(enumeration.getValue()));
    }

    @Override
    public HitType read(final JsonReader jsonReader) throws IOException {
      Object value = jsonReader.nextInt();
      return HitType.fromValue((Integer)(value));
    }
  }
}
