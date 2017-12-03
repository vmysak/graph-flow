package org.graphflow.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.graphflow.annotations.VertexEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@VertexEntity
@EqualsAndHashCode(callSuper = true)
public class StartActivity extends AbstractActivity {

    private String type;

}
