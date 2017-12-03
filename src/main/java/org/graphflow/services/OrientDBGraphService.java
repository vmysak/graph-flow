package org.graphflow.services;

import com.tinkerpop.blueprints.TransactionalGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;
import lombok.AllArgsConstructor;
import org.graphflow.annotations.VertexEntity;
import org.graphflow.commons.Constants;
import org.graphflow.events.base.FlowEvent;
import org.graphflow.events.base.ShutdownEvent;
import org.graphflow.events.base.StartupEvent;
import org.graphflow.listeners.FlowEventListener;
import org.graphflow.utils.OrientUtil;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
@Service
public class OrientDBGraphService implements FlowEventListener {

    private final AnnotationLoaderService annotationLoaderService;
    private final OrientGraphFactory graphFactory;
    private final ConfigurableListableBeanFactory beanFactory;

    @Override
    public void onEvent(FlowEvent event) {
    }

    @Override
    public void onStartupEvent(StartupEvent event) {
        setupGraphs();
        createUserGraph();
    }

    @Override
    public void onShutdownEvent(ShutdownEvent event) {

    }

    private void createUserGraph() {
        TransactionalGraph graph = graphFactory.getTx();
        beanFactory.registerSingleton(Constants.USERS_GRAPH, graph);
        beanFactory.autowireBean(graph);
    }

    private void setupGraphs() {
        OrientGraphNoTx graph = graphFactory.getNoTx();
        graph.setUseClassForVertexLabel(true);

        Set<Class<?>> classes = annotationLoaderService.loadClasses(VertexEntity.class);
        classes.forEach(clazz -> {
            String name = OrientUtil.getVertexName(clazz);
            if (graph.getVertexType(name) == null) {
                graph.createVertexType(name);
            }
        });
    }

}
