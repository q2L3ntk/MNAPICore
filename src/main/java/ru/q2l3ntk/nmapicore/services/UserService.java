package ru.q2l3ntk.nmapicore.services;

import ru.q2l3ntk.nmapicore.models.User;

import java.util.List;

public interface UserService {
    User attemptRegistration(User userDetails);
    List<User> listUsers();
    User retrieveUserData(String username);
    User retrieveUserData(Long id);
    Boolean usernameExists(String username);
}
