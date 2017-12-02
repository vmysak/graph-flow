package org.graphflow.repositories;

import org.graphflow.models.User;
import org.springframework.data.orient.graph.repository.OrientGraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends OrientGraphRepository<User> {

}
