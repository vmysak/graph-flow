package org.graphflow.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractActivity extends AbstractEntity {

    private Status status;

    public enum Status {
        NOT_STARTED,
        STARTED,
        FINISHED
    }
}
