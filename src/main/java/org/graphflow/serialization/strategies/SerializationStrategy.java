package org.graphflow.serialization.strategies;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import org.graphflow.annotations.GraphProperty;

public class SerializationStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        final GraphProperty graph = fieldAttributes.getAnnotation(GraphProperty.class);
        return graph != null && !graph.serialize();
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }

}
