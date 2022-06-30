package com.laserfiche.repository.api.serialization;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

import java.io.IOException;

/**
 * Gson TypeAdapter for JSR310 LocalDate type
 */
public class LocalDateTypeAdapter extends TypeAdapter<LocalDate> {

    private DateTimeFormatter formatter;

    public LocalDateTypeAdapter() {
        this(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public LocalDateTypeAdapter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public void setFormat(DateTimeFormatter dateFormat) {
        this.formatter = dateFormat;
    }

    @Override
    public void write(JsonWriter out, LocalDate date) throws IOException {
        if (date == null) {
            out.nullValue();
        } else {
            out.value(formatter.format(date));
        }
    }

    @Override
    public LocalDate read(JsonReader in) throws IOException {
        switch (in.peek()) {
            case NULL:
                in.nextNull();
                return null;
            default:
                String date = in.nextString();
                return LocalDate.parse(date, formatter);
        }
    }
}