package org.graphflow.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.graphflow.events.base.StartupEvent;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrientDBStartupEvent extends StartupEvent {

    public OrientDBStartupEvent(Object source) {
        super(source);
    }

}
