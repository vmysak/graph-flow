package org.graphflow.startup;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraph;
import org.apache.tinkerpop.gremlin.orientdb.OrientGraphFactory;
import org.graphflow.annotations.EdgeEntity;
import org.graphflow.annotations.VertexEntity;
import org.graphflow.commons.Constants;
import org.graphflow.events.base.FlowEvent;
import org.graphflow.events.base.ShutdownEvent;
import org.graphflow.events.base.StartupEvent;
import org.graphflow.listeners.FlowEventListener;
import org.graphflow.utils.ActivityGraphUtil;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@Service
public class OrientDBStartupService implements FlowEventListener {

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
        log.info("DB Initialization completed");
    }

    @Override
    public void onShutdownEvent(ShutdownEvent event) {

    }

    private void createUserGraph() {
        OrientGraph graph = graphFactory.getTx();
        beanFactory.registerSingleton(Constants.PROCESS_GRAPH, graph);
        beanFactory.autowireBean(graph);
    }

    private void setupGraphs() {
        OrientGraph graph = graphFactory.getNoTx();

        Map<String, Class<?>> vertexClasses = annotationLoaderService.loadClasses(VertexEntity.class);
        vertexClasses.forEach((name, clazz) -> graph.createVertexClass(ActivityGraphUtil.getEntityLabel(graph, name)));

        Map<String, Class<?>> edgeClasses = annotationLoaderService.loadClasses(EdgeEntity.class);
        edgeClasses.forEach((name, clazz) -> graph.createEdgeClass(ActivityGraphUtil.getEntityLabel(graph, name)));
    }

}
