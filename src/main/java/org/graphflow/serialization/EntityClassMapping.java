package org.graphflow.serialization;

import org.graphflow.annotations.EdgeEntity;
import org.graphflow.annotations.VertexEntity;
import org.graphflow.startup.AnnotationLoaderService;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class EntityClassMapping implements Serializable {

    private final Map<String, Class<?>> classMapping = new LinkedHashMap<>();

    private EntityClassMapping() {
    }

    public static EntityClassMapping from(AnnotationLoaderService service) {
        EntityClassMapping mapping = new EntityClassMapping();
        Map<String, Class<?>> vertexes = service.loadClasses(VertexEntity.class);
        Map<String, Class<?>> edges = service.loadClasses(EdgeEntity.class);
        vertexes.forEach(mapping.classMapping::put);
        edges.forEach(mapping.classMapping::put);
        return mapping;
    }

    public Class<?> getMappedClass(String simpleName) {
        return classMapping.get(simpleName);
    }


}
