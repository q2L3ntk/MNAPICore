package ru.q2l3ntk.nmapicore.services.tokens

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import java.util.*
import javax.crypto.SecretKey

internal object TokenAuthenticationService {
    private val TOKEN_EXPIRY: Long = 864000000
    private val SECRET: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512)
    private val TOKEN_PREFIX = "Bearer"
    private val AUTHORIZATION_HEADER_KEY = "Authorization"

    fun addAuthentication(res: HttpServletResponse, username: String) {
        val JWT = Jwts.builder()
            .setSubject(username)
            .setExpiration(Date(System.currentTimeMillis() + TOKEN_EXPIRY))
            .signWith(SECRET)
            .compact()

        res.addHeader(AUTHORIZATION_HEADER_KEY, "$TOKEN_PREFIX $JWT")
    }

    fun getAuthentication(request: HttpServletRequest): Authentication? {
        val token = request.getHeader(AUTHORIZATION_HEADER_KEY)

        if (token != null) {
            val user = Jwts.parserBuilder()
                .setSigningKey(SECRET).build()
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .body.subject

            if (user != null) {
                return UsernamePasswordAuthenticationToken(user, null, emptyList<GrantedAuthority>())
            }
        }

        return null
    }
}