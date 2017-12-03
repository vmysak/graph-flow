package org.graphflow.serialization;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import lombok.AllArgsConstructor;
import org.graphflow.models.AbstractActivity;

import java.lang.reflect.Type;

import static org.graphflow.models.AbstractActivity.ACTIVITY_TYPE;
import static org.graphflow.serialization.DefaultSerializer.toJsonTree;

@AllArgsConstructor
public class ActivitySerializer<T extends AbstractActivity> implements JsonSerializer<T>, JsonDeserializer<T> {

    private final VertexClassMapping mapping;

    @Override
    public JsonElement serialize(T activity, Type t, JsonSerializationContext ctx) {
        return toJsonTree(activity).getAsJsonObject();
    }

    @Override
    public T deserialize(JsonElement element, Type t, JsonDeserializationContext ctx) throws JsonParseException {
        JsonObject jsonObject = element.getAsJsonObject();

        String activityType = jsonObject.get(ACTIVITY_TYPE).getAsString();
        Class<?> activityClass = mapping.getMappedClass(activityType);

        return DefaultSerializer.fromJsonTree(jsonObject, activityClass);
    }
}
