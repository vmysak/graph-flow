package org.graphflow.serialization.strategies;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import org.graphflow.annotations.GraphProperty;

public class DeserializationStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        final GraphProperty graph = fieldAttributes.getAnnotation(GraphProperty.class);
        return graph != null && !graph.deserialize();
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }

}
