package org.graphflow.serialization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;

import java.lang.reflect.Type;
import java.util.Map;

@AllArgsConstructor
public class OrientVertexPropertiesSerializer implements JsonDeserializer<Map<String, Object>> {

    public static final Type TYPE = new TypeToken<Map<String, Object>>() {
    }.getType();

    @Override
    public Map<String, Object> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return DefaultSerializer.fromJsonTree(jsonElement, type);
    }

}
