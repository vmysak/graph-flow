package org.graphflow.repositories;

import org.apache.tinkerpop.gremlin.orientdb.OrientGraph;
import org.apache.tinkerpop.gremlin.orientdb.OrientVertex;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.context.ApplicationContextAware;

import java.util.Optional;

public interface OrientRepository<V, F> extends ApplicationContextAware {

    String graphName();

    OrientGraph getGraph();

    Vertex createVertex(V v);

    Edge createEdge(Vertex from, Vertex to, F f);

    Optional<Vertex> getVertex(String id);

}
