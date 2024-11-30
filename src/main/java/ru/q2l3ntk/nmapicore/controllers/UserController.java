package ru.q2l3ntk.nmapicore.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.q2l3ntk.nmapicore.models.User;
import ru.q2l3ntk.nmapicore.repositories.UserRepository;
import ru.q2l3ntk.nmapicore.services.UserService;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private UserRepository userRepository;

    @PostMapping("/registrations")
    public ResponseEntity<User> create(@Validated @RequestBody User userDetails) {
        User user = userService.attemptRegistration(userDetails);

        return ResponseEntity.ok(user);
    }
}
