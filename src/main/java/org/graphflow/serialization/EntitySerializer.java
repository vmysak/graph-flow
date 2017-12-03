package org.graphflow.serialization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import lombok.AllArgsConstructor;
import org.graphflow.models.AbstractEntity;

import java.lang.reflect.Type;

import static org.graphflow.serialization.DefaultSerializer.toJsonTree;

@AllArgsConstructor
public class EntitySerializer<T extends AbstractEntity> implements JsonSerializer<T>, JsonDeserializer<T> {

    private final EntityClassMapping mapping;

    @Override
    public JsonElement serialize(T activity, Type t, JsonSerializationContext ctx) {
        return toJsonTree(activity).getAsJsonObject();
    }

    @Override
    public T deserialize(JsonElement element, Type t, JsonDeserializationContext ctx) throws JsonParseException {
        JsonObject jsonObject = element.getAsJsonObject();

        String entityType = jsonObject.get(AbstractEntity.TYPE).getAsString();
        Class<?> activityClass = mapping.getMappedClass(entityType);

        return DefaultSerializer.fromJsonTree(jsonObject, activityClass);
    }
}
