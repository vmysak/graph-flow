package org.graphflow.controllers;

import lombok.AllArgsConstructor;
import org.graphflow.models.User;
import org.graphflow.repositories.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @PostMapping
    public User save(@RequestBody User user) {
        return userRepository.save(user);
    }
}
