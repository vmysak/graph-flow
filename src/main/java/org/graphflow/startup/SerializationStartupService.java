package org.graphflow.startup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tinkerpop.gremlin.orientdb.OrientVertex;
import org.graphflow.events.base.FlowEvent;
import org.graphflow.events.base.ShutdownEvent;
import org.graphflow.events.base.StartupEvent;
import org.graphflow.listeners.FlowEventListener;
import org.graphflow.models.AbstractActivity;
import org.graphflow.serialization.ActivitySerializer;
import org.graphflow.serialization.OrientVertexPropertiesSerializer;
import org.graphflow.serialization.OrientVertexSerializer;
import org.graphflow.serialization.VertexClassMapping;
import org.graphflow.serialization.strategies.DeserializationStrategy;
import org.graphflow.serialization.strategies.SerializationStrategy;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Service;

import static org.graphflow.commons.Constants.ACTIVITY_SERIALIZER;

@Slf4j
@AllArgsConstructor
@Service
public class SerializationStartupService implements FlowEventListener {

    private final AnnotationLoaderService annotationLoaderService;
    private final ConfigurableListableBeanFactory beanFactory;

    @Override
    public void onEvent(FlowEvent event) {
    }

    @Override
    public void onStartupEvent(StartupEvent event) {
        setupSerializators();
        log.info("Serializers initialization completed");
    }

    private void setupSerializators() {
        VertexClassMapping mapping = VertexClassMapping.from(annotationLoaderService);

        GsonBuilder builder = new GsonBuilder()
                .registerTypeHierarchyAdapter(AbstractActivity.class, new ActivitySerializer(mapping))
                .registerTypeAdapter(OrientVertex.class, new OrientVertexSerializer())
                .registerTypeAdapter(OrientVertexPropertiesSerializer.TYPE, new OrientVertexPropertiesSerializer())
                .addSerializationExclusionStrategy(new SerializationStrategy())
                .addDeserializationExclusionStrategy(new DeserializationStrategy());

        Gson gson = builder.create();
        beanFactory.registerSingleton(ACTIVITY_SERIALIZER, gson);
    }


    @Override
    public void onShutdownEvent(ShutdownEvent event) {

    }

}
