package org.graphflow.events.base;

import org.springframework.context.ApplicationEvent;

public abstract class FlowEvent extends ApplicationEvent {

    private final FlowEvent trigger;

    public FlowEvent(Object source) {
        super(source);
        this.trigger = null;
    }

    public FlowEvent(Object source, FlowEvent trigger) {
        super(source);
        this.trigger = trigger;
    }

    public final FlowEvent getTrigger() {
        return trigger;
    }
}
