package org.graphflow.utils;

import org.apache.tinkerpop.gremlin.orientdb.OrientGraph;

public final class OrientUtil {

    private OrientUtil() {
    }

    public static String getVertexLabel(OrientGraph graph, Class<?> clazz) {
        return graph.classNameToLabel(clazz.getSimpleName());
    }

    public static String getVertexLabel(OrientGraph graph, Object object) {
        return getVertexLabel(graph, object.getClass());
    }
}
