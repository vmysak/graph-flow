package org.graphflow.utils;

public final class OrientUtil {

    private final static String VERTEX_CLASS = "class:";

    private OrientUtil() {
    }

    public static String getVertexName(Class<?> clazz) {
        return clazz.getSimpleName();
    }

    public static String getVertexName(Object object) {
        return VERTEX_CLASS + getVertexName(object.getClass());
    }
}
