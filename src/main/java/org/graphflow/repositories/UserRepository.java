package org.graphflow.repositories;

import org.graphflow.models.User;
import org.springframework.data.orient.commons.repository.OrientRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends OrientRepository<User> {

}
