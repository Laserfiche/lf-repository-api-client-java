/*
 * Laserfiche Repository API
 * Welcome to the Laserfiche API Swagger Playground. You can try out any of our API calls against your live Laserfiche Cloud account. Visit the developer center for more details: <a href=\"https://developer.laserfiche.com\">https://developer.laserfiche.com</a><p><strong>Build# : </strong>51c16645afa5983c3eb4a849158d6f1e355d2bb0_.20220512.1</p>
 *
 * OpenAPI spec version: 1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package com.laserfiche.api.client.model;

import java.util.Objects;
import java.util.Arrays;
import io.swagger.v3.oas.annotations.media.Schema;
import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

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
