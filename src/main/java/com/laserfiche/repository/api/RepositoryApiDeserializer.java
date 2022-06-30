package com.laserfiche.repository.api;

import com.google.gson.Gson;
import com.laserfiche.repository.api.client.model.*;
import com.laserfiche.repository.api.serialization.*;
import io.gsonfire.GsonFireBuilder;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;
import java.util.Date;

public class RepositoryApiDeserializer {
    Gson gson;

    public RepositoryApiDeserializer() {
        GsonFireBuilder fireBuilder = new GsonFireBuilder()
                .registerTypeSelector(Entry.class, new RepositoryApiReturnTypeSelector<>(Entry.class))
                .registerTypeSelector(EntryFieldValue.class, new RepositoryApiReturnTypeSelector<>(EntryFieldValue.class));
        gson = fireBuilder.createGsonBuilder()
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .registerTypeAdapter(java.sql.Date.class, new SqlDateTypeAdapter())
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeTypeAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
    }

    public Gson getGson() {
        return this.gson;
    }
}
