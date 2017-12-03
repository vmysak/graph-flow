package org.graphflow.repositories;

import com.orientechnologies.orient.core.id.ORID;
import lombok.AllArgsConstructor;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.graphflow.commons.Constants;
import org.graphflow.models.AbstractActivity;
import org.graphflow.models.AbstractFlow;
import org.graphflow.models.EndActivity;
import org.graphflow.models.ErrorActivity;
import org.graphflow.models.ErrorFlow;
import org.graphflow.models.GatewayActivity;
import org.graphflow.models.NormalFlow;
import org.graphflow.models.StartActivity;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class ProcessRepository extends AbstractActivityRepository<AbstractActivity, AbstractFlow> {

    public ORID saveStartActivity(StartActivity startActivity) {
        GatewayActivity gatewayActivity = new GatewayActivity();
        gatewayActivity.setName(startActivity.getName());
        Vertex startVertex = createVertex(startActivity);

        if (startActivity.isWithError()) {
            ErrorActivity errorActivity = new ErrorActivity();
            Vertex error = createVertex(errorActivity);
            createEdge(startVertex, error, new ErrorFlow());
            getGraph().commit();
            return (ORID) startVertex.id();
        }

        EndActivity e1 = new EndActivity();
        EndActivity e2 = new EndActivity();

        Vertex gwVertex = createVertex(gatewayActivity);
        Vertex e1V = createVertex(e1);
        Vertex e2V = createVertex(e2);

        createEdge(startVertex, gwVertex, new NormalFlow());
        createEdge(gwVertex, e1V, new NormalFlow());
        createEdge(gwVertex, e2V, new NormalFlow());

        getGraph().commit();
        return (ORID) startVertex.id();
    }

    @Override
    public String graphName() {
        return Constants.PROCESS_GRAPH;
    }
}
