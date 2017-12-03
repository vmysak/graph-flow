package org.graphflow.events.base;

public abstract class StartupEvent extends FlowEvent {

    public StartupEvent(Object source) {
        super(source);
    }

    public StartupEvent(Object source, FlowEvent trigger) {
        super(source, trigger);
    }
}
