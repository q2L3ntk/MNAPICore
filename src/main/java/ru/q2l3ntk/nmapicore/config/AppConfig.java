package ru.q2l3ntk.nmapicore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.q2l3ntk.nmapicore.components.interceptor.AccountValidityInterceptor;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Autowired
    private AccountValidityInterceptor accountValidityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accountValidityInterceptor);
    }
}
