package org.graphflow;

import lombok.AllArgsConstructor;
import org.graphflow.events.OrientDBStartupEvent;
import org.graphflow.events.SerializationStartupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationStartupListener.class);

    private final ApplicationEventPublisher publisher;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        publisher.publishEvent(new OrientDBStartupEvent(this));
        publisher.publishEvent(new SerializationStartupEvent(this));
    }

}
