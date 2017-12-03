package org.graphflow.serialization;

import org.graphflow.annotations.VertexEntity;
import org.graphflow.startup.AnnotationLoaderService;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class VertexClassMapping implements Serializable {

    private final Map<String, Class<?>> classMapping = new LinkedHashMap<>();

    private VertexClassMapping() {
    }

    public static VertexClassMapping from(AnnotationLoaderService service) {
        VertexClassMapping mapping = new VertexClassMapping();
        Map<String, Class<?>> annotated = service.loadClasses(VertexEntity.class);
        annotated.forEach(mapping.classMapping::put);
        return mapping;
    }

    public Class<?> getMappedClass(String simpleName) {
        return classMapping.get(simpleName);
    }


}
