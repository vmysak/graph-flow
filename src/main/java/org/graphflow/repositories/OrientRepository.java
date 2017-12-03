package org.graphflow.repositories;

import org.apache.tinkerpop.gremlin.orientdb.OrientGraph;
import org.apache.tinkerpop.gremlin.orientdb.OrientVertex;
import org.springframework.context.ApplicationContextAware;

import java.util.Optional;

public interface OrientRepository<V> extends ApplicationContextAware {

    String graphName();

    OrientGraph getGraph();

    OrientVertex createVertex(V v);

    Optional<OrientVertex> getVertex(String id);

}
