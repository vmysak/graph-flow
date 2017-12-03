package org.graphflow.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.graphflow.annotations.VertexEntity;

import java.util.Set;

@VertexEntity
@Data
@EqualsAndHashCode(callSuper = true)
public class GatewayActivity extends AbstractActivity {

}
