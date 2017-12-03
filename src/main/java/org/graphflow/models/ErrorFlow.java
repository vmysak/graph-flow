package org.graphflow.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.graphflow.annotations.EdgeEntity;

@EdgeEntity
@Data
@EqualsAndHashCode(callSuper = true)
public class ErrorFlow extends AbstractFlow {

}
