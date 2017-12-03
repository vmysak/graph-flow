package org.graphflow.repositories;

import com.tinkerpop.blueprints.Vertex;
import lombok.AllArgsConstructor;
import org.graphflow.commons.Constants;
import org.graphflow.models.User;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class UserRepositoryImpl extends AbstractGraphRepository<User> implements UserRepository {

    @Override
    public User save(User user) {
        Vertex vertex = vertex(user);
        vertex.setProperty("user", 1);
        Vertex vertex2 = vertex(user);
        vertex.setProperty("user", 2);
        graph().commit();
        return user;
    }

    @Override
    protected String graphName() {
        return Constants.USERS_GRAPH;
    }
}
