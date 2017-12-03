package org.graphflow.repositories;

import com.orientechnologies.orient.core.id.ORID;
import lombok.AllArgsConstructor;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.graphflow.commons.Constants;
import org.graphflow.models.StartActivity;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class ProcessRepository extends AbstractActivityRepository<StartActivity> {

    public ORID saveStartActivity(StartActivity startActivity) {
        Vertex vertex = createVertex(startActivity);
        getGraph().commit();
        return (ORID) vertex.id();
    }

    @Override
    public String graphName() {
        return Constants.PROCESS_GRAPH;
    }
}
