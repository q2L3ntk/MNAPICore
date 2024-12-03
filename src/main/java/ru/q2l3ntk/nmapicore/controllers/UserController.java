package ru.q2l3ntk.nmapicore.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.q2l3ntk.nmapicore.components.assembler.UserAssembler;
import ru.q2l3ntk.nmapicore.models.User;
import ru.q2l3ntk.nmapicore.objects.UserListVO;
import ru.q2l3ntk.nmapicore.objects.UserVO;
import ru.q2l3ntk.nmapicore.repositories.UserRepository;
import ru.q2l3ntk.nmapicore.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private UserAssembler userAssembler;
    private UserRepository userRepository;

    @PostMapping("/registrations")
    public ResponseEntity<UserVO> create(@Validated @RequestBody User userDetails) {
        User user = userService.attemptRegistration(userDetails);

        return ResponseEntity.ok(userAssembler.toUserVO(user));
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserVO> show(@PathVariable("user_id") Long userId) {
        User user = userService.retrieveUserData(userId);
        return ResponseEntity.ok(userAssembler.toUserVO(user));
    }

    @GetMapping("/details")
    public ResponseEntity<UserVO> echoDetails(HttpServletRequest request) {
        User user = userRepository.findByUsername(request.getUserPrincipal().getName());
        return ResponseEntity.ok(userAssembler.toUserVO(user));
    }

    @GetMapping
    public ResponseEntity<UserListVO> index(HttpServletRequest request) {
        User user = userRepository.findByUsername(request.getUserPrincipal().getName());
        List<User> users = userService.listUsers(user);

        return ResponseEntity.ok(userAssembler.toUserListVO(users));
    }

    @PutMapping
    public ResponseEntity<UserVO> update(@Validated @RequestBody User updateDetails, HttpServletRequest request) {
        User currentUser = userRepository.findByUsername(request.getUserPrincipal().getName());
        userService.updateUserStatus(currentUser, updateDetails);

        return ResponseEntity.ok(userAssembler.toUserVO(currentUser));
    }
}
