package org.graphflow.repositories;

import org.graphflow.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    User save(User user);

}
