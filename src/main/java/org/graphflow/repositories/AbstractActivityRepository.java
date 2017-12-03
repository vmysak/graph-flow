package org.graphflow.repositories;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraph;
import org.apache.tinkerpop.gremlin.orientdb.OrientVertex;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.graphflow.models.AbstractActivity;
import org.graphflow.serialization.OrientVertexPropertiesSerializer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isNoneEmpty;
import static org.graphflow.commons.Constants.ACTIVITY_SERIALIZER;
import static org.graphflow.utils.ActivityGraphUtil.getVertexLabel;

public abstract class AbstractActivityRepository<V extends AbstractActivity> implements OrientRepository<V> {

    private ApplicationContext applicationContext;

    public final Gson serializer() {
        return (Gson) applicationContext.getBean(ACTIVITY_SERIALIZER);
    }

    private AbstractActivity convertToActivity(OrientVertex vertex) {
        JsonElement serialized = serializer().toJsonTree(vertex);
        return serializer().fromJson(serialized, AbstractActivity.class);
    }

    private Map<String, Object> convertToVertexProperties(AbstractActivity activity) {
        JsonElement serialized = serializer().toJsonTree(activity);
        return serializer().fromJson(serialized, OrientVertexPropertiesSerializer.TYPE);
    }

    @Override
    public final OrientGraph getGraph() {
        return (OrientGraph) applicationContext.getBean(graphName());
    }

    @Override
    public final OrientVertex createVertex(V v) {
        String vertexLabel = getVertexLabel(getGraph(), v);
        OrientVertex vertex = (OrientVertex) getGraph().addVertex(vertexLabel);
        convertToVertexProperties(v).entrySet().stream()
                .filter(e -> isNoneEmpty(e.getKey()) && e.getValue() != null)
                .forEach(e -> vertex.property(e.getKey(), e.getValue()));
        return vertex;
    }

    @Override
    public final Optional<OrientVertex> getVertex(String id) {
        Iterator<Vertex> vertices = getGraph().vertices(id);
        if (vertices.hasNext()) {
            OrientVertex vertex = (OrientVertex) vertices.next();
            return Optional.ofNullable(vertex);
        }
        return Optional.empty();
    }

    public final Optional<AbstractActivity> get(String id) {
        OrientVertex vertex = getVertex(id).orElse(null);
        if (vertex != null) {
            return Optional.ofNullable(convertToActivity(vertex));
        }
        return Optional.empty();
    }

    @Override
    public final void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
