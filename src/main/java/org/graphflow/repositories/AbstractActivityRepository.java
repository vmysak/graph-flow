package org.graphflow.repositories;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.tinkerpop.gremlin.orientdb.OrientElement;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraph;
import org.apache.tinkerpop.gremlin.orientdb.OrientVertex;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Element;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.graphflow.models.AbstractActivity;
import org.graphflow.models.AbstractEntity;
import org.graphflow.models.AbstractFlow;
import org.graphflow.serialization.OrientPropertiesSerializer;
import org.graphflow.utils.ActivityGraphUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isNoneEmpty;
import static org.graphflow.commons.Constants.ACTIVITY_SERIALIZER;
import static org.graphflow.utils.ActivityGraphUtil.getEntityLabel;

public abstract class AbstractActivityRepository<A extends AbstractActivity, F extends AbstractFlow> implements OrientRepository<A, F> {

    private ApplicationContext applicationContext;

    public final Gson serializer() {
        return (Gson) applicationContext.getBean(ACTIVITY_SERIALIZER);
    }

    private AbstractEntity convertToEntity(OrientElement element) {
        JsonElement serialized = serializer().toJsonTree(element);
        return serializer().fromJson(serialized, AbstractEntity.class);
    }

    private Map<String, Object> convertToProperties(AbstractEntity entity) {
        JsonElement serialized = serializer().toJsonTree(entity);
        return serializer().fromJson(serialized, OrientPropertiesSerializer.TYPE);
    }

    @Override
    public final OrientGraph getGraph() {
        return (OrientGraph) applicationContext.getBean(graphName());
    }

    @Override
    public final Vertex createVertex(A a) {
        String vertexLabel = getEntityLabel(getGraph(), a);
        Vertex vertex = getGraph().addVertex(vertexLabel);
        populateProperties(a, vertex);
        return vertex;
    }

    @Override
    public final Edge createEdge(Vertex from, Vertex to, F f) {
        String edgeLabel = ActivityGraphUtil.getEntityLabel(getGraph(), f);
        Edge edge = from.addEdge(edgeLabel, to);
        populateProperties(f, edge);
        return edge;
    }

    private void populateProperties(AbstractEntity entity, Element element) {
        convertToProperties(entity).entrySet().stream()
                .filter(e -> isNoneEmpty(e.getKey()) && e.getValue() != null)
                .forEach(e -> element.property(e.getKey(), e.getValue()));
    }

    @Override
    public final Optional<Vertex> getVertex(String id) {
        Iterator<Vertex> vertices = getGraph().vertices(id);
        if (vertices.hasNext()) {
            Vertex vertex = vertices.next();
            return Optional.ofNullable(vertex);
        }
        return Optional.empty();
    }

    public final Optional<AbstractActivity> get(String id) {
        Vertex vertex = getVertex(id).orElse(null);
        if (vertex != null) {
            return Optional.ofNullable((AbstractActivity) convertToEntity((OrientVertex) vertex));
        }
        return Optional.empty();
    }

    @Override
    public final void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
