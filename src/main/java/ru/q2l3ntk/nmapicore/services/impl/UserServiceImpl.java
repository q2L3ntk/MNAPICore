package ru.q2l3ntk.nmapicore.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.q2l3ntk.nmapicore.exceptions.InvalidUserIdException;
import ru.q2l3ntk.nmapicore.exceptions.UserStatusException;
import ru.q2l3ntk.nmapicore.exceptions.UsernameUnavailableException;
import ru.q2l3ntk.nmapicore.models.User;
import ru.q2l3ntk.nmapicore.repositories.UserRepository;
import ru.q2l3ntk.nmapicore.services.UserService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository repository;

    private void obscurePassword(User user) {
        user.setPassword("XXX XXX XXX");
    }

    @Override
    public User attemptRegistration(User userDetails) throws UsernameUnavailableException {
        if (!usernameExists(userDetails.getUsername())) {
            User user = new User();
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPhoneNumber());
            user.setPassword(userDetails.getPassword());
            repository.save(user);
            obscurePassword(user);
            return user;
        }

        throw new UsernameUnavailableException(String.format("The username %s is unavailable.", userDetails.getUsername()));
    }

    public User updateUserStatus(User currentUser, User updateDetails) throws UserStatusException {
        if (!updateDetails.getStatus().isEmpty()) {
            currentUser.setStatus(updateDetails.getStatus());
            repository.save(currentUser);
            return currentUser;
        }

        throw new UserStatusException();
    }

    @Override
    public List<User> listUsers(User currentUser) {
        return (List<User>) repository.findAll();
    }

    @Override
    public User retrieveUserData(String username) {
        User user = repository.findByUsername(username);
        obscurePassword(user);
        return user;
    }

    @Override
    public User retrieveUserData(Long id) throws InvalidUserIdException {
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            obscurePassword(user);
            return user;
        }

        throw new InvalidUserIdException(String.format("The user with id %s does not exist.", id));
    }

    @Override
    public Boolean usernameExists(String username) {
        return repository.findByUsername(username) != null;
    }
}
