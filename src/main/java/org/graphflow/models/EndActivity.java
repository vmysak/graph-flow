package org.graphflow.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.graphflow.annotations.VertexEntity;

@VertexEntity
@Data
@EqualsAndHashCode(callSuper = true)
public class EndActivity extends AbstractActivity {

    private String processStatus;

}
