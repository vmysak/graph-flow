package org.graphflow.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.graphflow.annotations.VertexEntity;
import org.graphflow.annotations.GraphProperty;

@VertexEntity
@Data
@EqualsAndHashCode(callSuper = true)
public class StartActivity extends AbstractActivity {

    @GraphProperty(serialize = false)
    private String gatewayName;
    private boolean withError;

}
