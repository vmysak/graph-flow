package org.graphflow.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.graphflow.events.base.StartupEvent;

@Data
@EqualsAndHashCode(callSuper = true)
public class SerializationStartupEvent extends StartupEvent {

    public SerializationStartupEvent(Object source) {
        super(source);
    }

}
