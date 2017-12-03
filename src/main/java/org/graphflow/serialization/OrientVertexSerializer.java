package org.graphflow.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import lombok.AllArgsConstructor;
import org.apache.tinkerpop.gremlin.orientdb.OrientProperty;
import org.apache.tinkerpop.gremlin.orientdb.OrientVertex;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.graphflow.models.AbstractActivity.ACTIVITY_TYPE;
import static org.graphflow.serialization.DefaultSerializer.toJsonTree;
import static org.graphflow.utils.ActivityGraphUtil.getVertexClass;

@AllArgsConstructor
public class OrientVertexSerializer implements JsonSerializer<OrientVertex> {

    @Override
    public JsonElement serialize(OrientVertex vertex, Type t, JsonSerializationContext ctx) {
        Map<String, Object> properties = convertProperties(vertex);
        return toJsonTree(properties);
    }

    private Map<String, Object> convertProperties(OrientVertex vertex) {
        Map<String, Object> map = new LinkedHashMap<>();
        Iterator<VertexProperty<Object>> properties = vertex.properties();
        while (properties.hasNext()) {
            OrientProperty property = (OrientProperty) properties.next();
            map.put(property.key(), property.value());
        }
        map.put(ACTIVITY_TYPE, getVertexClass(vertex));
        return map;
    }
}
