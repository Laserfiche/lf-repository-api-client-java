package com.laserfiche.repository.api.serialization;

import com.google.gson.JsonElement;
import com.laserfiche.repository.api.client.model.*;
import io.gsonfire.TypeSelector;

import java.util.HashMap;
import java.util.Map;

public class RepositoryApiReturnTypeSelector<T> implements TypeSelector<T> {
    private final Map<String, Class> typeSelectableClasses;

    private final Class<T> type;

    public RepositoryApiReturnTypeSelector(Class<T> type) {
        typeSelectableClasses = new HashMap<>();
        this.type = type;

        if (type == EntryFieldValue.class) {
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
            // If there's no odata type, we default to the base type.
            return this.type;
        }

        String typeComponentStr = element.getAsString();
        String[] typeComponents = typeComponentStr.split("\\.");
        String type = typeComponents[typeComponents.length - 1];
        Class<T> classToDeserializeTo = typeSelectableClasses.get(type);

        if (classToDeserializeTo == null) {
            throw new RuntimeException(String.format("Cannot find class: %s", type));
        }
        return classToDeserializeTo;
    }
}