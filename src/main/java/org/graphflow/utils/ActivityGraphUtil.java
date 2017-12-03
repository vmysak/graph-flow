package org.graphflow.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tinkerpop.gremlin.orientdb.OrientElement;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraph;
import org.graphflow.models.AbstractEntity;

@Slf4j
public final class ActivityGraphUtil {

    private ActivityGraphUtil() {
    }

    public static String getEntityClass(OrientElement element) {
        return element.getGraph().labelToClassName(element.label(), null);
    }

    public static String getEntityLabel(OrientGraph graph, String classSimpleName) {
        return graph.classNameToLabel(classSimpleName);
    }

    public static String getEntityLabel(OrientGraph graph, Class<?> clazz) {
        return getEntityLabel(graph, clazz.getSimpleName());
    }

    public static String getEntityLabel(OrientGraph graph, AbstractEntity entity) {
        return getEntityLabel(graph, entity.getClass());
    }
}
