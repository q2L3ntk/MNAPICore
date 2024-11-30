package ru.q2l3ntk.nmapicore.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.q2l3ntk.nmapicore.repositories.UserRepository;

@Component
@AllArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = (UserDetails) userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("Username with the username %s not found", username));
        } else {
            user.getUsername();
            user.getPassword();
            user.getAuthorities();
            return user;
        }
    }
}
