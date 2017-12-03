package org.graphflow.startup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tinkerpop.gremlin.orientdb.OrientElement;
import org.graphflow.events.base.FlowEvent;
import org.graphflow.events.base.ShutdownEvent;
import org.graphflow.events.base.StartupEvent;
import org.graphflow.listeners.FlowEventListener;
import org.graphflow.models.AbstractEntity;
import org.graphflow.serialization.EntityClassMapping;
import org.graphflow.serialization.EntitySerializer;
import org.graphflow.serialization.OrientElementSerializer;
import org.graphflow.serialization.OrientPropertiesSerializer;
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
        EntityClassMapping mapping = EntityClassMapping.from(annotationLoaderService);

        GsonBuilder builder = new GsonBuilder()
                .registerTypeHierarchyAdapter(AbstractEntity.class, new EntitySerializer(mapping))
                .registerTypeHierarchyAdapter(OrientElement.class, new OrientElementSerializer())
                .registerTypeAdapter(OrientPropertiesSerializer.TYPE, new OrientPropertiesSerializer())
                .addSerializationExclusionStrategy(new SerializationStrategy())
                .addDeserializationExclusionStrategy(new DeserializationStrategy());

        Gson gson = builder.create();
        beanFactory.registerSingleton(ACTIVITY_SERIALIZER, gson);
    }


    @Override
    public void onShutdownEvent(ShutdownEvent event) {

    }

}
