package org.graphflow.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraph;
import org.graphflow.models.AbstractActivity;

import java.util.Map;

@Slf4j
public final class ActivityGraphUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ActivityGraphUtil() {
    }

    public static Map<String, Object> getVertexBody(AbstractActivity activity) {
            return objectMapper.convertValue(activity, Map.class);
    }

    public static String getVertexLabel(OrientGraph graph, Class<?> clazz) {
        return graph.classNameToLabel(clazz.getSimpleName());
    }

    public static String getVertexLabel(OrientGraph graph, AbstractActivity object) {
        return getVertexLabel(graph, object.getClass());
    }
}
