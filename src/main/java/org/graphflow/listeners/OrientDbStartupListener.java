package org.graphflow.listeners;

import lombok.AllArgsConstructor;
import org.graphflow.events.OrientDBStartupEvent;
import org.graphflow.services.OrientDBGraphService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class OrientDbStartupListener implements ApplicationListener<OrientDBStartupEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(OrientDbStartupListener.class);

    private final OrientDBGraphService orientDBGraphService;

    @Override
    public void onApplicationEvent(OrientDBStartupEvent event) {
        orientDBGraphService.onStartupEvent(event);
    }
}
