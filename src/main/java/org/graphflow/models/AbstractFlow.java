package org.graphflow.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractFlow extends AbstractEntity {

    private String parameters;

}
