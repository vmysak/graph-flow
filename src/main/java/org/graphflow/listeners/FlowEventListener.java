package org.graphflow.listeners;

import org.graphflow.events.base.FlowEvent;
import org.graphflow.events.base.ShutdownEvent;
import org.graphflow.events.base.StartupEvent;

public interface FlowEventListener<T extends FlowEvent> {

    void onEvent(T event);

    void onStartupEvent(StartupEvent event);

    void onShutdownEvent(ShutdownEvent event);

}
