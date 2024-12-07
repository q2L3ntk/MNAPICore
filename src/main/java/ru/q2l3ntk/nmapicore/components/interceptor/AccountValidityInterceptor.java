package ru.q2l3ntk.nmapicore.components.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ru.q2l3ntk.nmapicore.exceptions.UserDeactivatedException;
import ru.q2l3ntk.nmapicore.models.User;
import ru.q2l3ntk.nmapicore.repositories.UserRepository;

import java.security.Principal;

@Component
@AllArgsConstructor
public class AccountValidityInterceptor implements HandlerInterceptor {
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UserDeactivatedException {
        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            User user = userRepository.findByUsername(principal.getName());

            if (user.getStatus().equals("deactivated")) {
                throw new UserDeactivatedException("The account is deactivated");
            }
        }

        return true;
    }
}
