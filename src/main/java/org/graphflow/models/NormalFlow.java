package org.graphflow.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.graphflow.annotations.EdgeEntity;
import org.graphflow.annotations.VertexEntity;

@EdgeEntity
@Data
@EqualsAndHashCode(callSuper = true)
public class NormalFlow extends AbstractFlow {

}
