/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonPrimitive
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 */
package minegame159.meteorclient.utils.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.UUID;

public class UUIDSerializer
implements JsonSerializer<UUID>,
JsonDeserializer<UUID> {
    public JsonElement serialize(UUID llllllllllllllllllIIIlIllIIlIlIl, Type llllllllllllllllllIIIlIllIIlIlII, JsonSerializationContext llllllllllllllllllIIIlIllIIlIIll) {
        return new JsonPrimitive(llllllllllllllllllIIIlIllIIlIlIl.toString());
    }

    public UUIDSerializer() {
        UUIDSerializer llllllllllllllllllIIIlIllIIllIII;
    }

    public UUID deserialize(JsonElement llllllllllllllllllIIIlIllIIIllll, Type llllllllllllllllllIIIlIllIIIlllI, JsonDeserializationContext llllllllllllllllllIIIlIllIIIllIl) throws JsonParseException {
        return UUID.fromString(llllllllllllllllllIIIlIllIIIllll.getAsString());
    }
}

