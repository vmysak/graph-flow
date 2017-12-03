package org.graphflow.repositories;

import com.tinkerpop.blueprints.TransactionalGraph;
import com.tinkerpop.blueprints.Vertex;
import org.graphflow.utils.OrientUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class AbstractGraphRepository<V> implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    protected abstract String graphName();

    protected final TransactionalGraph graph() {
        return (TransactionalGraph) applicationContext.getBean(graphName());
    }

    protected final Vertex vertex(V v) {
        return graph().addVertex(OrientUtil.getVertexName(v));
    }

    @Override
    public final void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
