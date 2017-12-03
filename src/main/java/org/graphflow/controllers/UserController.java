package org.graphflow.controllers;

import lombok.AllArgsConstructor;
import org.graphflow.models.User;
import org.graphflow.repositories.UserRepository;
import org.graphflow.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }
}
