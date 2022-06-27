package com.laserfiche.repository.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.laserfiche.repository.api.client.model.*;
import io.gsonfire.GsonFireBuilder;
import io.gsonfire.TypeSelector;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ClientJson {
    Gson gson;

    public ClientJson() {
        GsonFireBuilder fireBuilder = new GsonFireBuilder()
                .registerTypeSelector(Entry.class, new RepositoryApiReturnTypeSelector<>(Entry.class))
                .registerTypeSelector(EntryFieldValue.class, new RepositoryApiReturnTypeSelector<>(EntryFieldValue.class))
                .registerTypeSelector(ODataValueOfIListOfContextHit.class, new RepositoryApiReturnTypeSelector<>(ODataValueOfIListOfContextHit.class))
                .registerTypeSelector(ODataValueOfIListOfEntry.class, new RepositoryApiReturnTypeSelector<>(ODataValueOfIListOfEntry.class))
                .registerTypeSelector(ODataValueOfIListOfFieldValue.class, new RepositoryApiReturnTypeSelector<>(ODataValueOfIListOfFieldValue.class))
                .registerTypeSelector(ODataValueOfIListOfTemplateFieldInfo.class, new RepositoryApiReturnTypeSelector<>(ODataValueOfIListOfTemplateFieldInfo.class))
                .registerTypeSelector(ODataValueOfIListOfWEntryLinkInfo.class, new RepositoryApiReturnTypeSelector<>(ODataValueOfIListOfWEntryLinkInfo.class))
                .registerTypeSelector(ODataValueOfIListOfWFieldInfo.class, new RepositoryApiReturnTypeSelector<>(ODataValueOfIListOfWFieldInfo.class))
                .registerTypeSelector(ODataValueOfIListOfWTagInfo.class, new RepositoryApiReturnTypeSelector<>(ODataValueOfIListOfWTagInfo.class))
                .registerTypeSelector(ODataValueOfIListOfWTemplateInfo.class, new RepositoryApiReturnTypeSelector<>(ODataValueOfIListOfWTemplateInfo.class))
                .registerTypeSelector(ODataValueOfListOfAttribute.class, new RepositoryApiReturnTypeSelector<>(ODataValueOfListOfAttribute.class))
                .registerTypeSelector(WFieldInfo.class, new RepositoryApiReturnTypeSelector<>(WFieldInfo.class));
        gson = fireBuilder.createGsonBuilder()
                .registerTypeAdapter(Date.class, new JSON.DateTypeAdapter())
                .registerTypeAdapter(java.sql.Date.class, new JSON.SqlDateTypeAdapter())
                .registerTypeAdapter(OffsetDateTime.class, new JSON.OffsetDateTimeTypeAdapter())
                .registerTypeAdapter(LocalDate.class, new JSON.LocalDateTypeAdapter())
                .create();
    }

    public Gson getGson() {
        return this.gson;
    }

    static class RepositoryApiReturnTypeSelector<T> implements TypeSelector<T> {
        private Map<String, Class> typeSelectableClasses;

        public RepositoryApiReturnTypeSelector(Class<T> type) {
            typeSelectableClasses = new HashMap<>();

            if (type == WFieldInfo.class) {
                typeSelectableClasses.put("WFieldInfo", WFieldInfo.class);
                typeSelectableClasses.put("TemplateFieldInfo", TemplateFieldInfo.class);
            } else if (type == ODataValueOfListOfAttribute.class) {
                typeSelectableClasses.put("ODataValueOfListOfAttribute", ODataValueOfListOfAttribute.class);
                typeSelectableClasses.put("ODataValueContextOfListOfAttribute", ODataValueContextOfListOfAttribute.class);
            } else if (type == ODataValueOfIListOfWTemplateInfo.class) {
                typeSelectableClasses.put("ODataValueOfIListOfWTemplateInfo", ODataValueOfIListOfWTemplateInfo.class);
                typeSelectableClasses.put("ODataValueContextOfIListOfWTemplateInfo", ODataValueContextOfIListOfWTemplateInfo.class);
            } else if (type == ODataValueOfIListOfWTagInfo.class) {
                typeSelectableClasses.put("ODataValueOfIListOfWTagInfo", ODataValueOfIListOfWTagInfo.class);
                typeSelectableClasses.put("ODataValueContextOfIListOfWTagInfo", ODataValueContextOfIListOfWTagInfo.class);
            } else if (type == ODataValueOfIListOfWFieldInfo.class) {
                typeSelectableClasses.put("ODataValueOfIListOfWFieldInfo", ODataValueOfIListOfWFieldInfo.class);
                typeSelectableClasses.put("ODataValueContextOfIListOfWFieldInfo", ODataValueContextOfIListOfWFieldInfo.class);
            } else if (type == ODataValueOfIListOfWEntryLinkInfo.class) {
                typeSelectableClasses.put("ODataValueOfIListOfWEntryLinkInfo", ODataValueOfIListOfWEntryLinkInfo.class);
                typeSelectableClasses.put("ODataValueContextOfIListOfWEntryLinkInfo", ODataValueContextOfIListOfWEntryLinkInfo.class);
            } else if (type == ODataValueOfIListOfTemplateFieldInfo.class) {
                typeSelectableClasses.put("ODataValueOfIListOfTemplateFieldInfo", ODataValueOfIListOfTemplateFieldInfo.class);
                typeSelectableClasses.put("ODataValueContextOfIListOfTemplateFieldInfo", ODataValueContextOfIListOfTemplateFieldInfo.class);
            } else if (type == ODataValueOfIListOfFieldValue.class) {
                typeSelectableClasses.put("ODataValueOfIListOfFieldValue", ODataValueOfIListOfFieldValue.class);
                typeSelectableClasses.put("ODataValueContextOfIListOfFieldValue", ODataValueContextOfIListOfFieldValue.class);
            } else if (type == ODataValueOfIListOfEntry.class) {
                typeSelectableClasses.put("ODataValueOfIListOfEntry", ODataValueOfIListOfEntry.class);
                typeSelectableClasses.put("ODataValueContextOfIListOfEntry", ODataValueContextOfIListOfEntry.class);
            } else if (type == ODataValueOfIListOfContextHit.class) {
                typeSelectableClasses.put("ODataValueOfIListOfContextHit", ODataValueOfIListOfContextHit.class);
                typeSelectableClasses.put("ODataValueContextOfIListOfContextHit", ODataValueContextOfIListOfContextHit.class);
            } else if (type == EntryFieldValue.class) {
                typeSelectableClasses.put("EntryFieldValue", EntryFieldValue.class);
                typeSelectableClasses.put("FieldValue", FieldValue.class);
            } else if (type == Entry.class) {
                typeSelectableClasses.put("Entry", Entry.class);
                typeSelectableClasses.put("Shortcut", Shortcut.class);
                typeSelectableClasses.put("Folder", Folder.class);
                typeSelectableClasses.put("Document", Document.class);
            } else {
                throw new UnsupportedOperationException(String.format("Type %s is not supported.", type.getCanonicalName()));
            }
        }

        @Override
        public Class<T> getClassForElement(JsonElement jsonElement) {
            String odataType = "@odata.type";
            JsonElement element = jsonElement.getAsJsonObject().get(odataType);
            if (element == null) {
                throw new IllegalArgumentException(String.format("The JSON object doesn't contain required field: %s", odataType));
            }

            String typeComponentStr = element.getAsString();
            String[] typeComponents = typeComponentStr.split("\\.");
            String type = typeComponents[typeComponents.length - 1];

            Class<T> classToDeserialize = typeSelectableClasses.get(type);
            if (classToDeserialize == null) {
                throw new RuntimeException(String.format("Cannot find class: %s", type));
            }
            return classToDeserialize;
        }
    }
}
