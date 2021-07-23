/*
 * Decompiled with CFR 0.151.
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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class UUIDSerializer
implements JsonSerializer<UUID>,
JsonDeserializer<UUID> {
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return this.deserialize(jsonElement, type, jsonDeserializationContext);
    }

    public JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
        return this.serialize((UUID)object, type, jsonSerializationContext);
    }

    public JsonElement serialize(UUID uUID, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(uUID.toString());
    }

    public UUID deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return UUID.fromString(jsonElement.getAsString());
    }
}

