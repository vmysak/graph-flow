package org.graphflow.serialization.strategies;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import org.graphflow.annotations.VertexProperty;

public class SerializationStrategy implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        final VertexProperty vertexProperty = fieldAttributes.getAnnotation(VertexProperty.class);
        return vertexProperty != null && !vertexProperty.serialize();
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }

}
