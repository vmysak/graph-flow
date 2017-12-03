package org.graphflow.repositories;

import lombok.AllArgsConstructor;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.graphflow.commons.Constants;
import org.graphflow.models.StartActivity;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class ProcessRepository extends AbstractActivityRepository<StartActivity> {

    public StartActivity saveStartActivity(StartActivity startActivity) {
        Vertex vertex = vertex(startActivity);
        graph().commit();
        return startActivity;
    }

    @Override
    protected String graphName() {
        return Constants.PROCESS_GRAPH;
    }
}
