// Copyright (c) Laserfiche.
// Licensed under the MIT License. See LICENSE in the project root for license information.
package com.laserfiche.repository.api.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import com.fasterxml.jackson.databind.jsontype.impl.TypeDeserializerBase;
import com.laserfiche.api.client.deserialization.TokenClientObjectMapper;
import com.laserfiche.repository.api.clients.impl.model.Document;
import com.laserfiche.repository.api.clients.impl.model.Entry;
import com.laserfiche.repository.api.clients.impl.model.EntryType;
import com.laserfiche.repository.api.clients.impl.model.Folder;
import com.laserfiche.repository.api.clients.impl.model.RecordSeries;
import com.laserfiche.repository.api.clients.impl.model.Shortcut;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class EntryDeserializationTest {
    @Test
    void readEntry_RepeatedUnknownTypeUsesFallback() throws ReflectiveOperationException {
        TokenClientObjectMapper mapper = new TokenClientObjectMapper();
        for (int i = 0; i < 10000; i++) {
            assertFallback(mapper, "unknown");
        }
        assertEquals(1, typeIdCache(mapper).size());
    }

    @Test
    void readEntry_DistinctUnknownTypesHaveBoundedCache() throws ReflectiveOperationException {
        TokenClientObjectMapper mapper = new TokenClientObjectMapper();
        for (int i = 0; i < 10000; i++) {
            assertFallback(mapper, "unknown-" + i);
        }
        Map<?, ?> cache = typeIdCache(mapper);
        assertTrue(cache.size() > 0);
        assertTrue(cache.size() <= 1000, "Unknown type IDs must not grow the cache beyond Jackson's bound");
        Entry folder = mapper.readValue("{\"entryType\":\"Folder\",\"id\":42}", Entry.class);
        assertEquals(Folder.class, folder.getClass());
        assertEquals(EntryType.FOLDER, folder.getEntryType());
    }

    @Test
    void readEntry_OverlongUnknownTypesAreNotCached() throws ReflectiveOperationException {
        TokenClientObjectMapper mapper = new TokenClientObjectMapper();
        char[] characters = new char[257];
        Arrays.fill(characters, 'x');
        String prefix = new String(characters);
        for (int i = 0; i < 100; i++) {
            assertFallback(mapper, prefix + i);
        }
        assertEquals(0, typeIdCache(mapper).size());
    }

    @Test
    void readEntry_KnownTypesStillSelectTheirSubtypes() {
        TokenClientObjectMapper mapper = new TokenClientObjectMapper();
        assertKnownType(mapper, EntryType.DOCUMENT, Document.class);
        assertKnownType(mapper, EntryType.FOLDER, Folder.class);
        assertKnownType(mapper, EntryType.SHORTCUT, Shortcut.class);
        assertKnownType(mapper, EntryType.RECORD_SERIES, RecordSeries.class);
    }

    private static void assertFallback(TokenClientObjectMapper mapper, String typeId) {
        Entry entry = mapper.readValue("{\"entryType\":\"" + typeId + "\",\"id\":42}", Entry.class);
        assertEquals(Entry.class, entry.getClass());
        assertNull(entry.getEntryType());
        assertEquals(Integer.valueOf(42), entry.getId());
    }

    private static void assertKnownType(
            TokenClientObjectMapper mapper, EntryType entryType, Class<? extends Entry> subtype) {
        Entry entry = mapper.readValue("{\"entryType\":\"" + entryType.getValue() + "\",\"id\":42}", Entry.class);
        assertEquals(subtype, entry.getClass());
        assertEquals(entryType, entry.getEntryType());
        assertEquals(Integer.valueOf(42), entry.getId());
    }

    private static Map<?, ?> typeIdCache(TokenClientObjectMapper mapper) throws ReflectiveOperationException {
        // Observe retained keys on the actual mapper; successful fallback reads alone cannot detect this regression.
        Field mapperField = TokenClientObjectMapper.class.getDeclaredField("jacksonMapper");
        mapperField.setAccessible(true);
        ObjectMapper jackson = ObjectMapper.class.cast(mapperField.get(mapper));
        Field rootsField = ObjectMapper.class.getDeclaredField("_rootDeserializers");
        rootsField.setAccessible(true);
        Map<?, ?> roots = Map.class.cast(rootsField.get(jackson));
        TypeWrappedDeserializer root =
                TypeWrappedDeserializer.class.cast(roots.get(jackson.constructType(Entry.class)));
        Field typeField = TypeWrappedDeserializer.class.getDeclaredField("_typeDeserializer");
        typeField.setAccessible(true);
        Field cacheField = TypeDeserializerBase.class.getDeclaredField("_deserializers");
        cacheField.setAccessible(true);
        return Map.class.cast(cacheField.get(typeField.get(root)));
    }
}
