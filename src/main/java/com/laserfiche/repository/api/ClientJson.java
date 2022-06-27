package com.laserfiche.repository.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.laserfiche.repository.api.client.model.*;
import io.gsonfire.GsonFireBuilder;
import io.gsonfire.TypeSelector;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Date;

public class ClientJson extends JSON {
    private Gson gson;
    private DateTypeAdapter dateTypeAdapter = new DateTypeAdapter();
    private SqlDateTypeAdapter sqlDateTypeAdapter = new SqlDateTypeAdapter();
    private OffsetDateTimeTypeAdapter offsetDateTimeTypeAdapter = new OffsetDateTimeTypeAdapter();
    private LocalDateTypeAdapter localDateTypeAdapter = new LocalDateTypeAdapter();

    public static GsonBuilder createGson() {
        GsonFireBuilder fireBuilder = new GsonFireBuilder()
                .registerTypeSelector(Entry.class, new TypeSelector() {
                    @Override
                    public Class getClassForElement(JsonElement readElement) {
                        throw new NotImplementedException();
                    }
                })
                .registerTypeSelector(EntryFieldValue.class, new TypeSelector() {
                    @Override
                    public Class getClassForElement(JsonElement readElement) {
                        throw new NotImplementedException();
                    }
                })
                .registerTypeSelector(ODataValueOfIListOfContextHit.class, new TypeSelector() {
                    @Override
                    public Class getClassForElement(JsonElement readElement) {
                        throw new NotImplementedException();
                    }
                })
                .registerTypeSelector(ODataValueOfIListOfEntry.class, new TypeSelector() {
                    @Override
                    public Class getClassForElement(JsonElement readElement) {
                        throw new NotImplementedException();
                    }
                })
                .registerTypeSelector(ODataValueOfIListOfFieldValue.class, new TypeSelector() {
                    @Override
                    public Class getClassForElement(JsonElement readElement) {
                        throw new NotImplementedException();
                    }
                })
                .registerTypeSelector(ODataValueOfIListOfTemplateFieldInfo.class, new TypeSelector() {
                    @Override
                    public Class getClassForElement(JsonElement readElement) {
                        throw new NotImplementedException();
                    }
                })
                .registerTypeSelector(ODataValueOfIListOfWEntryLinkInfo.class, new TypeSelector() {
                    @Override
                    public Class getClassForElement(JsonElement readElement) {
                        throw new NotImplementedException();
                    }
                })
                .registerTypeSelector(ODataValueOfIListOfWFieldInfo.class, new TypeSelector() {
                    @Override
                    public Class getClassForElement(JsonElement readElement) {
                        throw new NotImplementedException();
                    }
                })
                .registerTypeSelector(ODataValueOfIListOfWTagInfo.class, new TypeSelector() {
                    @Override
                    public Class getClassForElement(JsonElement readElement) {
                        throw new NotImplementedException();
                    }
                })
                .registerTypeSelector(ODataValueOfIListOfWTemplateInfo.class, new TypeSelector() {
                    @Override
                    public Class getClassForElement(JsonElement readElement) {
                        throw new NotImplementedException();
                    }
                })
                .registerTypeSelector(ODataValueOfListOfAttribute.class, new TypeSelector() {
                    @Override
                    public Class getClassForElement(JsonElement readElement) {
                        throw new NotImplementedException();
                    }
                })
                .registerTypeSelector(WFieldInfo.class, new TypeSelector() {
                    @Override
                    public Class getClassForElement(JsonElement readElement) {
                        throw new NotImplementedException();
                    }
                });
        return fireBuilder.createGsonBuilder();
    }

    public ClientJson() {
        gson = createGson()
                .registerTypeAdapter(Date.class, dateTypeAdapter)
                .registerTypeAdapter(java.sql.Date.class, sqlDateTypeAdapter)
                .registerTypeAdapter(OffsetDateTime.class, offsetDateTimeTypeAdapter)
                .registerTypeAdapter(LocalDate.class, localDateTypeAdapter)
                .create();
    }
}
