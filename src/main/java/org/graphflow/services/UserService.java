package org.graphflow.services;

import lombok.AllArgsConstructor;
import org.graphflow.models.User;
import org.graphflow.repositories.UserRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }
}
