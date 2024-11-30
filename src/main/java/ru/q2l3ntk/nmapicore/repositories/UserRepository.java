package ru.q2l3ntk.nmapicore.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.q2l3ntk.nmapicore.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByPhoneNumber(String phoneNumber);
}
