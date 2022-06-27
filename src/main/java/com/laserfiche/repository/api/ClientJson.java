package com.laserfiche.repository.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.laserfiche.repository.api.client.model.*;
import io.gsonfire.GsonFireBuilder;
import io.gsonfire.TypeSelector;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ClientJson extends JSON {
    private Gson gson;
    private DateTypeAdapter dateTypeAdapter = new DateTypeAdapter();
    private SqlDateTypeAdapter sqlDateTypeAdapter = new SqlDateTypeAdapter();
    private OffsetDateTimeTypeAdapter offsetDateTimeTypeAdapter = new OffsetDateTimeTypeAdapter();
    private LocalDateTypeAdapter localDateTypeAdapter = new LocalDateTypeAdapter();

    public static GsonBuilder createGson() {
        GsonFireBuilder fireBuilder = new GsonFireBuilder()
                .registerTypeSelector(Entry.class, new RepositoryApiReturnTypeSelector())
                .registerTypeSelector(EntryFieldValue.class, new RepositoryApiReturnTypeSelector())
                .registerTypeSelector(ODataValueOfIListOfContextHit.class, new RepositoryApiReturnTypeSelector())
                .registerTypeSelector(ODataValueOfIListOfEntry.class, new RepositoryApiReturnTypeSelector())
                .registerTypeSelector(ODataValueOfIListOfFieldValue.class, new RepositoryApiReturnTypeSelector())
                .registerTypeSelector(ODataValueOfIListOfTemplateFieldInfo.class, new RepositoryApiReturnTypeSelector())
                .registerTypeSelector(ODataValueOfIListOfWEntryLinkInfo.class, new RepositoryApiReturnTypeSelector())
                .registerTypeSelector(ODataValueOfIListOfWFieldInfo.class, new RepositoryApiReturnTypeSelector())
                .registerTypeSelector(ODataValueOfIListOfWTagInfo.class, new RepositoryApiReturnTypeSelector())
                .registerTypeSelector(ODataValueOfIListOfWTemplateInfo.class, new RepositoryApiReturnTypeSelector())
                .registerTypeSelector(ODataValueOfListOfAttribute.class, new RepositoryApiReturnTypeSelector())
                .registerTypeSelector(WFieldInfo.class, new RepositoryApiReturnTypeSelector());
        return fireBuilder.createGsonBuilder();
    }

    static class RepositoryApiReturnTypeSelector implements TypeSelector {
        private static Map<String, Class> typeSelectableClasses = null;

        public RepositoryApiReturnTypeSelector() {
            if (typeSelectableClasses != null) {
                return;
            }
            typeSelectableClasses = new HashMap<>();
            typeSelectableClasses.put("WFieldInfo", WFieldInfo.class);
            typeSelectableClasses.put("TemplateFieldInfo", TemplateFieldInfo.class);
            typeSelectableClasses.put("ODataValueOfListOfAttribute", ODataValueOfListOfAttribute.class);
            typeSelectableClasses.put("ODataValueContextOfListOfAttribute", ODataValueContextOfListOfAttribute.class);
            typeSelectableClasses.put("ODataValueOfIListOfWTemplateInfo", ODataValueOfIListOfWTemplateInfo.class);
            typeSelectableClasses.put("ODataValueContextOfIListOfWTemplateInfo", ODataValueContextOfIListOfWTemplateInfo.class);
            typeSelectableClasses.put("ODataValueOfIListOfWTagInfo", ODataValueOfIListOfWTagInfo.class);
            typeSelectableClasses.put("ODataValueContextOfIListOfWTagInfo", ODataValueContextOfIListOfWTagInfo.class);
            typeSelectableClasses.put("ODataValueOfIListOfWFieldInfo", ODataValueOfIListOfWFieldInfo.class);
            typeSelectableClasses.put("ODataValueContextOfIListOfWFieldInfo", ODataValueContextOfIListOfWFieldInfo.class);
            typeSelectableClasses.put("ODataValueOfIListOfWEntryLinkInfo", ODataValueOfIListOfWEntryLinkInfo.class);
            typeSelectableClasses.put("ODataValueContextOfIListOfWEntryLinkInfo", ODataValueContextOfIListOfWEntryLinkInfo.class);
            typeSelectableClasses.put("ODataValueOfIListOfTemplateFieldInfo", ODataValueOfIListOfTemplateFieldInfo.class);
            typeSelectableClasses.put("ODataValueContextOfIListOfTemplateFieldInfo", ODataValueContextOfIListOfTemplateFieldInfo.class);
            typeSelectableClasses.put("ODataValueOfIListOfFieldValue", ODataValueOfIListOfFieldValue.class);
            typeSelectableClasses.put("ODataValueContextOfIListOfFieldValue", ODataValueContextOfIListOfFieldValue.class);
            typeSelectableClasses.put("ODataValueOfIListOfEntry", ODataValueOfIListOfEntry.class);
            typeSelectableClasses.put("ODataValueContextOfIListOfEntry", ODataValueContextOfIListOfEntry.class);
            typeSelectableClasses.put("ODataValueOfIListOfContextHit", ODataValueOfIListOfContextHit.class);
            typeSelectableClasses.put("ODataValueContextOfIListOfContextHit", ODataValueContextOfIListOfContextHit.class);
            typeSelectableClasses.put("EntryFieldValue", EntryFieldValue.class);
            typeSelectableClasses.put("FieldValue", FieldValue.class);
            typeSelectableClasses.put("Entry", Entry.class);
            typeSelectableClasses.put("Shortcut", Shortcut.class);
            typeSelectableClasses.put("Folder", Folder.class);
            typeSelectableClasses.put("Document", Document.class);
        }

        @Override
        public Class getClassForElement(JsonElement jsonElement) {
            String odataType = "@odata.type";
            JsonElement element = jsonElement.getAsJsonObject().get(odataType);
            if (element == null) {
                throw new IllegalArgumentException(String.format("The JSON object doesn't contain required field: %s", odataType));
            }
            String type = element.getAsString();
            Class classToDeserialize = typeSelectableClasses.get(type);
            if (classToDeserialize == null) {
                throw new RuntimeException(String.format("Cannot find class: %s", type));
            }
            return classToDeserialize;
        }
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
