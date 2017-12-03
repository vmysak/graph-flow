package org.graphflow.repositories;

import org.apache.tinkerpop.gremlin.orientdb.OrientGraph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import static org.graphflow.utils.OrientUtil.getVertexLabel;

public abstract class AbstractGraphRepository<V> implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    protected abstract String graphName();

    protected final OrientGraph graph() {
        return (OrientGraph) applicationContext.getBean(graphName());
    }

    protected final Vertex vertex(V v) {
        return graph().addVertex(getVertexLabel(graph(), v));
    }

    @Override
    public final void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
