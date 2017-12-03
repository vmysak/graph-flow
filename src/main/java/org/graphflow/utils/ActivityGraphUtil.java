package org.graphflow.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraph;
import org.apache.tinkerpop.gremlin.orientdb.OrientVertex;
import org.graphflow.models.AbstractActivity;

@Slf4j
public final class ActivityGraphUtil {

    private ActivityGraphUtil() {
    }

    public static String getVertexClass(OrientVertex vertex){
        return vertex.getGraph().labelToClassName(vertex.label(), null);
    }

    public static String getVertexLabel(OrientGraph graph, String classSimpleName) {
        return graph.classNameToLabel(classSimpleName);
    }

    public static String getVertexLabel(OrientGraph graph, Class<?> clazz) {
        return getVertexLabel(graph, clazz.getSimpleName());
    }

    public static String getVertexLabel(OrientGraph graph, AbstractActivity object) {
        return getVertexLabel(graph, object.getClass());
    }
}
