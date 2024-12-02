package ru.q2l3ntk.nmapicore.filters

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import ru.q2l3ntk.nmapicore.security.AccountCredentials
import ru.q2l3ntk.nmapicore.services.tokens.TokenAuthenticationService
import java.io.IOException

@Deprecated("Due to lack of opportunity to implement")
class JWTLoginFilter(url: String, authManager: AuthenticationManager) : AbstractAuthenticationProcessingFilter(url, authManager) {
    init {
        authenticationManager = authManager
    }

    @Throws(AuthenticationException::class, IOException::class, ServletException::class)
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val credentials = ObjectMapper().readValue(request?.inputStream, AccountCredentials::class.java)

        return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(credentials.username, credentials.password, emptyList()))
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        chain: FilterChain?,
        authResult: Authentication
    ) {
        TokenAuthenticationService.addAuthentication(response, authResult.name);
    }
}