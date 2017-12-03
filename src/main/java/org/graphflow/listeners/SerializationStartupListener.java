package org.graphflow.listeners;

import lombok.AllArgsConstructor;
import org.graphflow.events.SerializationStartupEvent;
import org.graphflow.startup.SerializationStartupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SerializationStartupListener implements ApplicationListener<SerializationStartupEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(SerializationStartupListener.class);

    private final SerializationStartupService serializationStartupService;

    @Override
    public void onApplicationEvent(SerializationStartupEvent event) {
        serializationStartupService.onStartupEvent(event);
    }
}
