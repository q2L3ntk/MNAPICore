package ru.q2l3ntk.nmapicore.listeners;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.q2l3ntk.nmapicore.models.User;

public class UserListener {
    @PrePersist
    @PreUpdate
    public void hashPassword(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.password = encoder.encode(user.password);
    }
}
