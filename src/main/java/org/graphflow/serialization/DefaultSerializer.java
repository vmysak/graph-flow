package org.graphflow.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.graphflow.serialization.strategies.DeserializationStrategy;
import org.graphflow.serialization.strategies.SerializationStrategy;

import java.lang.reflect.Type;

public final class DefaultSerializer {

    private static Gson gson;

    static {
        gson = new GsonBuilder()
                .addSerializationExclusionStrategy(new SerializationStrategy())
                .addDeserializationExclusionStrategy(new DeserializationStrategy())
                .create();
    }

    private DefaultSerializer() {
    }

    public static String serialize(Object obj) {
        return gson.toJson(obj);
    }

    public static JsonElement toJsonTree(Object obj) {
        return gson.toJsonTree(obj);
    }

    public static <T> T fromJsonTree(JsonElement jsonElement, Type type) {
        return gson.fromJson(jsonElement, type);
    }

}