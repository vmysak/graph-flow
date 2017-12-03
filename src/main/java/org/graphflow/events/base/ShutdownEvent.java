package org.graphflow.events.base;

public abstract class ShutdownEvent extends FlowEvent {

    public ShutdownEvent(Object source) {
        super(source);
    }

    public ShutdownEvent(Object source, FlowEvent trigger) {
        super(source, trigger);
    }
}
