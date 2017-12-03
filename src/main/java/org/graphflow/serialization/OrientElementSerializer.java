package org.graphflow.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.apache.tinkerpop.gremlin.orientdb.OrientElement;
import org.apache.tinkerpop.gremlin.orientdb.OrientProperty;
import org.apache.tinkerpop.gremlin.structure.Property;
import org.graphflow.models.AbstractEntity;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.graphflow.serialization.DefaultSerializer.toJsonTree;
import static org.graphflow.utils.ActivityGraphUtil.getEntityClass;

public class OrientElementSerializer implements JsonSerializer<OrientElement> {

    @Override
    public JsonElement serialize(OrientElement vertex, Type t, JsonSerializationContext ctx) {
        Map<String, Object> properties = convertProperties(vertex);
        return toJsonTree(properties);
    }

    private Map<String, Object> convertProperties(OrientElement element) {
        Map<String, Object> map = new LinkedHashMap<>();
        Iterator<? extends Property<Object>> properties = element.properties();
        while (properties.hasNext()) {
            OrientProperty property = (OrientProperty) properties.next();
            map.put(property.key(), property.value());
        }
        map.put(AbstractEntity.TYPE, getEntityClass(element));
        return map;
    }
}
