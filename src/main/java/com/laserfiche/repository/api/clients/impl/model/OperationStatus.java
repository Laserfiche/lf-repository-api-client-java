package com.laserfiche.repository.api.clients.impl.model;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Gets or Sets OperationStatus
 */
@JsonAdapter(OperationStatus.Adapter.class)
public enum OperationStatus {
  NOTSTARTED("NotStarted"),
  INPROGRESS("InProgress"),
  COMPLETED("Completed"),
  FAILED("Failed"),
  CANCELLED("Cancelled");

  private String value;

  OperationStatus(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static OperationStatus fromValue(String input) {
    for (OperationStatus b : OperationStatus.values()) {
      if (b.value.equals(input)) {
        return b;
      }
    }
    return null;
  }

  public static class Adapter extends TypeAdapter<OperationStatus> {
    @Override
    public void write(final JsonWriter jsonWriter, final OperationStatus enumeration) throws IOException {
      jsonWriter.value(String.valueOf(enumeration.getValue()));
    }

    @Override
    public OperationStatus read(final JsonReader jsonReader) throws IOException {
      Object value = jsonReader.nextString();
      return OperationStatus.fromValue((String)(value));
    }
  }
}
