package org.graphflow.repositories;

import org.apache.tinkerpop.gremlin.orientdb.OrientGraph;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.graphflow.models.AbstractActivity;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import static org.graphflow.utils.ActivityGraphUtil.getVertexBody;
import static org.graphflow.utils.ActivityGraphUtil.getVertexLabel;

public abstract class AbstractActivityRepository<V extends AbstractActivity> implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    protected abstract String graphName();

    protected final OrientGraph graph() {
        return (OrientGraph) applicationContext.getBean(graphName());
    }

    protected final Vertex vertex(V v) {
        String vertexLabel = getVertexLabel(graph(), v);
        Vertex vertex = graph().addVertex(vertexLabel);
        getVertexBody(v).forEach(vertex::property);
        return vertex;
    }

    @Override
    public final void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
